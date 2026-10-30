<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>统计分析</h2>
        <span class="sub">华东智造基地 · 产出达成、OEE、质量缺陷多口径分析</span>
      </div>
      <div class="head-actions">
        <el-radio-group v-model="range" @change="load">
          <el-radio-button value="7">近 7 天</el-radio-button>
          <el-radio-button value="30">近 30 天</el-radio-button>
          <el-radio-button value="month">本月</el-radio-button>
        </el-radio-group>
        <el-button icon="Refresh" :loading="loading" @click="load">刷新</el-button>
        <el-button icon="Download" v-hasPermi="['mes:workOrder:export']" @click="handleExport">导出</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="6" />

    <el-row :gutter="12">
      <el-col :span="8">
        <el-card shadow="never" class="panel">
          <template #header><span>产出趋势 · {{ rangeLabel }}</span></template>
          <MesChart :option="trendOption" :height="250" />
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="never" class="panel">
          <template #header><span>OEE 产线对比</span></template>
          <MesChart :option="oeeOption" :height="250" />
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="never" class="panel">
          <template #header><span>缺陷 Pareto</span></template>
          <MesChart :option="defectOption" :height="250" />
        </el-card>
      </el-col>
    </el-row>

    <el-card shadow="never" class="panel" style="margin-top: 12px">
      <template #header>
        <span>产线对比</span>
        <span class="panel-tip">点击行查看产线指标明细</span>
      </template>
      <el-table v-loading="loading" :data="lines" size="small" border @row-click="openLine">
        <el-table-column prop="lineCode" label="产线" width="110" />
        <el-table-column prop="planQty" label="计划数量" min-width="110" sortable align="right">
          <template #default="{ row }">{{ num(row.planQty) }}</template>
        </el-table-column>
        <el-table-column prop="doneQty" label="完成数量" min-width="110" sortable align="right">
          <template #default="{ row }">{{ num(row.doneQty) }}</template>
        </el-table-column>
        <el-table-column prop="ngQty" label="不良数量" min-width="100" sortable align="right">
          <template #default="{ row }"><span :class="{ danger: num(row.ngQty) > 0 }">{{ num(row.ngQty) }}</span></template>
        </el-table-column>
        <el-table-column label="达成率" min-width="170">
          <template #default="{ row }">
            <el-progress :percentage="rate(row)" :stroke-width="8" :status="rate(row) >= 90 ? 'success' : 'warning'" />
          </template>
        </el-table-column>
        <el-table-column prop="oee" label="OEE" min-width="100" sortable align="right">
          <template #default="{ row }">
            <span :class="{ danger: num(row.oee) < 80 }">{{ fmt(row.oee) }}%</span>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-drawer v-model="drawerOpen" :title="'产线指标 · ' + (current.lineCode || '')" size="380px">
      <div v-if="current.lineCode" class="drawer-body">
        <div class="drawer-kpi">
          <div class="drawer-kpi__item">
            <span>计划数量</span>
            <strong class="num">{{ num(current.planQty) }}</strong>
          </div>
          <div class="drawer-kpi__item">
            <span>完成数量</span>
            <strong class="num">{{ num(current.doneQty) }}</strong>
          </div>
          <div class="drawer-kpi__item">
            <span>不良数量</span>
            <strong class="num danger">{{ num(current.ngQty) }}</strong>
          </div>
          <div class="drawer-kpi__item">
            <span>达成率</span>
            <strong class="num">{{ rate(current) }}%</strong>
          </div>
        </div>
        <div class="drawer-block">
          <div class="drawer-block__label">综合效率 OEE</div>
          <el-progress :percentage="Math.min(num(current.oee), 100)" :stroke-width="14" :status="num(current.oee) >= 80 ? 'success' : 'exception'" />
          <div class="drawer-note">{{ num(current.oee) >= 80 ? '达到 ≥ 80% 目标' : '低于 ≥ 80% 目标，需关注停机与换型损失' }}</div>
        </div>
        <div class="drawer-block">
          <div class="drawer-block__label">产出达成</div>
          <el-progress :percentage="rate(current)" :stroke-width="14" :status="rate(current) >= 90 ? 'success' : 'warning'" />
          <div class="drawer-note">完成 {{ num(current.doneQty) }} / 计划 {{ num(current.planQty) }} 台</div>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script setup name="MesAnalysis">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { getAnalysis } from '@/api/mes/dashboard'
import MesKpi from '../components/MesKpi.vue'
import MesChart from '../components/MesChart.vue'

const { proxy } = getCurrentInstance()

const loading = ref(false)
const range = ref('7')
const data = ref({})
const drawerOpen = ref(false)
const current = ref({})

const num = v => (v === null || v === undefined || v === '' ? 0 : Number(v))
const fmt = v => num(v).toFixed(1)

const rangeLabel = computed(() => ({ '7': '近 7 天', '30': '近 30 天', month: '本月' }[range.value] || '近 7 天'))
const lines = computed(() => data.value.lines || [])

const kpis = computed(() => {
  const k = data.value.kpis || {}
  return [
    { label: '区间产出', value: num(k.output), unit: '台', foot: rangeLabel.value + '合格产出', tone: 'ok' },
    { label: '累计产出', value: num(k.totalOutput), unit: '台', foot: '历史报工累计', tone: '' },
    { label: '不良数量', value: num(k.ngQty), unit: '台', foot: '历史报工不良', tone: 'danger' },
    { label: '平均 OEE', value: fmt(k.oee), unit: '%', foot: '设备平均综合效率', tone: num(k.oee) >= 80 ? 'ok' : 'warn' },
    { label: '质量问题数', value: num(k.issueCount), unit: '条', foot: '累计质量问题', tone: 'warn' },
    { label: '报警数', value: num(k.alarmCount), unit: '条', foot: '累计设备报警', tone: 'danger' }
  ]
})

const trendOption = computed(() => {
  const list = data.value.trend || []
  return {
    grid: { left: 44, right: 16, top: 20, bottom: 30 },
    tooltip: { trigger: 'axis' },
    xAxis: {
      type: 'category',
      data: list.map(i => i.d),
      axisLine: { lineStyle: { color: '#c9d3d9' } },
      axisLabel: { color: '#5b6b74' }
    },
    yAxis: {
      type: 'value',
      splitLine: { lineStyle: { color: '#eef2f4' } },
      axisLabel: { color: '#5b6b74' }
    },
    series: [{
      name: '产出',
      type: 'line',
      smooth: true,
      symbolSize: 6,
      data: list.map(i => num(i.v)),
      lineStyle: { color: '#12495f', width: 2 },
      itemStyle: { color: '#12495f' },
      areaStyle: { color: 'rgba(18, 73, 95, 0.12)' }
    }]
  }
})

const oeeOption = computed(() => {
  const list = data.value.oee || []
  return {
    grid: { left: 44, right: 16, top: 20, bottom: 30 },
    tooltip: { trigger: 'axis', valueFormatter: v => v + '%' },
    xAxis: {
      type: 'category',
      data: list.map(i => i.lineCode),
      axisLine: { lineStyle: { color: '#c9d3d9' } },
      axisLabel: { color: '#5b6b74' }
    },
    yAxis: {
      type: 'value',
      max: 100,
      splitLine: { lineStyle: { color: '#eef2f4' } },
      axisLabel: { color: '#5b6b74', formatter: '{value}%' }
    },
    series: [{
      name: 'OEE',
      type: 'bar',
      barMaxWidth: 26,
      data: list.map(i => num(i.oee)),
      itemStyle: {
        borderRadius: [3, 3, 0, 0],
        color: p => (Number(p.value) < 80 ? '#b23a2e' : '#1b7f55')
      },
      markLine: {
        silent: true,
        symbol: 'none',
        label: { formatter: '目标 80%', color: '#b8790a' },
        lineStyle: { color: '#b8790a', type: 'dashed' },
        data: [{ yAxis: 80 }]
      }
    }]
  }
})

const defectOption = computed(() => {
  const list = data.value.defects || []
  return {
    grid: { left: 44, right: 16, top: 24, bottom: 38 },
    tooltip: { trigger: 'axis' },
    xAxis: {
      type: 'category',
      data: list.map(i => i.name),
      axisLine: { lineStyle: { color: '#c9d3d9' } },
      axisLabel: { color: '#5b6b74', interval: 0, rotate: list.length > 5 ? 28 : 0 }
    },
    yAxis: {
      type: 'value',
      splitLine: { lineStyle: { color: '#eef2f4' } },
      axisLabel: { color: '#5b6b74' }
    },
    series: [{
      name: '缺陷数',
      type: 'bar',
      barMaxWidth: 26,
      data: list.map(i => num(i.v)),
      itemStyle: {
        borderRadius: [3, 3, 0, 0],
        color: p => (p.dataIndex === 0 ? '#b8790a' : '#12495f')
      },
      label: { show: true, position: 'top', color: '#5b6b74', fontSize: 11 }
    }]
  }
})

function rate(row) {
  const plan = num(row.planQty)
  return plan ? Math.min(Math.round((num(row.doneQty) / plan) * 100), 100) : 0
}

function openLine(row) {
  current.value = { ...row }
  drawerOpen.value = true
}

async function load() {
  loading.value = true
  try {
    const days = range.value === 'month' ? 30 : Number(range.value)
    const res = await getAnalysis(days)
    data.value = res.data || {}
  } finally {
    loading.value = false
  }
}

function handleExport() {
  proxy.download('mes/workOrder/export', {}, `analysis_${Date.now()}.xlsx`)
}

onMounted(load)
</script>

<style scoped>
.mes-page .page-head { display: flex; align-items: flex-start; justify-content: space-between; margin-bottom: 12px; }
.page-head h2 { margin: 0; font-size: 20px; color: #16242b; }
.page-head .sub { font-size: 12px; color: #8a98a0; }
.head-actions { display: flex; align-items: center; gap: 8px; }
.panel { border-color: #d3dbe0; }
.panel :deep(.el-card__header) { padding: 10px 14px; font-weight: 600; color: #16242b; background: #f5f7f8; }
.panel-tip { margin-left: 10px; font-size: 12px; font-weight: 400; color: #8a98a0; }
.num { font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.danger { color: #b23a2e; }
:deep(.el-table__row) { cursor: pointer; }
.drawer-body { padding: 0 4px; }
.drawer-kpi { display: grid; grid-template-columns: repeat(2, minmax(0, 1fr)); gap: 10px; margin-bottom: 18px; }
.drawer-kpi__item { border: 1px solid #d3dbe0; border-left: 3px solid #12495f; border-radius: 5px; padding: 10px 12px; display: flex; flex-direction: column; gap: 6px; }
.drawer-kpi__item span { font-size: 12px; color: #8a98a0; }
.drawer-kpi__item strong { font-size: 24px; color: #16242b; }
.drawer-kpi__item strong.danger { color: #b23a2e; }
.drawer-block { margin-bottom: 18px; }
.drawer-block__label { font-size: 13px; color: #16242b; margin-bottom: 8px; }
.drawer-note { margin-top: 6px; font-size: 12px; color: #8a98a0; }
</style>
