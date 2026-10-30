<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>线边物料</h2>
        <span class="sub">线边库存 · 安全库存预警 · 缺料拉动 · 库房占用</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="refresh">刷新</el-button>
        <el-button type="primary" icon="Plus" v-hasPermi="['mes:collab:add']" @click="openPull()">新建拉动单</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-row :gutter="12" class="warehouse-row">
      <el-col v-for="w in warehouseCards" :key="w.code" :span="6">
        <div class="wh-card" :class="{ 'is-line': w.code === 'WH-LINE' }">
          <div class="wh-card__head">
            <span class="wh-card__name">{{ w.name }}</span>
            <span class="wh-card__code">{{ w.code }}</span>
          </div>
          <div class="wh-card__amount"><span class="num">{{ money(w.amount) }}</span><em>元</em></div>
          <div class="wh-card__foot">
            <span>物料 <b class="num">{{ w.count }}</b> 项</span>
            <span>数量 <b class="num">{{ fmt(w.qty) }}</b></span>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-card shadow="never" class="panel">
      <template #header>
        <div class="card-header">
          <span>线边库存台账 · WH-LINE</span>
          <span class="hint">低于安全库存行标红</span>
        </div>
      </template>

      <el-form :inline="true" class="query-bar">
        <el-form-item label="关键字">
          <el-input v-model="keyword" placeholder="物料编码 / 名称 / 批次" clearable style="width: 250px" @keyup.enter="handleQuery" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
          <el-button icon="Refresh" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <el-table v-loading="loading" :data="rows" size="small" border :row-class-name="rowClassName">
        <el-table-column prop="materialCode" label="物料编码" width="130" />
        <el-table-column prop="materialName" label="物料名称" min-width="150" show-overflow-tooltip />
        <el-table-column prop="spec" label="规格" width="110" show-overflow-tooltip />
        <el-table-column prop="batchNo" label="批次" width="120" show-overflow-tooltip />
        <el-table-column label="数量" width="100" align="right">
          <template #default="{ row }"><span class="num" :class="{ danger: isLow(row) }">{{ fmt(row.qty) }}</span></template>
        </el-table-column>
        <el-table-column label="安全库存" width="100" align="right">
          <template #default="{ row }"><span class="num">{{ fmt(row.safetyQty) }}</span></template>
        </el-table-column>
        <el-table-column label="金额" width="130" align="right">
          <template #default="{ row }"><span class="num">{{ money(row.amount) }}</span></template>
        </el-table-column>
        <el-table-column prop="location" label="库位" width="110" />
        <el-table-column label="状态" width="90">
          <template #default="{ row }"><MesStatusTag :value="row.status" type="stock" /></template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" v-model:page="page" v-model:limit="size" @pagination="load" />
    </el-card>

    <el-card shadow="never" class="panel pull-panel">
      <template #header><span>线边拉动单</span></template>
      <el-table v-loading="collabLoading" :data="collabs" size="small" border>
        <el-table-column prop="collabCode" label="拉动单号" width="160" />
        <el-table-column label="物料" min-width="170" show-overflow-tooltip>
          <template #default="{ row }">
            <span>{{ row.materialName || '-' }}</span>
            <span class="code">{{ row.materialCode }}</span>
          </template>
        </el-table-column>
        <el-table-column label="数量" width="110" align="right">
          <template #default="{ row }"><span class="num">{{ fmt(row.qty) }}</span></template>
        </el-table-column>
        <el-table-column prop="planDate" label="计划日期" width="120" />
        <el-table-column label="状态" width="100">
          <template #default="{ row }"><MesStatusTag :value="row.status" type="collab" /></template>
        </el-table-column>
        <el-table-column prop="content" label="内容" min-width="240" show-overflow-tooltip />
        <el-table-column prop="handler" label="处理人" width="100" />
      </el-table>
      <el-empty v-if="!collabLoading && !collabs.length" description="暂无线边拉动单" :image-size="60" />
    </el-card>

    <el-dialog v-model="formOpen" title="新建拉动单" width="620px" append-to-body>
      <el-form ref="pullRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="拉动单号" prop="collabCode">
          <el-input v-model="form.collabCode" placeholder="如 PL-261030-001" />
        </el-form-item>
        <el-form-item label="物料" prop="materialCode">
          <el-select v-model="form.materialCode" filterable placeholder="请选择线边物料" style="width: 100%" @change="pickMaterial">
            <el-option v-for="m in materialOptions" :key="m.code" :label="m.name + '（' + m.code + '）'" :value="m.code" />
          </el-select>
        </el-form-item>
        <el-form-item label="物料名称">
          <el-input v-model="form.materialName" placeholder="选择物料后自动带出" />
        </el-form-item>
        <el-form-item label="数量" prop="qty">
          <el-input-number v-model="form.qty" :min="1" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="计划日期">
          <el-date-picker v-model="form.planDate" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
        <el-form-item label="拉动内容">
          <el-input v-model="form.content" type="textarea" :rows="2" />
        </el-form-item>
        <el-form-item label="处理人">
          <el-input v-model="form.handler" placeholder="拣货 / 配送跟进人" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="formOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitPull">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="MesMaterial">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { listStock } from '@/api/mes/stock'
import { listCollab, addCollab } from '@/api/mes/collab'
import { listMaterial } from '@/api/mes/material'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'

const { proxy } = getCurrentInstance()

const warehouses = [
  { code: 'WH-LINE', name: '线边库' },
  { code: 'WH-RM', name: '原材料仓' },
  { code: 'WH-SUB', name: '半成品仓' },
  { code: 'WH-FG', name: '成品仓' }
]

const loading = ref(false)
const collabLoading = ref(false)
const saving = ref(false)
const rows = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(10)
const keyword = ref('')
const allStocks = ref([])
const collabs = ref([])
const materials = ref([])

const formOpen = ref(false)
const form = ref({})
const pullRef = ref()

const rules = {
  collabCode: [{ required: true, message: '请输入拉动单号', trigger: 'blur' }],
  materialCode: [{ required: true, message: '请选择物料', trigger: 'change' }],
  qty: [{ required: true, message: '请输入数量', trigger: 'blur' }]
}

const num = v => (v === null || v === undefined ? 0 : Number(v))
const fmt = v => num(v).toLocaleString('zh-CN', { maximumFractionDigits: 2 })
const money = v => num(v).toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 })

function isLow(row) {
  return num(row.qty) < num(row.safetyQty)
}

function rowClassName({ row }) {
  return isLow(row) ? 'row-danger' : ''
}

const lineStocks = computed(() => allStocks.value.filter(i => i.warehouseCode === 'WH-LINE'))

const kpis = computed(() => {
  const line = lineStocks.value
  const amount = line.reduce((s, i) => s + num(i.amount), 0)
  const low = line.filter(i => isLow(i)).length
  const shortage = line.filter(i => i.status === 'shortage').length
  return [
    { label: '线边库存金额', value: (amount / 10000).toFixed(1), unit: '万元', foot: '线边库实时库存合计', tone: '' },
    { label: '低于安全库存', value: low, unit: '项', foot: '需补货 / 拉动', tone: low > 0 ? 'warn' : 'ok' },
    { label: '缺料数', value: shortage, unit: '项', foot: '影响产线齐套', tone: shortage > 0 ? 'danger' : 'ok' },
    { label: '拉动单数', value: collabs.value.length, unit: '单', foot: '线边物料协同拉动', tone: 'accent' }
  ]
})

const warehouseCards = computed(() => warehouses.map(w => {
  const list = allStocks.value.filter(i => i.warehouseCode === w.code)
  return {
    ...w,
    count: list.length,
    qty: list.reduce((s, i) => s + num(i.qty), 0),
    amount: list.reduce((s, i) => s + num(i.amount), 0)
  }
}))

const materialOptions = computed(() => {
  const map = {}
  allStocks.value.filter(i => i.warehouseCode === 'WH-LINE').forEach(i => {
    if (i.materialCode) map[i.materialCode] = { code: i.materialCode, name: i.materialName }
  })
  materials.value.forEach(m => {
    if (m.materialCode && !map[m.materialCode]) map[m.materialCode] = { code: m.materialCode, name: m.materialName }
  })
  return Object.values(map)
})

function pad(n) {
  return String(n).padStart(2, '0')
}

function today() {
  const d = new Date()
  return d.getFullYear() + '-' + pad(d.getMonth() + 1) + '-' + pad(d.getDate())
}

function genCode() {
  const d = new Date()
  const ymd = String(d.getFullYear()).slice(2) + pad(d.getMonth() + 1) + pad(d.getDate())
  return 'PL-' + ymd + '-' + String(Math.floor(Math.random() * 900) + 100)
}

function buildQuery() {
  const q = { pageNum: page.value, pageSize: size.value, warehouseCode: 'WH-LINE' }
  if (keyword.value) q.params = { keyword: keyword.value }
  return q
}

async function load() {
  loading.value = true
  try {
    const res = await listStock(buildQuery())
    rows.value = res.rows || []
    total.value = res.total || 0
  } finally {
    loading.value = false
  }
}

async function loadAllStocks() {
  const res = await listStock({ pageNum: 1, pageSize: 500 })
  allStocks.value = res.rows || []
}

async function loadCollabs() {
  collabLoading.value = true
  try {
    const res = await listCollab({ pageNum: 1, pageSize: 100, collabType: 'material' })
    collabs.value = res.rows || []
  } finally {
    collabLoading.value = false
  }
}

async function loadMaterials() {
  const res = await listMaterial({ pageNum: 1, pageSize: 200 })
  materials.value = res.rows || []
}

async function refresh() {
  await loadAllStocks()
  await load()
  await loadCollabs()
}

function handleQuery() {
  page.value = 1
  load()
}

function resetQuery() {
  keyword.value = ''
  page.value = 1
  load()
}

function openPull() {
  form.value = {
    collabCode: genCode(),
    collabType: 'material',
    supplierName: '线边库',
    materialCode: '',
    materialName: '',
    qty: 100,
    planDate: today(),
    content: '线边拉动：原材料仓 → 线边库',
    handler: ''
  }
  formOpen.value = true
}

function pickMaterial(code) {
  const hit = materialOptions.value.find(m => m.code === code)
  if (hit) form.value.materialName = hit.name
}

function submitPull() {
  proxy.$refs.pullRef.validate(async valid => {
    if (!valid) return
    saving.value = true
    try {
      await addCollab({ ...form.value, collabType: 'material', status: 'pending' })
      proxy.$modal.msgSuccess('拉动单已创建')
      formOpen.value = false
      await loadCollabs()
    } finally {
      saving.value = false
    }
  })
}

onMounted(async () => {
  await loadMaterials()
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
.card-header { display: flex; justify-content: space-between; align-items: center; }
.hint { font-size: 12px; color: #8a98a0; font-weight: 400; }
.warehouse-row { margin-bottom: 12px; }
.wh-card { background: #fff; border: 1px solid #d3dbe0; border-top: 3px solid #12495f; border-radius: 6px; padding: 10px 14px; }
.wh-card.is-line { border-top-color: #e07b1a; background: #fffdf9; }
.wh-card__head { display: flex; justify-content: space-between; align-items: baseline; }
.wh-card__name { font-size: 14px; font-weight: 600; color: #16242b; }
.wh-card__code { font-size: 11px; color: #8a98a0; font-family: 'Roboto Mono', monospace; }
.wh-card__amount { margin: 6px 0; font-size: 22px; font-weight: 600; color: #12495f; }
.wh-card__amount .num { font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.wh-card__amount em { font-size: 11px; font-style: normal; color: #8a98a0; margin-left: 4px; }
.wh-card__foot { display: flex; justify-content: space-between; font-size: 12px; color: #5b6b74; }
.wh-card__foot b { color: #16242b; font-weight: 600; }
.query-bar { margin-bottom: 4px; }
.pull-panel { margin-top: 12px; }
.num { font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.code { display: block; font-size: 11px; color: #8a98a0; font-family: 'Roboto Mono', monospace; }
.danger { color: #b23a2e; }
:deep(.el-table .row-danger) { --el-table-tr-bg-color: #fdf3f1; }
</style>
