<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>生产执行</h2>
        <span class="sub">工单执行 · 工位作业 · 报工与不良联动</span>
      </div>
      <el-button type="primary" icon="EditPen" v-hasPermi="['mes:report:add']" @click="openReport()">生产报工</el-button>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-card shadow="never" class="panel">
      <template #header><span>工位链 · 白班</span></template>
      <div class="stations">
        <div v-for="(s, i) in stations" :key="s.name" class="station" :class="{ 'is-active': i === activeStation }" @click="activeStation = i">
          <div class="station__name">{{ s.name }}</div>
          <div class="station__meta">{{ s.line }}</div>
          <div class="station__value">{{ s.output }}<em>台</em></div>
        </div>
      </div>
    </el-card>

    <el-row :gutter="12" style="margin-top: 12px">
      <el-col :span="14">
        <el-card shadow="never" class="panel">
          <template #header>
            <div class="card-header">
              <span>工单台账</span>
              <el-radio-group v-model="woStatus" size="small" @change="loadWorkOrders">
                <el-radio-button label="">全部</el-radio-button>
                <el-radio-button label="running">生产中</el-radio-button>
                <el-radio-button label="waiting">待料</el-radio-button>
                <el-radio-button label="scheduled">已排程</el-radio-button>
                <el-radio-button label="finished">已完成</el-radio-button>
              </el-radio-group>
            </div>
          </template>
          <el-table v-loading="loading" :data="workOrders" size="small" @row-click="openDetail">
            <el-table-column prop="orderCode" label="工单号" width="145" />
            <el-table-column prop="productName" label="产品" min-width="150" show-overflow-tooltip />
            <el-table-column prop="lineCode" label="产线" width="80" />
            <el-table-column label="进度" width="150">
              <template #default="{ row }">
                <el-progress :percentage="progress(row)" :stroke-width="8" />
              </template>
            </el-table-column>
            <el-table-column label="状态" width="90">
              <template #default="{ row }"><MesStatusTag :value="row.status" type="workOrder" /></template>
            </el-table-column>
            <el-table-column label="操作" width="90" fixed="right">
              <template #default="{ row }">
                <el-button link type="primary" v-hasPermi="['mes:report:add']" @click.stop="openReport(row)">报工</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="10">
        <el-card shadow="never" class="panel">
          <template #header><span>当班报工记录</span></template>
          <el-table :data="reports" size="small">
            <el-table-column prop="reportCode" label="报工单号" width="140" />
            <el-table-column prop="station" label="工位" min-width="120" show-overflow-tooltip />
            <el-table-column prop="operator" label="报工人" width="80" />
            <el-table-column label="合格 / 不良" width="100">
              <template #default="{ row }">{{ num(row.qtyOk) }} / {{ num(row.qtyNg) }}</template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>

    <el-drawer v-model="detailOpen" :title="detail.orderCode + ' · 工单详情'" size="440px">
      <el-descriptions :column="1" border size="small">
        <el-descriptions-item label="产品">{{ detail.productName }}</el-descriptions-item>
        <el-descriptions-item label="计划数量">{{ num(detail.qty) }} 台</el-descriptions-item>
        <el-descriptions-item label="完成数量">{{ num(detail.completedQty) }} 台</el-descriptions-item>
        <el-descriptions-item label="不良数量">{{ num(detail.ngQty) }} 台</el-descriptions-item>
        <el-descriptions-item label="产线 / 工位">{{ detail.lineCode }} / {{ detail.station || '-' }}</el-descriptions-item>
        <el-descriptions-item label="状态"><MesStatusTag :value="detail.status" type="workOrder" /></el-descriptions-item>
      </el-descriptions>
      <el-divider content-position="left">工序进度</el-divider>
      <el-steps direction="vertical" :active="detail.status === 'finished' ? 5 : 3" finish-status="success">
        <el-step title="机加" description="MC-01 · 已完工" />
        <el-step title="装配" description="AS-01 · 进行中" />
        <el-step title="测试" description="TS-01 · 待检验" />
        <el-step title="包装" description="PK-01 · 待包装" />
        <el-step title="入库" description="成品仓" />
      </el-steps>
      <el-divider content-position="left">最近报工</el-divider>
      <el-table :data="detailReports" size="small">
        <el-table-column prop="reportCode" label="报工单号" width="140" />
        <el-table-column prop="qtyOk" label="合格" width="70" />
        <el-table-column prop="qtyNg" label="不良" width="70" />
        <el-table-column prop="reportTime" label="时间" min-width="140" />
      </el-table>
    </el-drawer>

    <el-dialog v-model="reportOpen" title="生产报工" width="560px" append-to-body>
      <el-form ref="reportRef" :model="report" :rules="rules" label-width="90px">
        <el-form-item label="工单号" prop="orderCode">
          <el-select v-model="report.orderCode" filterable style="width: 100%" @change="pickOrder">
            <el-option v-for="w in workOrders" :key="w.workOrderId" :label="w.orderCode + ' · ' + w.productName" :value="w.orderCode" :disabled="w.status === 'finished'" />
          </el-select>
        </el-form-item>
        <el-form-item label="工位">
          <el-input v-model="report.station" placeholder="如 AS-01 / OP40" />
        </el-form-item>
        <el-form-item label="合格数量" prop="qtyOk">
          <el-input-number v-model="report.qtyOk" :min="0" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="不良数量">
          <el-input-number v-model="report.qtyNg" :min="0" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="班次">
          <el-select v-model="report.shift" style="width: 100%">
            <el-option label="白班" value="白班" />
            <el-option label="夜班" value="夜班" />
          </el-select>
        </el-form-item>
        <el-alert v-if="report.qtyNg > 0" type="warning" :closable="false" show-icon title="不良数量大于 0 时将自动生成质量问题单，进入不合格处置流程。" />
      </el-form>
      <template #footer>
        <el-button @click="reportOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submit">提交报工</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="MesProduction">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { listWorkOrder } from '@/api/mes/workOrder'
import { listReport } from '@/api/mes/report'
import { listQualityIssue } from '@/api/mes/quality'
import { submitReport } from '@/api/mes/biz'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'

const { proxy } = getCurrentInstance()
const loading = ref(false)
const saving = ref(false)
const workOrders = ref([])
const reports = ref([])
const issues = ref([])
const woStatus = ref('')
const activeStation = ref(1)
const detailOpen = ref(false)
const detail = ref({})
const detailReports = ref([])
const reportOpen = ref(false)
const report = ref({})
const rules = {
  orderCode: [{ required: true, message: '请选择工单', trigger: 'change' }],
  qtyOk: [{ required: true, message: '请输入合格数量', trigger: 'blur' }]
}

const num = v => (v === null || v === undefined ? 0 : Number(v))

const stations = computed(() => {
  const lines = ['MC-01', 'AS-01', 'AS-02', 'TS-01', 'PK-01']
  return [
    { name: 'OP10 机加', line: 'MC-01', output: sum('MC-01') },
    { name: 'OP20 装配', line: 'AS-02', output: sum('AS-02') },
    { name: 'OP30 装配', line: 'AS-01', output: sum('AS-01') },
    { name: 'OP40 测试', line: 'TS-01', output: sum('TS-01') },
    { name: 'OP50 包装', line: 'PK-01', output: sum('PK-01') }
  ].filter(s => lines.includes(s.line))
})

function sum(line) {
  return workOrders.value.filter(w => w.lineCode === line).reduce((s, w) => s + num(w.completedQty), 0)
}

const kpis = computed(() => {
  const running = workOrders.value.filter(w => w.status === 'running').length
  const waiting = workOrders.value.filter(w => w.status === 'waiting').length
  const planQty = workOrders.value.reduce((s, w) => s + num(w.qty), 0)
  const doneQty = workOrders.value.reduce((s, w) => s + num(w.completedQty), 0)
  const ng = workOrders.value.reduce((s, w) => s + num(w.ngQty), 0)
  return [
    { label: '生产中工单', value: running, unit: '单', foot: '执行中', tone: 'ok' },
    { label: '待料工单', value: waiting, unit: '单', foot: '需物料拉动', tone: 'warn' },
    { label: '完成数量', value: doneQty, unit: '台', foot: '计划 ' + planQty + ' 台', tone: '' },
    { label: '质量问题', value: issues.value.length, unit: '条', foot: '不良累计 ' + ng + ' 台', tone: 'danger' }
  ]
})

function progress(row) {
  const qty = num(row.qty)
  return qty ? Math.min(Math.round((num(row.completedQty) / qty) * 100), 100) : 0
}

async function loadWorkOrders() {
  loading.value = true
  try {
    const res = await listWorkOrder({ pageNum: 1, pageSize: 100, status: woStatus.value || undefined })
    workOrders.value = res.rows || []
  } finally {
    loading.value = false
  }
}

async function loadRecords() {
  const r = await listReport({ pageNum: 1, pageSize: 20 })
  reports.value = r.rows || []
  const q = await listQualityIssue({ pageNum: 1, pageSize: 100 })
  issues.value = q.rows || []
}

function openDetail(row) {
  detail.value = row
  detailReports.value = reports.value.filter(i => i.orderCode === row.orderCode)
  detailOpen.value = true
}

function openReport(row) {
  report.value = {
    orderCode: row ? row.orderCode : '',
    station: row ? row.station : '',
    qtyOk: 0,
    qtyNg: 0,
    shift: '白班'
  }
  reportOpen.value = true
}

function pickOrder(code) {
  const hit = workOrders.value.find(w => w.orderCode === code)
  if (hit) report.value.station = hit.station || (hit.lineCode + ' / OP40')
}

function submit() {
  proxy.$refs.reportRef.validate(async valid => {
    if (!valid) return
    saving.value = true
    try {
      const res = await submitReport(report.value)
      const tip = res.data && res.data.issueCode ? '，已生成质量问题 ' + res.data.issueCode : ''
      proxy.$modal.msgSuccess('报工成功' + tip)
      reportOpen.value = false
      await loadWorkOrders()
      await loadRecords()
    } finally {
      saving.value = false
    }
  })
}

onMounted(async () => {
  await loadWorkOrders()
  await loadRecords()
})
</script>

<style scoped>
.page-head { display: flex; align-items: flex-start; justify-content: space-between; margin-bottom: 12px; }
.page-head h2 { margin: 0; font-size: 20px; color: #16242b; }
.page-head .sub { font-size: 12px; color: #8a98a0; }
.panel { border-color: #d3dbe0; }
.panel :deep(.el-card__header) { padding: 10px 14px; font-weight: 600; background: #f5f7f8; }
.card-header { display: flex; justify-content: space-between; align-items: center; }
.stations { display: flex; gap: 12px; }
.station { flex: 1; border: 1px solid #d3dbe0; border-radius: 6px; padding: 10px 12px; cursor: pointer; transition: all .15s; }
.station.is-active { border-color: #12495f; background: #eef4f7; }
.station__name { font-size: 13px; font-weight: 600; color: #16242b; }
.station__meta { font-size: 11px; color: #8a98a0; }
.station__value { font-size: 22px; font-weight: 600; color: #12495f; font-family: 'DIN Alternate', monospace; }
.station__value em { font-size: 11px; font-style: normal; color: #8a98a0; margin-left: 2px; }
:deep(.el-table__row) { cursor: pointer; }
</style>
