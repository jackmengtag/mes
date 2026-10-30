<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>协同管理</h2>
        <span class="sub">供应商交付计划 · 送货跟踪 · 样品送检 · 异常与绩效</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="refresh">刷新</el-button>
        <el-button type="primary" icon="Plus" v-hasPermi="['mes:collab:add']" @click="openForm()">新增协同单</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-tabs v-model="activeTab" @tab-change="onTabChange">
      <el-tab-pane v-for="t in tabs" :key="t.value" :label="t.label" :name="t.value" />
    </el-tabs>

    <el-form :inline="true" class="query-bar">
      <el-form-item label="关键字">
        <el-input v-model="keyword" placeholder="协同单号 / 供应商 / 物料" clearable style="width: 240px" @keyup.enter="handleQuery" />
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

    <el-table v-loading="loading" :data="rows" size="small" border>
      <el-table-column prop="collabCode" label="协同单号" width="150" />
      <el-table-column prop="supplierName" label="供应商" min-width="180" show-overflow-tooltip />
      <el-table-column label="物料" min-width="160" show-overflow-tooltip>
        <template #default="{ row }">
          <span>{{ row.materialName || '-' }}</span>
          <span class="code">{{ row.materialCode }}</span>
        </template>
      </el-table-column>
      <el-table-column label="数量" width="110" align="right">
        <template #default="{ row }"><span class="num">{{ fmt(row.qty) }}</span></template>
      </el-table-column>
      <el-table-column prop="planDate" label="计划日期" width="110" />
      <el-table-column label="状态" width="100">
        <template #default="{ row }"><MesStatusTag :value="row.status" type="collab" /></template>
      </el-table-column>
      <el-table-column prop="content" label="内容" min-width="220" show-overflow-tooltip />
      <el-table-column prop="handler" label="处理人" width="90" />
      <el-table-column v-if="activeTab === 'exception'" label="操作" width="140" fixed="right">
        <template #default="{ row }">
          <el-button link type="primary" v-hasPermi="['mes:quality:add']" @click="toQuality(row)">生成质量问题</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="page" v-model:limit="size" @pagination="load" />

    <el-card v-if="activeTab === 'exception'" shadow="never" class="panel perf-panel">
      <template #header><span>供应商绩效</span></template>
      <el-table :data="suppliers" size="small" border max-height="360">
        <el-table-column prop="supplierName" label="供应商名称" min-width="200" show-overflow-tooltip />
        <el-table-column prop="level" label="等级" width="90" />
        <el-table-column label="准时率" width="120" align="right">
          <template #default="{ row }"><span class="num">{{ num(row.onTimeRate).toFixed(1) }}%</span></template>
        </el-table-column>
        <el-table-column label="综合评分" width="120" align="right">
          <template #default="{ row }"><span class="num">{{ num(row.score).toFixed(1) }}</span></template>
        </el-table-column>
        <el-table-column label="状态" width="110">
          <template #default="{ row }">
            <el-tag :type="supplierTone(row.status)" size="small">{{ supplierText(row.status) }}</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="formOpen" title="新增协同单" width="640px" append-to-body>
      <el-form ref="collabFormRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="协同单号" prop="collabCode">
          <el-input v-model="form.collabCode" placeholder="如 DP-261030-001" />
        </el-form-item>
        <el-form-item label="协同类型" prop="collabType">
          <el-select v-model="form.collabType" style="width: 100%">
            <el-option v-for="t in tabs" :key="t.value" :label="t.label" :value="t.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="供应商">
          <el-select v-model="form.supplierName" filterable clearable placeholder="请选择供应商" style="width: 100%" @change="pickSupplier">
            <el-option v-for="s in suppliers" :key="s.supplierId" :label="s.supplierName" :value="s.supplierName" />
          </el-select>
        </el-form-item>
        <el-form-item label="物料" prop="materialCode">
          <el-select v-model="form.materialCode" filterable placeholder="请选择物料" style="width: 100%" @change="pickMaterial">
            <el-option v-for="m in materials" :key="m.materialId" :label="m.materialName + '（' + m.materialCode + '）'" :value="m.materialCode" />
          </el-select>
        </el-form-item>
        <el-form-item label="数量" prop="qty">
          <el-input-number v-model="form.qty" :min="1" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="计划日期" prop="planDate">
          <el-date-picker v-model="form.planDate" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
        <el-form-item label="内容">
          <el-input v-model="form.content" type="textarea" :rows="2" placeholder="交付说明 / 送货信息 / 异常描述" />
        </el-form-item>
        <el-form-item label="处理人">
          <el-input v-model="form.handler" placeholder="跟进人" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="formOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitForm">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="MesCollab">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { listCollab, addCollab } from '@/api/mes/collab'
import { listSupplier } from '@/api/mes/supplier'
import { listMaterial } from '@/api/mes/material'
import { collabToQuality } from '@/api/mes/biz'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'

const { proxy } = getCurrentInstance()

const tabs = [
  { value: 'delivery', label: '交付计划' },
  { value: 'shipping', label: '送货跟踪' },
  { value: 'sample', label: '样品送检' },
  { value: 'exception', label: '异常与绩效' }
]
const prefixMap = { delivery: 'DP', shipping: 'SH', sample: 'SA', exception: 'EX', material: 'WL' }
const statusOptions = [
  { value: 'pending', label: '待确认' },
  { value: 'running', label: '执行中' },
  { value: 'finished', label: '已完成' },
  { value: 'overdue', label: '已逾期' },
  { value: 'abnormal', label: '异常' }
]

const activeTab = ref('delivery')
const loading = ref(false)
const saving = ref(false)
const rows = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(10)
const keyword = ref('')
const status = ref('')
const allCollabs = ref([])
const suppliers = ref([])
const materials = ref([])

const formOpen = ref(false)
const form = ref({})
const collabFormRef = ref()

const rules = {
  collabCode: [{ required: true, message: '请输入协同单号', trigger: 'blur' }],
  collabType: [{ required: true, message: '请选择协同类型', trigger: 'change' }],
  materialCode: [{ required: true, message: '请选择物料', trigger: 'change' }],
  qty: [{ required: true, message: '请输入数量', trigger: 'blur' }],
  planDate: [{ required: true, message: '请选择计划日期', trigger: 'change' }]
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

function genCode(type) {
  const d = new Date()
  const ymd = String(d.getFullYear()).slice(2) + pad(d.getMonth() + 1) + pad(d.getDate())
  return (prefixMap[type] || 'CO') + '-' + ymd + '-' + String(Math.floor(Math.random() * 900) + 100)
}

const kpis = computed(() => {
  const list = allCollabs.value
  const avgRate = suppliers.value.length
    ? suppliers.value.reduce((s, i) => s + num(i.onTimeRate), 0) / suppliers.value.length
    : 0
  return [
    { label: '待确认协同', value: list.filter(i => i.status === 'pending').length, unit: '单', foot: '等待供应商确认', tone: 'warn' },
    { label: '执行中', value: list.filter(i => i.status === 'running').length, unit: '单', foot: '交付 / 送货进行中', tone: 'accent' },
    { label: '逾期 / 异常', value: list.filter(i => ['overdue', 'abnormal'].includes(i.status)).length, unit: '单', foot: '需跟催处理', tone: 'danger' },
    { label: '供应商平均准时率', value: avgRate.toFixed(1), unit: '%', foot: '共 ' + suppliers.value.length + ' 家供应商', tone: avgRate >= 95 ? 'ok' : 'warn' }
  ]
})

function buildQuery() {
  const q = { pageNum: page.value, pageSize: size.value, collabType: activeTab.value }
  if (keyword.value) q.params = { keyword: keyword.value }
  if (status.value) q.status = status.value
  return q
}

async function load() {
  loading.value = true
  try {
    const res = await listCollab(buildQuery())
    rows.value = res.rows || []
    total.value = res.total || 0
  } finally {
    loading.value = false
  }
}

async function loadMeta() {
  const c = await listCollab({ pageNum: 1, pageSize: 500 })
  allCollabs.value = c.rows || []
  const s = await listSupplier({ pageNum: 1, pageSize: 200 })
  suppliers.value = s.rows || []
  const m = await listMaterial({ pageNum: 1, pageSize: 500 })
  materials.value = m.rows || []
}

function refresh() {
  loadMeta()
  load()
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

function openForm() {
  form.value = {
    collabCode: genCode(activeTab.value),
    collabType: activeTab.value,
    supplierCode: '',
    supplierName: '',
    materialCode: '',
    materialName: '',
    qty: 100,
    planDate: today(),
    content: '',
    handler: ''
  }
  formOpen.value = true
}

function pickSupplier(name) {
  const hit = suppliers.value.find(i => i.supplierName === name)
  form.value.supplierCode = hit ? hit.supplierCode : ''
}

function pickMaterial(code) {
  const hit = materials.value.find(i => i.materialCode === code)
  form.value.materialName = hit ? hit.materialName : ''
}

function submitForm() {
  collabFormRef.value.validate(async valid => {
    if (!valid) return
    saving.value = true
    try {
      await addCollab({ ...form.value, status: 'pending' })
      proxy.$modal.msgSuccess('协同单已创建')
      formOpen.value = false
      await loadMeta()
      load()
    } finally {
      saving.value = false
    }
  })
}

async function toQuality(row) {
  try {
    await proxy.$modal.confirm('确认将协同单 ' + row.collabCode + ' 转为质量问题？')
  } catch (e) {
    return
  }
  const res = await collabToQuality(row.collabId)
  proxy.$modal.msgSuccess(res.msg || '已生成质量问题')
  load()
}

function supplierText(status) {
  return { '0': '待审核', '1': '合作中', '2': '停用' }[status] || status || '-'
}

function supplierTone(status) {
  return { '0': 'warning', '1': 'success', '2': 'info' }[status] || 'info'
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
.perf-panel { margin-top: 12px; }
.num { font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.code { display: block; font-size: 11px; color: #8a98a0; font-family: 'Roboto Mono', monospace; }
</style>
