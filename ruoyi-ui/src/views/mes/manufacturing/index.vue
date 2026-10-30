<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>生产制造</h2>
        <span class="sub">计划 → 指令 → 排产 → 日计划 → 投料 → 报工 → 检验 → 入库</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="refresh">刷新</el-button>
        <el-button icon="Download" v-hasPermi="['mes:workOrder:export']" @click="handleExport">导出</el-button>
        <el-button type="primary" icon="Plus" v-hasPermi="['mes:workOrder:add']" @click="openForm()">新增工单</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-card shadow="never" class="panel">
      <template #header><span>制造流程 · 工单执行进度</span></template>
      <el-steps :active="activeStep" finish-status="success" process-status="process" align-center>
        <el-step v-for="step in flowSteps" :key="step" :title="step" />
      </el-steps>
    </el-card>

    <el-tabs v-model="activeStatus" class="status-tabs" @tab-change="onTabChange">
      <el-tab-pane v-for="t in statusTabs" :key="t.value" :label="t.label" :name="t.value" />
    </el-tabs>

    <el-form :inline="true" class="query-bar">
      <el-form-item label="关键字">
        <el-input v-model="keyword" placeholder="工单号 / 产品编码 / 产品名称" clearable style="width: 260px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="rows" size="small" border>
      <el-table-column prop="orderCode" label="工单号" width="150" />
      <el-table-column label="产品" min-width="170" show-overflow-tooltip>
        <template #default="{ row }">
          <span>{{ row.productName || '-' }}</span>
          <span class="code">{{ row.productCode }}</span>
        </template>
      </el-table-column>
      <el-table-column label="计划 / 完成 / 不良" width="170" align="right">
        <template #default="{ row }">
          <span class="num">{{ fmt(row.qty) }} / {{ fmt(row.completedQty) }} / {{ fmt(row.ngQty) }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="lineCode" label="产线" width="90" />
      <el-table-column label="当前工位" width="130" show-overflow-tooltip>
        <template #default="{ row }">{{ row.station || '-' }}</template>
      </el-table-column>
      <el-table-column label="进度" width="150">
        <template #default="{ row }">
          <el-progress :percentage="progress(row)" :stroke-width="8" :status="progress(row) >= 100 ? 'success' : ''" />
        </template>
      </el-table-column>
      <el-table-column label="状态" width="100">
        <template #default="{ row }"><MesStatusTag :value="row.status" type="workOrder" /></template>
      </el-table-column>
      <el-table-column label="计划日期" width="190">
        <template #default="{ row }">{{ row.planStart || '-' }} ~ {{ row.planEnd || '-' }}</template>
      </el-table-column>
      <el-table-column label="操作" width="170" fixed="right">
        <template #default="{ row }">
          <el-button v-if="row.status === 'scheduled'" link type="primary" v-hasPermi="['mes:workOrder:edit']" @click="release(row)">下达</el-button>
          <el-button v-if="row.status === 'running'" link type="success" v-hasPermi="['mes:workOrder:edit']" @click="finish(row)">完工</el-button>
          <el-button link type="primary" v-hasPermi="['mes:workOrder:edit']" @click="openForm(row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="page" v-model:limit="size" @pagination="load" />

    <el-dialog v-model="formOpen" :title="form.workOrderId ? '编辑工单' : '新增工单'" width="640px" append-to-body>
      <el-form ref="orderRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="工单号" prop="orderCode">
          <el-input v-model="form.orderCode" placeholder="如 WO-261030-001" />
        </el-form-item>
        <el-form-item label="来源计划">
          <el-select v-model="form.planCode" filterable clearable placeholder="请选择生产计划" style="width: 100%" @change="pickPlan">
            <el-option v-for="p in plans" :key="p.planId" :label="p.planCode + ' · ' + (p.productName || '')" :value="p.planCode" />
          </el-select>
        </el-form-item>
        <el-form-item label="产品" prop="productCode">
          <el-select v-model="form.productCode" filterable placeholder="请选择产品" style="width: 100%" @change="pickProduct">
            <el-option v-for="m in products" :key="m.materialId" :label="m.materialName + '（' + m.materialCode + '）'" :value="m.materialCode" />
          </el-select>
        </el-form-item>
        <el-form-item label="产品名称">
          <el-input v-model="form.productName" placeholder="选择产品后自动带出" />
        </el-form-item>
        <el-form-item label="计划数量" prop="qty">
          <el-input-number v-model="form.qty" :min="1" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="产线" prop="lineCode">
          <el-select v-model="form.lineCode" placeholder="请选择产线" style="width: 100%">
            <el-option v-for="l in lines" :key="l" :label="l" :value="l" />
          </el-select>
        </el-form-item>
        <el-form-item label="优先级">
          <el-select v-model="form.priority" style="width: 100%">
            <el-option label="普通" value="normal" />
            <el-option label="加急" value="urgent" />
          </el-select>
        </el-form-item>
        <el-form-item label="计划开始">
          <el-date-picker v-model="form.planStart" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
        <el-form-item label="计划结束">
          <el-date-picker v-model="form.planEnd" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="formOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitForm">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="MesManufacturing">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { listWorkOrder, addWorkOrder, updateWorkOrder } from '@/api/mes/workOrder'
import { listPlan } from '@/api/mes/plan'
import { listMaterial } from '@/api/mes/material'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'

const { proxy } = getCurrentInstance()

const lines = ['MC-01', 'MC-02', 'AS-01', 'AS-02', 'TS-01', 'PK-01']
const flowSteps = ['计划', '指令', '排产', '日计划', '投料', '报工', '检验', '入库']
const statusTabs = [
  { value: 'all', label: '全部' },
  { value: 'scheduled', label: '已排程' },
  { value: 'running', label: '生产中' },
  { value: 'waiting', label: '待料' },
  { value: 'finished', label: '已完成' }
]

const loading = ref(false)
const saving = ref(false)
const rows = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(10)
const keyword = ref('')
const activeStatus = ref('all')
const allOrders = ref([])
const plans = ref([])
const products = ref([])

const formOpen = ref(false)
const form = ref({})
const orderRef = ref()

const rules = {
  orderCode: [{ required: true, message: '请输入工单号', trigger: 'blur' }],
  productCode: [{ required: true, message: '请选择产品', trigger: 'change' }],
  qty: [{ required: true, message: '请输入计划数量', trigger: 'blur' }],
  lineCode: [{ required: true, message: '请选择产线', trigger: 'change' }]
}

const num = v => (v === null || v === undefined ? 0 : Number(v))
const fmt = v => num(v).toLocaleString('zh-CN', { maximumFractionDigits: 2 })

function pad(n) {
  return String(n).padStart(2, '0')
}

function today() {
  const d = new Date()
  return d.getFullYear() + '-' + pad(d.getMonth() + 1) + '-' + pad(d.getDate())
}

function genOrderCode() {
  const d = new Date()
  const ymd = String(d.getFullYear()).slice(2) + pad(d.getMonth() + 1) + pad(d.getDate())
  return 'WO-' + ymd + '-' + String(Math.floor(Math.random() * 900) + 100)
}

function progress(row) {
  const qty = num(row.qty)
  return qty ? Math.min(Math.round((num(row.completedQty) / qty) * 100), 100) : 0
}

const kpis = computed(() => {
  const list = allOrders.value
  const planQty = list.reduce((s, i) => s + num(i.qty), 0)
  const doneQty = list.reduce((s, i) => s + num(i.completedQty), 0)
  const running = list.filter(i => i.status === 'running').length
  const finished = list.filter(i => i.status === 'finished').length
  const rate = planQty ? ((doneQty / planQty) * 100).toFixed(1) : '0.0'
  return [
    { label: '计划工单数', value: list.length, unit: '单', foot: '计划总量 ' + fmt(planQty), tone: '' },
    { label: '生产中', value: running, unit: '单', foot: '车间执行中', tone: 'accent' },
    { label: '已完工', value: finished, unit: '单', foot: '累计完成 ' + fmt(doneQty), tone: 'ok' },
    { label: '平均完成率', value: rate, unit: '%', foot: '完成数量 / 计划数量', tone: Number(rate) >= 90 ? 'ok' : 'warn' }
  ]
})

const activeStep = computed(() => {
  const list = allOrders.value
  if (!list.length) return 0
  let step = 4
  if (list.some(i => ['running', 'finished'].includes(i.status) || num(i.completedQty) > 0)) step = 6
  if (list.some(i => i.status === 'finished')) step = 7
  if (list.every(i => i.status === 'finished')) step = 8
  return step
})

function buildQuery() {
  const q = { pageNum: page.value, pageSize: size.value }
  if (keyword.value) q.params = { keyword: keyword.value }
  if (activeStatus.value !== 'all') q.status = activeStatus.value
  return q
}

async function load() {
  loading.value = true
  try {
    const res = await listWorkOrder(buildQuery())
    rows.value = res.rows || []
    total.value = res.total || 0
  } finally {
    loading.value = false
  }
}

async function loadAll() {
  const res = await listWorkOrder({ pageNum: 1, pageSize: 500 })
  allOrders.value = res.rows || []
}

async function loadMeta() {
  const [p, m] = await Promise.all([
    listPlan({ pageNum: 1, pageSize: 200 }),
    listMaterial({ pageNum: 1, pageSize: 200 })
  ])
  plans.value = p.rows || []
  products.value = (m.rows || []).filter(i => i.productFlag === '1')
}

async function refresh() {
  await loadAll()
  await load()
}

function onTabChange() {
  page.value = 1
  load()
}

function handleQuery() {
  page.value = 1
  load()
}

function resetQuery() {
  keyword.value = ''
  activeStatus.value = 'all'
  page.value = 1
  load()
}

function openForm(row) {
  form.value = row
    ? { ...row }
    : {
        orderCode: genOrderCode(),
        planCode: '',
        productCode: '',
        productName: '',
        qty: 100,
        lineCode: 'AS-01',
        priority: 'normal',
        planStart: today(),
        planEnd: today()
      }
  formOpen.value = true
}

function pickPlan(code) {
  const hit = plans.value.find(p => p.planCode === code)
  if (!hit) return
  form.value.productCode = hit.productCode
  form.value.productName = hit.productName
  form.value.qty = Number(hit.qty) || form.value.qty
  form.value.lineCode = hit.lineCode || form.value.lineCode
}

function pickProduct(code) {
  const hit = products.value.find(m => m.materialCode === code)
  if (hit) form.value.productName = hit.materialName
}

function submitForm() {
  proxy.$refs.orderRef.validate(async valid => {
    if (!valid) return
    saving.value = true
    try {
      if (form.value.workOrderId) {
        await updateWorkOrder({ ...form.value })
        proxy.$modal.msgSuccess('工单已更新')
      } else {
        await addWorkOrder({ ...form.value, status: 'scheduled', completedQty: 0, ngQty: 0 })
        proxy.$modal.msgSuccess('工单已创建')
      }
      formOpen.value = false
      await loadAll()
      await load()
    } finally {
      saving.value = false
    }
  })
}

async function release(row) {
  await updateWorkOrder({ ...row, status: 'running' })
  proxy.$modal.msgSuccess('工单已下达，状态更新为生产中')
  await refresh()
}

async function finish(row) {
  try {
    await proxy.$modal.confirm('确认将工单 ' + row.orderCode + ' 标记为已完工？')
  } catch (e) {
    return
  }
  await updateWorkOrder({ ...row, status: 'finished' })
  proxy.$modal.msgSuccess('工单已完工')
  await refresh()
}

function handleExport() {
  proxy.download('mes/workOrder/export', { ...buildQuery() }, `workOrder_${Date.now()}.xlsx`)
}

onMounted(async () => {
  await loadMeta()
  await refresh()
})
</script>

<style scoped>
.page-head { display: flex; align-items: flex-start; justify-content: space-between; margin-bottom: 12px; }
.page-head h2 { margin: 0; font-size: 20px; color: #16242b; }
.page-head .sub { font-size: 12px; color: #8a98a0; }
.head-actions { display: flex; gap: 8px; flex-wrap: wrap; justify-content: flex-end; }
.panel { border-color: #d3dbe0; }
.panel :deep(.el-card__header) { padding: 10px 14px; font-weight: 600; color: #16242b; background: #f5f7f8; }
.status-tabs { margin-top: 6px; }
.query-bar { margin-bottom: 4px; }
.num { font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.code { display: block; font-size: 11px; color: #8a98a0; font-family: 'Roboto Mono', monospace; }
</style>
