<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>质量追溯</h2>
        <span class="sub">以序列号 / 批次为索引，查询人机料法环全要素履历与批次反查结果</span>
      </div>
      <div class="head-actions">
        <el-button type="primary" plain icon="Download" v-hasPermi="['mes:trace:export']" @click="handleExport">导出追溯报告</el-button>
      </div>
    </div>

    <el-card shadow="never" class="panel search-panel">
      <el-form :inline="true" class="query-bar">
        <el-form-item label="追溯索引">
          <el-input v-model="keyword" placeholder="序列号 / 批次号，如 SN-SD3-261025-0090 或 B261025-01" clearable style="width: 440px" @keyup.enter="query" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="Search" :loading="loading" @click="query">查询</el-button>
          <el-button icon="Refresh" @click="reset">重置</el-button>
          <span class="hint">支持 ?serial= / ?batch= 深链接</span>
        </el-form-item>
      </el-form>
    </el-card>

    <div v-loading="loading" class="trace-body">
      <template v-if="exists">
        <el-card shadow="never" class="panel">
          <template #header>
            <div class="card-header">
              <span>产品档案 · {{ trace.serialNo || trace.batchNo }}</span>
            </div>
          </template>
          <el-descriptions :column="3" border size="small">
            <el-descriptions-item label="序列号">{{ trace.serialNo || '-' }}</el-descriptions-item>
            <el-descriptions-item label="批次号">{{ trace.batchNo || '-' }}</el-descriptions-item>
            <el-descriptions-item label="产品">{{ trace.productName || '-' }}（{{ trace.productCode || '-' }}）</el-descriptions-item>
            <el-descriptions-item label="工单号">{{ trace.orderCode || '-' }}</el-descriptions-item>
            <el-descriptions-item label="工位">{{ trace.station || '-' }}</el-descriptions-item>
            <el-descriptions-item label="关键件">{{ trace.keyMaterial || '-' }}</el-descriptions-item>
            <el-descriptions-item label="供应商批次">{{ trace.supplierBatch || '-' }}</el-descriptions-item>
            <el-descriptions-item label="检验结果">
              <MesStatusTag :value="trace.inspectionResult" type="common" />
            </el-descriptions-item>
            <el-descriptions-item label="生产时间">{{ trace.producedTime || '-' }}</el-descriptions-item>
            <el-descriptions-item label="状态">
              <el-tag :type="statusMeta(trace.status).type" size="small" effect="dark">{{ statusMeta(trace.status).label }}</el-tag>
            </el-descriptions-item>
          </el-descriptions>
        </el-card>

        <el-row :gutter="12" class="trace-row">
          <el-col :span="8">
            <el-card shadow="never" class="panel full-height">
              <template #header><span>全流程履历</span></template>
              <el-timeline>
                <el-timeline-item
                  v-for="node in timelineNodes"
                  :key="node.name"
                  :type="node.type"
                  :hollow="!node.done && !node.active"
                >
                  <div class="node-title">{{ node.name }}</div>
                  <div class="node-desc">{{ node.desc }} · {{ node.state }}</div>
                </el-timeline-item>
              </el-timeline>
            </el-card>
          </el-col>
          <el-col :span="16">
            <el-card shadow="never" class="panel full-height">
              <template #header><span>报工记录 · {{ trace.orderCode || '-' }}</span></template>
              <el-table :data="reports" size="small" border max-height="300">
                <el-table-column prop="reportCode" label="报工单号" width="150" />
                <el-table-column prop="station" label="工位" min-width="130" show-overflow-tooltip />
                <el-table-column prop="operator" label="报工人" width="90" />
                <el-table-column label="合格" width="90" align="right">
                  <template #default="{ row }"><span class="num">{{ fmt(row.qtyOk) }}</span></template>
                </el-table-column>
                <el-table-column label="不良" width="90" align="right">
                  <template #default="{ row }"><span class="num danger">{{ fmt(row.qtyNg) }}</span></template>
                </el-table-column>
                <el-table-column prop="reportTime" label="报工时间" width="160" />
              </el-table>
              <el-empty v-if="!reports.length" description="暂无报工记录" :image-size="60" />
            </el-card>
          </el-col>
        </el-row>

        <el-card shadow="never" class="panel trace-row">
          <template #header><span>质量问题 · 共 {{ issues.length }} 条</span></template>
          <el-table :data="issues" size="small" border>
            <el-table-column prop="issueCode" label="问题单号" width="150" />
            <el-table-column prop="issueType" label="问题类型" width="130" />
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
            <el-table-column prop="foundTime" label="发现时间" width="160" />
            <el-table-column prop="handler" label="处理人" width="100" />
            <el-table-column prop="description" label="问题描述" min-width="200" show-overflow-tooltip />
          </el-table>
          <el-empty v-if="!issues.length" description="该批次暂无质量问题" :image-size="60" />
        </el-card>

        <el-card shadow="never" class="panel trace-row">
          <template #header>
            <div class="card-header">
              <span>批次反查 · {{ trace.batchNo || '-' }}</span>
              <span v-if="num(result.batchFrozen) > 0" class="frozen-tip">存在 {{ num(result.batchFrozen) }} 台冻结品，禁止发货</span>
            </div>
          </template>

          <div class="stat-grid">
            <div class="stat-card">
              <div class="stat-label">批次总数</div>
              <div class="stat-value num">{{ num(result.batchTotal) }}</div>
              <div class="stat-foot">同批次序列号</div>
            </div>
            <div class="stat-card is-ok">
              <div class="stat-label">合格</div>
              <div class="stat-value num">{{ num(result.batchPass) }}</div>
              <div class="stat-foot">检验结果 pass</div>
            </div>
            <div class="stat-card is-danger">
              <div class="stat-label">不合格</div>
              <div class="stat-value num">{{ num(result.batchFail) }}</div>
              <div class="stat-foot">检验结果 fail</div>
            </div>
            <div class="stat-card is-warn">
              <div class="stat-label">冻结</div>
              <div class="stat-value num">{{ num(result.batchFrozen) }}</div>
              <div class="stat-foot">状态 frozen</div>
            </div>
          </div>

          <el-table :data="batchRows" size="small" border max-height="320">
            <el-table-column prop="serialNo" label="序列号" width="190" />
            <el-table-column label="检验结果" width="120">
              <template #default="{ row }"><MesStatusTag :value="row.inspectionResult" type="common" /></template>
            </el-table-column>
            <el-table-column label="状态" width="120">
              <template #default="{ row }">
                <el-tag :type="statusMeta(row.status).type" size="small" effect="light">{{ statusMeta(row.status).label }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="station" label="工位" min-width="140" show-overflow-tooltip />
            <el-table-column prop="producedTime" label="生产时间" width="170" />
          </el-table>
        </el-card>
      </template>

      <el-card v-else-if="queried" shadow="never" class="panel">
        <el-empty description="未找到该序列号/批次的追溯档案" :image-size="90">
          <div class="empty-tip">请确认输入的是完整序列号或批次号，例如 SN-SD3-261025-0090 / B261025-01</div>
        </el-empty>
      </el-card>

      <el-card v-else shadow="never" class="panel">
        <el-empty description="输入序列号或批次号开始追溯" :image-size="90">
          <div class="empty-tip">支持正向查询（序列号 → 全流程履历）与反向查询（批次 → 同批序列号）</div>
        </el-empty>
      </el-card>
    </div>
  </div>
</template>

<script setup name="MesTrace">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import { traceDetail } from '@/api/mes/biz'
import MesStatusTag from '../components/MesStatusTag.vue'

const { proxy } = getCurrentInstance()
const route = useRoute()

const keyword = ref('')
const loading = ref(false)
const queried = ref(false)
const result = ref({})

const trace = computed(() => result.value.trace || {})
const reports = computed(() => result.value.reports || [])
const issues = computed(() => result.value.issues || [])
const batchRows = computed(() => result.value.batchRows || [])
const exists = computed(() => !!result.value.exists)

const num = v => (v === null || v === undefined ? 0 : Number(v))
const fmt = v => num(v).toLocaleString('zh-CN', { maximumFractionDigits: 2 })

const severityMap = {
  serious: { label: '严重', type: 'danger' },
  normal: { label: '一般', type: 'warning' },
  info: { label: '提示', type: 'info' }
}

function severityText(v) {
  return (severityMap[v] || {}).label || v || '-'
}

function severityTone(v) {
  return (severityMap[v] || {}).type || 'info'
}

function statusMeta(v) {
  const map = {
    frozen: { label: '冻结', type: 'danger' },
    released: { label: '已放行', type: 'success' },
    produced: { label: '已生产', type: 'primary' }
  }
  const hit = map[v]
  return hit || { label: v || '-', type: 'info' }
}

function stationStage(station) {
  const s = String(station || '').toUpperCase()
  if (s.includes('PK')) return 4
  if (s.includes('TS')) return 3
  if (s.includes('AS')) return 2
  if (s.includes('MC')) return 1
  return 0
}

const timelineNodes = computed(() => {
  const current = stationStage(trace.value.station)
  const nodes = [
    { name: '物料上线', desc: '关键件与供应商批次绑定' },
    { name: '机加', desc: 'MC-01 / MC-02 机加产线' },
    { name: '装配', desc: 'AS-01 / AS-02 装配产线' },
    { name: '测试', desc: 'TS-01 功能与耐压测试' },
    { name: '包装', desc: 'PK-01 包装入库' }
  ]
  return nodes.map((n, i) => {
    const done = i < current
    const active = i === current
    return {
      ...n,
      done,
      active,
      type: done ? 'success' : active ? 'primary' : 'info',
      state: done ? '已完成' : active ? '当前节点' : '待执行'
    }
  })
})

async function query() {
  const kw = keyword.value.trim()
  if (!kw) {
    proxy.$modal.msgWarning('请输入序列号或批次号')
    return
  }
  loading.value = true
  try {
    const res = await traceDetail(kw)
    result.value = res.data || { exists: false }
    queried.value = true
  } finally {
    loading.value = false
  }
}

function reset() {
  keyword.value = ''
  result.value = {}
  queried.value = false
}

function handleExport() {
  if (!keyword.value.trim()) {
    proxy.$modal.msgWarning('请先输入并查询序列号或批次号')
    return
  }
  proxy.download('mes/trace/export', { params: { keyword: keyword.value.trim() } }, `trace_${Date.now()}.xlsx`)
}

onMounted(() => {
  const q = route.query
  const kw = q.serial || q.batch || q.keyword
  if (kw) {
    keyword.value = String(kw)
    query()
  }
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
.search-panel { margin-bottom: 12px; }
.query-bar { margin-bottom: 0; }
.hint { margin-left: 10px; font-size: 12px; color: #8a98a0; }
.trace-body { min-height: 320px; }
.trace-row { margin-top: 12px; }
.full-height { height: 100%; }
.node-title { font-size: 13px; font-weight: 600; color: #16242b; }
.node-desc { font-size: 12px; color: #5b6b74; }
.num { font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.danger { color: #b23a2e; }
.stat-grid { display: grid; grid-template-columns: repeat(4, minmax(0, 1fr)); gap: 12px; margin-bottom: 12px; }
.stat-card { border: 1px solid #d3dbe0; border-left: 3px solid #12495f; border-radius: 6px; padding: 10px 14px; background: #fff; }
.stat-card.is-ok { border-left-color: #1b7f55; }
.stat-card.is-warn { border-left-color: #b8790a; }
.stat-card.is-danger { border-left-color: #b23a2e; }
.stat-label { font-size: 13px; color: #5b6b74; }
.stat-value { font-size: 24px; font-weight: 600; color: #16242b; line-height: 1.3; }
.stat-foot { font-size: 11px; color: #8a98a0; }
.frozen-tip { font-size: 12px; color: #b23a2e; font-weight: 400; }
.empty-tip { font-size: 12px; color: #8a98a0; margin-top: 6px; }
@media (max-width: 1200px) {
  .stat-grid { grid-template-columns: repeat(2, minmax(0, 1fr)); }
}
</style>
