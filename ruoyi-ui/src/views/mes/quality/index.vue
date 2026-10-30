<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>质量管理</h2>
        <span class="sub">检验任务 · 质量问题 · 不合格品处置 · 缺陷 Pareto</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="refresh">刷新</el-button>
        <el-button type="primary" icon="Plus" v-hasPermi="['mes:quality:add']" @click="openInspection()">新建检验单</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-card shadow="never" class="panel">
      <template #header><span>不合格处置流程 · 发现 → 隔离 → 评审 → 处置 → 放行</span></template>
      <el-steps :active="disposeActive" finish-status="success" process-status="process" align-center>
        <el-step title="发现" description="检验 / 报工触发" />
        <el-step title="隔离" description="锁定批次与序列号" />
        <el-step title="评审" description="质量与工艺会签" />
        <el-step title="处置" description="返工 / 让步 / 报废" />
        <el-step title="放行" description="更新台账并归档" />
      </el-steps>
    </el-card>

    <el-tabs v-model="activeTab" class="status-tabs" @tab-change="onTabChange">
      <el-tab-pane v-for="t in tabs" :key="t.value" :label="t.label" :name="t.value" />
    </el-tabs>

    <el-card v-if="activeTab === 'issue'" shadow="never" class="panel pareto-panel">
      <template #header>
        <div class="card-header">
          <span>缺陷 Pareto · 按问题类型统计不良数量</span>
          <span class="hint">数据口径：最近 {{ allIssues.length }} 条质量问题</span>
        </div>
      </template>
      <MesChart :option="paretoOption" :height="220" />
    </el-card>

    <el-form :inline="true" class="query-bar">
      <el-form-item label="关键字">
        <el-input v-model="keyword" placeholder="单号 / 工单 / 产品 / 批次 / 序列号" clearable style="width: 280px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item v-if="activeTab === 'inspection'" label="检验类型">
        <el-select v-model="inspectionType" clearable placeholder="全部" style="width: 140px" @change="handleQuery">
          <el-option v-for="item in inspectionTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="rows" size="small" border>
      <template v-if="activeTab === 'inspection'">
        <el-table-column prop="inspectionCode" label="检验单号" width="150" />
        <el-table-column label="类型" width="90">
          <template #default="{ row }">{{ typeText(row.inspectionType) }}</template>
        </el-table-column>
        <el-table-column prop="orderCode" label="工单号" width="150" />
        <el-table-column prop="productCode" label="产品" min-width="130" show-overflow-tooltip />
        <el-table-column prop="batchNo" label="批次" width="120" show-overflow-tooltip />
        <el-table-column label="抽样 / 不良" width="110" align="right">
          <template #default="{ row }">
            <span class="num">{{ fmt(row.sampleQty) }} / {{ fmt(row.ngQty) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="结果" width="110">
          <template #default="{ row }"><MesStatusTag :value="row.result" type="common" /></template>
        </el-table-column>
        <el-table-column prop="inspector" label="检验员" width="90" />
        <el-table-column prop="inspectTime" label="检验时间" width="160" />
        <el-table-column label="操作" width="100" fixed="right">
          <template #default="{ row }">
            <el-button link type="primary" v-hasPermi="['mes:inspection:edit']" @click="openJudge(row)">判定</el-button>
          </template>
        </el-table-column>
      </template>

      <template v-else>
        <el-table-column prop="issueCode" label="问题单号" width="150" />
        <el-table-column label="来源" width="90">
          <template #default="{ row }">{{ sourceText(row.sourceType) }}</template>
        </el-table-column>
        <el-table-column prop="productCode" label="产品" width="130" show-overflow-tooltip />
        <el-table-column label="批次 / 序列号" min-width="170" show-overflow-tooltip>
          <template #default="{ row }">{{ row.batchNo || '-' }} / {{ row.serialNo || '-' }}</template>
        </el-table-column>
        <el-table-column prop="issueType" label="问题类型" width="120" show-overflow-tooltip />
        <el-table-column label="严重度" width="90">
          <template #default="{ row }">
            <el-tag :type="severityTone(row.severity)" size="small" effect="light">{{ severityText(row.severity) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="数量" width="90" align="right">
          <template #default="{ row }"><span class="num">{{ fmt(row.qty) }}</span></template>
        </el-table-column>
        <el-table-column label="状态" width="100">
          <template #default="{ row }"><MesStatusTag :value="row.status" type="quality" /></template>
        </el-table-column>
        <el-table-column prop="handler" label="处理人" width="90" />
        <el-table-column prop="foundTime" label="发现时间" width="160" />
        <el-table-column label="操作" width="100" fixed="right">
          <template #default="{ row }">
            <el-button v-if="row.status !== 'released'" link type="primary" v-hasPermi="['mes:quality:dispose']" @click="openDispose(row)">处置</el-button>
            <span v-else class="muted">已放行</span>
          </template>
        </el-table-column>
      </template>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="page" v-model:limit="size" @pagination="load" />

    <!-- 检验判定 -->
    <el-dialog v-model="judgeOpen" title="检验判定" width="480px" append-to-body>
      <el-form :model="judgeForm" label-width="90px">
        <el-form-item label="检验单号">
          <el-input v-model="judgeForm.inspectionCode" disabled />
        </el-form-item>
        <el-form-item label="工单号">
          <el-input v-model="judgeForm.orderCode" disabled />
        </el-form-item>
        <el-form-item label="抽样 / 不良">
          <span class="num">{{ fmt(judgeForm.sampleQty) }} / {{ fmt(judgeForm.ngQty) }}</span>
        </el-form-item>
        <el-form-item label="判定结果">
          <el-radio-group v-model="judgeForm.result">
            <el-radio-button label="pass">合格</el-radio-button>
            <el-radio-button label="fail">不合格</el-radio-button>
            <el-radio-button label="concession">让步接收</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-alert type="info" :closable="false" show-icon title="判定为不合格时，请同步确认是否已在质量问题中登记并进入处置流程。" />
      </el-form>
      <template #footer>
        <el-button @click="judgeOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitJudge">确定</el-button>
      </template>
    </el-dialog>

    <!-- 新建检验单 -->
    <el-dialog v-model="inspectionOpen" title="新建检验单" width="600px" append-to-body>
      <el-form ref="inspectionRef" :model="inspectionForm" :rules="inspectionRules" label-width="100px">
        <el-form-item label="检验单号" prop="inspectionCode">
          <el-input v-model="inspectionForm.inspectionCode" placeholder="如 IQ-261030-001" />
        </el-form-item>
        <el-form-item label="检验类型" prop="inspectionType">
          <el-select v-model="inspectionForm.inspectionType" style="width: 100%">
            <el-option v-for="item in inspectionTypeOptions" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
        </el-form-item>
        <el-form-item label="工单号" prop="orderCode">
          <el-select v-model="inspectionForm.orderCode" filterable placeholder="请选择工单" style="width: 100%" @change="pickOrder">
            <el-option v-for="w in workOrders" :key="w.workOrderId" :label="w.orderCode + ' · ' + (w.productName || '')" :value="w.orderCode" />
          </el-select>
        </el-form-item>
        <el-form-item label="产品编码" prop="productCode">
          <el-input v-model="inspectionForm.productCode" placeholder="选择工单后自动带出" />
        </el-form-item>
        <el-form-item label="批次号">
          <el-input v-model="inspectionForm.batchNo" placeholder="如 B261030-01" />
        </el-form-item>
        <el-form-item label="抽样数" prop="sampleQty">
          <el-input-number v-model="inspectionForm.sampleQty" :min="1" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="不良数">
          <el-input-number v-model="inspectionForm.ngQty" :min="0" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="检验员">
          <el-input v-model="inspectionForm.inspector" placeholder="检验员姓名" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="inspectionOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitInspection">提交</el-button>
      </template>
    </el-dialog>

    <!-- 不合格品处置 -->
    <el-dialog v-model="disposeOpen" title="不合格品处置" width="500px" append-to-body>
      <el-form :model="disposeForm" label-width="90px">
        <el-form-item label="问题单号">
          <el-input v-model="disposeForm.issueCode" disabled />
        </el-form-item>
        <el-form-item label="处置方式">
          <el-radio-group v-model="disposeForm.dispose">
            <el-radio label="rework">返工</el-radio>
            <el-radio label="concession">让步接收</el-radio>
            <el-radio label="scrap">报废</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-alert type="warning" :closable="false" show-icon title="让步接收将直接放行；返工 / 报废后进入已处置状态，需后续复检确认。" />
      </el-form>
      <template #footer>
        <el-button @click="disposeOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitDispose">确认处置</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="MesQuality">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import { listInspection, addInspection, updateInspection } from '@/api/mes/inspection'
import { listQualityIssue } from '@/api/mes/quality'
import { listWorkOrder } from '@/api/mes/workOrder'
import { disposeIssue } from '@/api/mes/biz'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'
import MesChart from '../components/MesChart.vue'

const { proxy } = getCurrentInstance()
const route = useRoute()

const tabs = [
  { value: 'inspection', label: '检验任务' },
  { value: 'issue', label: '质量问题' },
  { value: 'ledger', label: '不合格品台账' }
]
const inspectionTypeOptions = [
  { value: 'first', label: '首检' },
  { value: 'patrol', label: '巡检' },
  { value: 'final', label: '终检' }
]
const typeMap = { first: '首检', patrol: '巡检', final: '终检' }
const sourceMap = { report: '报工', incoming: '来料', process: '过程' }
const severityMap = {
  serious: { label: '严重', type: 'danger' },
  normal: { label: '一般', type: 'warning' },
  info: { label: '提示', type: 'info' }
}

const activeTab = ref('inspection')
const loading = ref(false)
const saving = ref(false)
const rows = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(10)
const keyword = ref('')
const inspectionType = ref('')
const allInspections = ref([])
const allIssues = ref([])
const workOrders = ref([])

const judgeOpen = ref(false)
const judgeForm = ref({})
const inspectionOpen = ref(false)
const inspectionForm = ref({})
const inspectionRef = ref()
const disposeOpen = ref(false)
const disposeForm = ref({})

const inspectionRules = {
  inspectionCode: [{ required: true, message: '请输入检验单号', trigger: 'blur' }],
  inspectionType: [{ required: true, message: '请选择检验类型', trigger: 'change' }],
  orderCode: [{ required: true, message: '请选择工单', trigger: 'change' }],
  productCode: [{ required: true, message: '请输入产品编码', trigger: 'blur' }],
  sampleQty: [{ required: true, message: '请输入抽样数', trigger: 'blur' }]
}

const num = v => (v === null || v === undefined ? 0 : Number(v))
const fmt = v => num(v).toLocaleString('zh-CN', { maximumFractionDigits: 2 })

function typeText(v) {
  return typeMap[v] || v || '-'
}

function sourceText(v) {
  return sourceMap[v] || v || '-'
}

function severityText(v) {
  return (severityMap[v] || {}).label || v || '-'
}

function severityTone(v) {
  return (severityMap[v] || {}).type || 'info'
}

function pad(n) {
  return String(n).padStart(2, '0')
}

function genInspectionCode() {
  const d = new Date()
  const ymd = String(d.getFullYear()).slice(2) + pad(d.getMonth() + 1) + pad(d.getDate())
  return 'IQ-' + ymd + '-' + String(Math.floor(Math.random() * 900) + 100)
}

function now() {
  const d = new Date()
  return d.getFullYear() + '-' + pad(d.getMonth() + 1) + '-' + pad(d.getDate()) + ' ' +
    pad(d.getHours()) + ':' + pad(d.getMinutes()) + ':' + pad(d.getSeconds())
}

const kpis = computed(() => {
  const ins = allInspections.value
  const iss = allIssues.value
  const fail = ins.filter(i => i.result === 'fail').length
  const pending = iss.filter(i => ['open', 'isolated', 'reviewing'].includes(i.status)).length
  const released = iss.filter(i => i.status === 'released').length
  return [
    { label: '检验任务数', value: ins.length, unit: '单', foot: '首检 / 巡检 / 终检', tone: '' },
    { label: '不合格数', value: fail, unit: '单', foot: '检验判定不合格', tone: fail > 0 ? 'danger' : 'ok' },
    { label: '待处置质量问题', value: pending, unit: '条', foot: '等待隔离 / 评审 / 处置', tone: pending > 0 ? 'warn' : 'ok' },
    { label: '已放行数', value: released, unit: '条', foot: '处置完成并放行', tone: 'ok' }
  ]
})

const disposeActive = computed(() => {
  const list = allIssues.value
  if (!list.length) return 0
  if (list.some(i => i.status === 'released')) return 5
  if (list.some(i => i.status === 'disposed')) return 4
  if (list.some(i => i.status === 'reviewing')) return 3
  if (list.some(i => i.status === 'isolated')) return 2
  return 1
})

const pareto = computed(() => {
  const map = {}
  allIssues.value.forEach(i => {
    const key = i.issueType || '未分类'
    map[key] = (map[key] || 0) + num(i.qty)
  })
  return Object.entries(map)
    .map(([name, value]) => ({ name, value }))
    .sort((a, b) => b.value - a.value)
})

const paretoOption = computed(() => ({
  grid: { left: 44, right: 16, top: 28, bottom: 30 },
  tooltip: { trigger: 'axis' },
  xAxis: {
    type: 'category',
    data: pareto.value.map(i => i.name),
    axisLine: { lineStyle: { color: '#c9d3d9' } },
    axisLabel: { color: '#5b6b74', interval: 0 }
  },
  yAxis: {
    type: 'value',
    name: '不良数量',
    nameTextStyle: { color: '#8a98a0' },
    splitLine: { lineStyle: { color: '#eef2f4' } },
    axisLabel: { color: '#5b6b74' }
  },
  series: [{
    type: 'bar',
    data: pareto.value.map(i => i.value),
    barMaxWidth: 30,
    itemStyle: { color: '#b23a2e' },
    label: { show: true, position: 'top', color: '#5b6b74' }
  }]
}))

function buildInspectionQuery() {
  const q = { pageNum: page.value, pageSize: size.value }
  if (keyword.value) q.params = { keyword: keyword.value }
  if (inspectionType.value) q.inspectionType = inspectionType.value
  return q
}

function buildIssueQuery() {
  const q = { pageNum: page.value, pageSize: size.value }
  if (keyword.value) q.params = { keyword: keyword.value }
  return q
}

async function load() {
  loading.value = true
  try {
    if (activeTab.value === 'inspection') {
      const res = await listInspection(buildInspectionQuery())
      rows.value = res.rows || []
      total.value = res.total || 0
    } else if (activeTab.value === 'issue') {
      const res = await listQualityIssue(buildIssueQuery())
      rows.value = res.rows || []
      total.value = res.total || 0
    } else {
      const q = { pageNum: 1, pageSize: 500 }
      if (keyword.value) q.params = { keyword: keyword.value }
      const res = await listQualityIssue(q)
      const filtered = (res.rows || []).filter(i => i.status !== 'released')
      total.value = filtered.length
      const start = (page.value - 1) * size.value
      rows.value = filtered.slice(start, start + size.value)
    }
  } finally {
    loading.value = false
  }
}

async function loadMeta() {
  const [ins, iss, wo] = await Promise.all([
    listInspection({ pageNum: 1, pageSize: 500 }),
    listQualityIssue({ pageNum: 1, pageSize: 500 }),
    listWorkOrder({ pageNum: 1, pageSize: 500 })
  ])
  allInspections.value = ins.rows || []
  allIssues.value = iss.rows || []
  workOrders.value = wo.rows || []
}

async function refresh() {
  await loadMeta()
  await load()
}

function onTabChange() {
  inspectionType.value = ''
  page.value = 1
  load()
}

function handleQuery() {
  page.value = 1
  load()
}

function resetQuery() {
  keyword.value = ''
  inspectionType.value = ''
  page.value = 1
  load()
}

function openJudge(row) {
  judgeForm.value = { ...row }
  judgeOpen.value = true
}

async function submitJudge() {
  if (!judgeForm.value.result) {
    proxy.$modal.msgWarning('请选择判定结果')
    return
  }
  saving.value = true
  try {
    await updateInspection({ ...judgeForm.value })
    proxy.$modal.msgSuccess('判定结果已更新')
    judgeOpen.value = false
    await refresh()
  } finally {
    saving.value = false
  }
}

function openInspection() {
  inspectionForm.value = {
    inspectionCode: genInspectionCode(),
    inspectionType: 'patrol',
    orderCode: '',
    productCode: '',
    batchNo: '',
    sampleQty: 8,
    ngQty: 0,
    inspector: '',
    inspectTime: now()
  }
  inspectionOpen.value = true
}

function pickOrder(code) {
  const hit = workOrders.value.find(w => w.orderCode === code)
  if (hit) inspectionForm.value.productCode = hit.productCode
}

function submitInspection() {
  proxy.$refs.inspectionRef.validate(async valid => {
    if (!valid) return
    saving.value = true
    try {
      await addInspection({ ...inspectionForm.value, result: 'pass' })
      proxy.$modal.msgSuccess('检验单已创建')
      inspectionOpen.value = false
      await refresh()
    } finally {
      saving.value = false
    }
  })
}

function openDispose(row) {
  disposeForm.value = {
    issueId: row.issueId,
    issueCode: row.issueCode,
    dispose: row.dispose || 'rework'
  }
  disposeOpen.value = true
}

async function submitDispose() {
  if (!disposeForm.value.dispose) {
    proxy.$modal.msgWarning('请选择处置方式')
    return
  }
  saving.value = true
  try {
    const status = disposeForm.value.dispose === 'concession' ? 'released' : 'disposed'
    const res = await disposeIssue({ issueId: disposeForm.value.issueId, dispose: disposeForm.value.dispose, status })
    proxy.$modal.msgSuccess(res.msg || '处置完成')
    disposeOpen.value = false
    await refresh()
  } finally {
    saving.value = false
  }
}

onMounted(async () => {
  const kw = route.query.keyword
  if (kw) keyword.value = String(kw)
  await loadMeta()
  await load()
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
.status-tabs { margin-top: 6px; }
.pareto-panel { margin-bottom: 12px; }
.query-bar { margin-bottom: 4px; }
.num { font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.muted { font-size: 12px; color: #8a98a0; }
</style>
