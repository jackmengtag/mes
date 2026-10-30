<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>生产总览</h2>
        <span class="sub">华东智造基地 · 当班生产、计划达成、质量与设备状态</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="load">刷新</el-button>
        <el-button type="primary" plain @click="$router.push('/mes/scm')">供应链总览</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-row :gutter="12">
      <el-col :span="16">
        <el-card shadow="never" class="panel">
          <template #header><span>价值流 · 工序产出</span></template>
          <div class="flow">
            <div v-for="(step, i) in flowSteps" :key="step.name" class="flow-node" :class="'is-' + step.tone" @click="go(step.path)">
              <div class="flow-node__name">{{ step.name }}</div>
              <div class="flow-node__meta">{{ step.meta }}</div>
              <div class="flow-node__value">{{ step.out }}<em>台</em></div>
              <div class="flow-node__foot">在制 {{ step.wip }}</div>
              <div v-if="i < flowSteps.length - 1" class="flow-arrow">→</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="never" class="panel">
          <template #header><span>分时产出</span></template>
          <MesChart :option="hourlyOption" :height="188" />
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="12" style="margin-top: 12px">
      <el-col :span="14">
        <el-card shadow="never" class="panel">
          <template #header><span>产线负荷</span></template>
          <el-table :data="lines" size="small" @row-click="row => go('/mes/production')">
            <el-table-column prop="lineCode" label="产线" width="90" />
            <el-table-column label="计划 / 完成" width="140">
              <template #default="{ row }">{{ num(row.planQty) }} / {{ num(row.doneQty) }}</template>
            </el-table-column>
            <el-table-column label="达成率" width="150">
              <template #default="{ row }">
                <el-progress :percentage="rate(row)" :stroke-width="8" :status="rate(row) >= 90 ? 'success' : 'warning'" />
              </template>
            </el-table-column>
            <el-table-column label="不良" width="80">
              <template #default="{ row }">{{ num(row.ngQty) }}</template>
            </el-table-column>
            <el-table-column label="OEE" width="90">
              <template #default="{ row }">
                <span :class="{ danger: Number(row.oee) < 80 }">{{ row.oee || 0 }}%</span>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="10">
        <el-card shadow="never" class="panel">
          <template #header><span>关键物料齐套</span></template>
          <div v-for="item in kitting" :key="item.name" class="kit-row">
            <span class="kit-name">{{ item.name }}</span>
            <el-progress :percentage="Math.min(Number(item.rate) || 0, 100)" :stroke-width="8" :status="Number(item.rate) >= 90 ? 'success' : 'warning'" style="flex: 1" />
            <span class="kit-value">{{ num(item.qty) }} / {{ num(item.safety) }}</span>
          </div>
          <el-empty v-if="!kitting.length" description="暂无齐套数据" :image-size="60" />
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="12" style="margin-top: 12px">
      <el-col :span="14">
        <el-card shadow="never" class="panel">
          <template #header><span>在制工单</span></template>
          <el-table :data="wipOrders" size="small" @row-click="row => go('/mes/production?keyword=' + row.orderCode)">
            <el-table-column prop="orderCode" label="工单号" width="150" />
            <el-table-column prop="productName" label="产品" min-width="150" show-overflow-tooltip />
            <el-table-column prop="lineCode" label="产线" width="80" />
            <el-table-column label="进度" width="160">
              <template #default="{ row }">
                <el-progress :percentage="progress(row)" :stroke-width="8" />
              </template>
            </el-table-column>
            <el-table-column label="状态" width="90">
              <template #default="{ row }"><MesStatusTag :value="row.status" type="workOrder" /></template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="10">
        <el-card shadow="never" class="panel">
          <template #header><span>异常与报警</span></template>
          <el-timeline v-if="alarms.length">
            <el-timeline-item v-for="a in alarms" :key="a.alarmCode" :timestamp="a.alarmTime" :type="a.level === 'serious' ? 'danger' : 'warning'">
              <div class="alarm-title">{{ a.equipmentName }} · {{ a.alarmCode }}</div>
              <div class="alarm-body">{{ a.content }}</div>
            </el-timeline-item>
          </el-timeline>
          <el-empty v-else description="当前无异常" :image-size="60" />
          <div class="panel-link" @click="go('/mes/equipment?tab=alarm')">查看全部报警 →</div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup name="MesOverview">
import { computed, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import { getOverview } from '@/api/mes/dashboard'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'
import MesChart from '../components/MesChart.vue'

const router = useRouter()
const loading = ref(false)
const data = ref({})
const kpis = ref([])
const lines = ref([])
const kitting = ref([])
const wipOrders = ref([])
const alarms = ref([])
const flowSteps = ref([])

const num = v => (v === null || v === undefined ? 0 : Number(v))

function rate(row) {
  const plan = num(row.planQty)
  return plan ? Math.min(Math.round((num(row.doneQty) / plan) * 100), 100) : 0
}

function progress(row) {
  const qty = num(row.qty)
  return qty ? Math.min(Math.round((num(row.completedQty) / qty) * 100), 100) : 0
}

const hourlyOption = computed(() => ({
  grid: { left: 34, right: 12, top: 16, bottom: 24 },
  tooltip: { trigger: 'axis' },
  xAxis: { type: 'category', data: (data.value.hourly || []).map(i => i.h + '时'), axisLine: { lineStyle: { color: '#c9d3d9' } }, axisLabel: { color: '#5b6b74' } },
  yAxis: { type: 'value', splitLine: { lineStyle: { color: '#eef2f4' } }, axisLabel: { color: '#5b6b74' } },
  series: [{ type: 'bar', data: (data.value.hourly || []).map(i => Number(i.v)), itemStyle: { color: '#12495f' }, barMaxWidth: 18 }]
}))

function go(path) {
  router.push(path)
}

async function load() {
  loading.value = true
  try {
    const res = await getOverview()
    data.value = res.data || {}
    const k = data.value.kpis || {}
    kpis.value = [
      { label: '当班产出', value: num(k.output), unit: '台', foot: '最近报工日累计', tone: 'ok' },
      { label: '计划数量', value: num(k.planQty), unit: '台', foot: '未完工工单计划', tone: '' },
      { label: '计划达成率', value: num(k.planQty) ? ((num(k.doneQty) / num(k.planQty)) * 100).toFixed(1) : '0.0', unit: '%', foot: '完成 ' + num(k.doneQty) + ' 台', tone: 'ok' },
      { label: '一次合格率 FTT', value: num(k.okQty) + num(k.ngQty) ? ((num(k.okQty) / (num(k.okQty) + num(k.ngQty))) * 100).toFixed(1) : '0.0', unit: '%', foot: '目标 ≥ 97.5%', tone: 'warn' },
      { label: '综合效率 OEE', value: num(k.oee).toFixed(1), unit: '%', foot: '目标 ≥ 80%', tone: num(k.oee) >= 80 ? 'ok' : 'warn' },
      { label: '在制品 WIP', value: num(k.wip), unit: '台', foot: '安全水位 ≤ 420 台', tone: 'warn' },
      { label: '缺料工单', value: num(k.shortageOrders), unit: '单', foot: '低于安全库存物料', tone: 'danger' },
      { label: '设备报警', value: num(k.activeAlarms), unit: '条', foot: '未处理报警', tone: 'danger' }
    ]
    lines.value = data.value.lines || []
    kitting.value = data.value.kitting || []
    wipOrders.value = data.value.wipOrders || []
    alarms.value = data.value.alarms || []
    const lineMap = {}
    lines.value.forEach(l => { lineMap[l.lineCode] = l })
    const group = (codes) => codes.reduce((acc, c) => {
      const row = lineMap[c] || {}
      acc.out += num(row.doneQty)
      acc.wip += Math.max(num(row.planQty) - num(row.doneQty), 0)
      return acc
    }, { out: 0, wip: 0 })
    const mc = group(['MC-01', 'MC-02'])
    const as = group(['AS-01', 'AS-02'])
    const ts = group(['TS-01'])
    const pk = group(['PK-01'])
    flowSteps.value = [
      { name: '机加', meta: 'MC-01 / MC-02', out: mc.out, wip: mc.wip, tone: 'ok', path: '/mes/production' },
      { name: '装配', meta: 'AS-01 / AS-02', out: as.out, wip: as.wip, tone: 'accent', path: '/mes/production' },
      { name: '测试', meta: 'TS-01', out: ts.out, wip: ts.wip, tone: 'ok', path: '/mes/quality' },
      { name: '包装', meta: 'PK-01', out: pk.out, wip: pk.wip, tone: 'warn', path: '/mes/inventory' }
    ]
  } finally {
    loading.value = false
  }
}

onMounted(load)
</script>

<style scoped>
.mes-page .page-head { display: flex; align-items: flex-start; justify-content: space-between; margin-bottom: 12px; }
.page-head h2 { margin: 0; font-size: 20px; color: #16242b; }
.page-head .sub { font-size: 12px; color: #8a98a0; }
.head-actions { display: flex; gap: 8px; }
.panel { border-color: #d3dbe0; }
.panel :deep(.el-card__header) { padding: 10px 14px; font-weight: 600; color: #16242b; background: #f5f7f8; }
.flow { display: flex; align-items: stretch; gap: 18px; }
.flow-node { position: relative; flex: 1; border: 1px solid #d3dbe0; border-top: 3px solid #12495f; border-radius: 6px; padding: 10px 12px; cursor: pointer; background: #fff; transition: all .15s; }
.flow-node:hover { box-shadow: 0 2px 10px rgba(18, 73, 95, .12); transform: translateY(-1px); }
.flow-node.is-ok { border-top-color: #1b7f55; }
.flow-node.is-warn { border-top-color: #b8790a; }
.flow-node.is-accent { border-top-color: #e07b1a; }
.flow-node__name { font-size: 14px; font-weight: 600; color: #16242b; }
.flow-node__meta { font-size: 11px; color: #8a98a0; margin: 2px 0 6px; }
.flow-node__value { font-size: 22px; font-weight: 600; color: #12495f; font-family: 'DIN Alternate', monospace; }
.flow-node__value em { font-size: 11px; font-style: normal; color: #8a98a0; margin-left: 3px; }
.flow-node__foot { font-size: 11px; color: #5b6b74; margin-top: 4px; }
.flow-arrow { position: absolute; right: -15px; top: 50%; transform: translateY(-50%); color: #b9c5cc; }
.kit-row { display: flex; align-items: center; gap: 10px; margin-bottom: 8px; font-size: 12px; }
.kit-name { width: 72px; color: #5b6b74; }
.kit-value { width: 104px; text-align: right; color: #8a98a0; font-family: 'Roboto Mono', monospace; }
.alarm-title { font-size: 13px; color: #16242b; font-weight: 600; }
.alarm-body { font-size: 12px; color: #5b6b74; }
.panel-link { text-align: right; font-size: 12px; color: #2a6f97; cursor: pointer; }
.danger { color: #b23a2e; }
:deep(.el-table__row) { cursor: pointer; }
</style>
