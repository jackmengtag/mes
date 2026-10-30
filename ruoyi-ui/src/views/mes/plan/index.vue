<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>计划管理</h2>
        <span class="sub">集团总计划 · 生产指令 · 计划排产 · 日计划 · 投料单</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="load">刷新</el-button>
        <el-button type="primary" plain :disabled="!selected.length" v-hasPermi="['mes:plan:publish']" @click="batchPublish">发布</el-button>
        <el-button type="primary" plain :disabled="selected.length !== 1" v-hasPermi="['mes:plan:edit']" @click="batchWorkOrder">生成工单</el-button>
        <el-button icon="Download" v-hasPermi="['mes:plan:export']" @click="handleExport">导出</el-button>
        <el-button type="primary" icon="Plus" v-hasPermi="['mes:plan:add']" @click="openForm()">新增计划</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-tabs v-model="activeTab" @tab-change="onTabChange">
      <el-tab-pane v-for="t in tabs" :key="t.value" :label="t.label" :name="t.value" />
    </el-tabs>

    <el-form :inline="true" class="query-bar">
      <el-form-item label="关键字">
        <el-input v-model="keyword" placeholder="计划单号 / 产品" clearable style="width: 230px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="status" clearable placeholder="全部" style="width: 150px" @change="handleQuery">
          <el-option v-for="item in statusOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="rows" size="small" border @selection-change="onSelectionChange">
      <el-table-column type="selection" width="44" />
      <el-table-column prop="planCode" label="计划单号" width="150" />
      <el-table-column label="产品" min-width="180" show-overflow-tooltip>
        <template #default="{ row }">
          <span>{{ row.productName || '-' }}</span>
          <span class="code">{{ row.productCode }}</span>
        </template>
      </el-table-column>
      <el-table-column label="数量" width="110" align="right">
        <template #default="{ row }"><span class="num">{{ fmt(row.qty) }}</span></template>
      </el-table-column>
      <el-table-column prop="unit" label="单位" width="70" />
      <el-table-column label="产线" width="90">
        <template #default="{ row }">{{ row.lineCode || '-' }}</template>
      </el-table-column>
      <el-table-column prop="startDate" label="开始日期" width="110" />
      <el-table-column prop="endDate" label="结束日期" width="110" />
      <el-table-column label="状态" width="100">
        <template #default="{ row }"><MesStatusTag :value="row.status" type="plan" /></template>
      </el-table-column>
      <el-table-column label="来源单号" width="140">
        <template #default="{ row }">{{ row.sourceCode || '-' }}</template>
      </el-table-column>
      <el-table-column label="操作" width="210" fixed="right">
        <template #default="{ row }">
          <el-button link type="primary" v-hasPermi="['mes:plan:publish']" @click="publish(row)">发布</el-button>
          <el-button link type="primary" v-hasPermi="['mes:plan:edit']" @click="toWorkOrder(row)">生成工单</el-button>
          <el-button link type="primary" v-hasPermi="['mes:plan:edit']" @click="openForm(row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="page" v-model:limit="size" @pagination="load" />

    <el-card shadow="never" class="panel achieve-panel">
      <template #header><span>计划达成 · 按产线（工单口径）</span></template>
      <el-table :data="achieveRows" size="small" border>
        <el-table-column prop="lineCode" label="产线" width="110" />
        <el-table-column label="计划数量" width="140" align="right">
          <template #default="{ row }"><span class="num">{{ fmt(row.planQty) }}</span></template>
        </el-table-column>
        <el-table-column label="完成数量" width="140" align="right">
          <template #default="{ row }"><span class="num">{{ fmt(row.doneQty) }}</span></template>
        </el-table-column>
        <el-table-column label="差异" width="120" align="right">
          <template #default="{ row }"><span class="num">{{ fmt(row.planQty - row.doneQty) }}</span></template>
        </el-table-column>
        <el-table-column label="达成率" min-width="240">
          <template #default="{ row }">
            <el-progress :percentage="row.rate" :stroke-width="8" :status="row.rate >= 90 ? 'success' : 'warning'" />
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="formOpen" :title="form.planId ? '编辑计划' : '新增计划'" width="680px" append-to-body>
      <el-form ref="planFormRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="计划单号" prop="planCode">
          <el-input v-model="form.planCode" placeholder="如 PD-261030-001" />
        </el-form-item>
        <el-form-item label="计划类型" prop="planType">
          <el-select v-model="form.planType" style="width: 100%">
            <el-option v-for="t in tabs" :key="t.value" :label="t.label" :value="t.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="计划月份" prop="planMonth">
          <el-date-picker v-model="form.planMonth" type="month" value-format="YYYY-MM" placeholder="选择月份" style="width: 100%" />
        </el-form-item>
        <el-form-item label="产品" prop="productCode">
          <el-select v-model="form.productCode" filterable placeholder="请选择产品" style="width: 100%" @change="pickProduct">
            <el-option v-for="m in products" :key="m.materialId" :label="m.productName || m.materialName" :value="m.materialCode">
              <span>{{ m.materialName }}</span>
              <span class="option-code">{{ m.materialCode }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="产品名称">
          <el-input v-model="form.productName" placeholder="选择产品后自动带出" />
        </el-form-item>
        <el-form-item label="计划数量" prop="qty">
          <el-input-number v-model="form.qty" :min="1" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="单位">
          <el-input v-model="form.unit" placeholder="台 / 件" />
        </el-form-item>
        <el-form-item label="产线">
          <el-select v-model="form.lineCode" clearable placeholder="请选择产线" style="width: 100%">
            <el-option v-for="l in lines" :key="l" :label="l" :value="l" />
          </el-select>
        </el-form-item>
        <el-form-item label="开始日期">
          <el-date-picker v-model="form.startDate" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
        <el-form-item label="结束日期">
          <el-date-picker v-model="form.endDate" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
        <el-form-item label="来源单号">
          <el-input v-model="form.sourceCode" placeholder="上游计划 / 指令单号" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="formOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitForm">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="MesPlan">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { listPlan, addPlan, updatePlan } from '@/api/mes/plan'
import { publishPlan, planToWorkOrder } from '@/api/mes/biz'
import { listMaterial } from '@/api/mes/material'
import { listWorkOrder } from '@/api/mes/workOrder'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'

const { proxy } = getCurrentInstance()

const tabs = [
  { value: 'group', label: '总计划' },
  { value: 'instruction', label: '生产指令' },
  { value: 'schedule', label: '计划排产' },
  { value: 'daily', label: '日计划' },
  { value: 'feeding', label: '投料单' }
]
const lines = ['MC-01', 'MC-02', 'AS-01', 'AS-02', 'TS-01', 'PK-01']
const statusOptions = [
  { value: 'draft', label: '草稿' },
  { value: 'released', label: '已发布' },
  { value: 'running', label: '执行中' },
  { value: 'finished', label: '已完成' },
  { value: 'locked', label: '已锁定' }
]
const prefixMap = { group: 'GP', instruction: 'PI', schedule: 'PS', daily: 'PD', feeding: 'PF' }

const activeTab = ref('group')
const loading = ref(false)
const saving = ref(false)
const rows = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(10)
const keyword = ref('')
const status = ref('')
const selected = ref([])
const allPlans = ref([])
const products = ref([])
const workOrders = ref([])

const formOpen = ref(false)
const form = ref({})
const planFormRef = ref()

const rules = {
  planCode: [{ required: true, message: '请输入计划单号', trigger: 'blur' }],
  planType: [{ required: true, message: '请选择计划类型', trigger: 'change' }],
  planMonth: [{ required: true, message: '请选择计划月份', trigger: 'change' }],
  productCode: [{ required: true, message: '请选择产品', trigger: 'change' }],
  qty: [{ required: true, message: '请输入计划数量', trigger: 'blur' }]
}

const num = v => (v === null || v === undefined ? 0 : Number(v))
const fmt = v => num(v).toLocaleString('zh-CN', { maximumFractionDigits: 2 })

function pad(n) {
  return String(n).padStart(2, '0')
}

function currentMonth() {
  const d = new Date()
  return d.getFullYear() + '-' + pad(d.getMonth() + 1)
}

function today() {
  const d = new Date()
  return d.getFullYear() + '-' + pad(d.getMonth() + 1) + '-' + pad(d.getDate())
}

function genPlanCode(type) {
  const d = new Date()
  const ymd = String(d.getFullYear()).slice(2) + pad(d.getMonth() + 1) + pad(d.getDate())
  return (prefixMap[type] || 'PL') + '-' + ymd + '-' + String(Math.floor(Math.random() * 900) + 100)
}

const kpis = computed(() => {
  const list = allPlans.value
  const month = currentMonth()
  return [
    { label: '本月计划数量', value: list.filter(i => i.planMonth === month).length, unit: '单', foot: '按计划月份统计', tone: '' },
    { label: '已发布计划', value: list.filter(i => i.status === 'released').length, unit: '单', foot: '待下达执行', tone: 'ok' },
    { label: '执行中计划', value: list.filter(i => i.status === 'running').length, unit: '单', foot: '车间执行中', tone: 'accent' },
    { label: '锁定计划', value: list.filter(i => i.status === 'locked').length, unit: '单', foot: 'D+3 锁定，变更需审批', tone: 'warn' }
  ]
})

const achieveRows = computed(() => {
  const map = {}
  workOrders.value.forEach(w => {
    const key = w.lineCode || '未分配'
    if (!map[key]) map[key] = { lineCode: key, planQty: 0, doneQty: 0, rate: 0 }
    map[key].planQty += num(w.qty)
    map[key].doneQty += num(w.completedQty)
  })
  const order = lines.concat(['未分配'])
  return Object.values(map)
    .map(i => ({ ...i, rate: i.planQty ? Math.min(Math.round((i.doneQty / i.planQty) * 100), 100) : 0 }))
    .sort((a, b) => order.indexOf(a.lineCode) - order.indexOf(b.lineCode))
})

function buildQuery() {
  const q = { pageNum: page.value, pageSize: size.value, planType: activeTab.value }
  if (keyword.value) q.params = { keyword: keyword.value }
  if (status.value) q.status = status.value
  return q
}

async function load() {
  loading.value = true
  try {
    const res = await listPlan(buildQuery())
    rows.value = res.rows || []
    total.value = res.total || 0
  } finally {
    loading.value = false
  }
}

async function loadMeta() {
  const p = await listPlan({ pageNum: 1, pageSize: 500 })
  allPlans.value = p.rows || []
  const m = await listMaterial({ pageNum: 1, pageSize: 500 })
  products.value = (m.rows || []).filter(i => i.productFlag === '1')
  const w = await listWorkOrder({ pageNum: 1, pageSize: 500 })
  workOrders.value = w.rows || []
}

function onTabChange() {
  status.value = ''
  page.value = 1
  load()
}

function handleQuery() {
  page.value = 1
  load()
}

function resetQuery() {
  keyword.value = ''
  status.value = ''
  page.value = 1
  load()
}

function onSelectionChange(list) {
  selected.value = list
}

function openForm(row) {
  form.value = row
    ? { ...row }
    : {
        planCode: genPlanCode(activeTab.value),
        planType: activeTab.value,
        planMonth: currentMonth(),
        productCode: '',
        productName: '',
        qty: 100,
        unit: '台',
        lineCode: '',
        startDate: today(),
        endDate: today(),
        sourceCode: ''
      }
  formOpen.value = true
}

function pickProduct(code) {
  const hit = products.value.find(i => i.materialCode === code)
  if (hit) {
    form.value.productName = hit.materialName
    form.value.unit = hit.unit || form.value.unit
  }
}

function submitForm() {
  planFormRef.value.validate(async valid => {
    if (!valid) return
    saving.value = true
    try {
      const payload = { ...form.value }
      if (payload.planId) {
        await updatePlan(payload)
        proxy.$modal.msgSuccess('计划修改成功')
      } else {
        payload.status = 'draft'
        await addPlan(payload)
        proxy.$modal.msgSuccess('计划已创建')
      }
      formOpen.value = false
      await loadMeta()
      load()
    } finally {
      saving.value = false
    }
  })
}

async function publish(row) {
  try {
    await proxy.$modal.confirm('确认发布计划 ' + row.planCode + '？')
  } catch (e) {
    return
  }
  const res = await publishPlan(row.planId)
  proxy.$modal.msgSuccess(res.msg || '计划已发布')
  await loadMeta()
  load()
}

async function batchPublish() {
  if (!selected.value.length) return
  try {
    await proxy.$modal.confirm('确认发布选中的 ' + selected.value.length + ' 条计划？')
  } catch (e) {
    return
  }
  for (const row of selected.value) {
    await publishPlan(row.planId)
  }
  proxy.$modal.msgSuccess('已发布 ' + selected.value.length + ' 条计划')
  await loadMeta()
  load()
}

async function toWorkOrder(row) {
  try {
    await proxy.$modal.confirm('确认为计划 ' + row.planCode + ' 生成生产工单？')
  } catch (e) {
    return
  }
  const res = await planToWorkOrder(row.planId)
  const code = res.data && res.data.orderCode ? '：' + res.data.orderCode : ''
  proxy.$modal.msgSuccess('工单已生成' + code)
  await loadMeta()
  load()
}

function batchWorkOrder() {
  if (selected.value.length !== 1) {
    proxy.$modal.msgWarning('请选择一条计划')
    return
  }
  toWorkOrder(selected.value[0])
}

function handleExport() {
  proxy.download('mes/plan/export', buildQuery(), `plan_${Date.now()}.xlsx`)
}

onMounted(async () => {
  await loadMeta()
  load()
})
</script>

<style scoped>
.page-head { display: flex; align-items: flex-start; justify-content: space-between; margin-bottom: 12px; }
.page-head h2 { margin: 0; font-size: 20px; color: #16242b; }
.page-head .sub { font-size: 12px; color: #8a98a0; }
.head-actions { display: flex; gap: 8px; }
.query-bar { margin-bottom: 4px; }
.panel { border-color: #d3dbe0; }
.panel :deep(.el-card__header) { padding: 10px 14px; font-weight: 600; color: #16242b; background: #f5f7f8; }
.achieve-panel { margin-top: 12px; }
.num { font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.code { display: block; font-size: 11px; color: #8a98a0; font-family: 'Roboto Mono', monospace; }
.option-code { float: right; color: #8a98a0; font-size: 12px; margin-left: 12px; }
</style>
