<template>
  <div ref="pageRef" class="app-container mes-page board-page">
    <div class="board-head">
      <div class="board-title">
        <h1>华东智造基地 · 车间实时看板</h1>
        <span class="board-sub">MES 实时监控 · 只读展示 · 数据每 60 秒自动同步</span>
      </div>
      <div class="board-tools">
        <span class="auto-refresh"><i class="dot"></i>自动刷新 60s</span>
        <span class="clock">{{ clock }}</span>
        <el-button class="ghost-btn" size="small" @click="toggleFullscreen">{{ fullscreen ? '退出全屏' : '全屏' }}</el-button>
        <el-button class="ghost-btn" size="small" :loading="loading" @click="load">刷新</el-button>
      </div>
    </div>

    <div class="led-strip">
      <div v-for="item in leds" :key="item.label" class="led" :class="'tone-' + item.tone">
        <div class="led__label">{{ item.label }}</div>
        <div class="led__value">{{ item.value }}<em>{{ item.unit }}</em></div>
        <div class="led__foot">{{ item.foot }}</div>
      </div>
    </div>

    <div v-loading="loading" class="board-body">
      <div class="board-panel board-panel--lines">
        <div class="panel-head">
          <span>产线实时看板</span>
          <span class="panel-head__meta">{{ lines.length }} 条产线</span>
        </div>
        <div class="line-cards">
          <div v-for="line in lines" :key="line.lineCode" class="line-card">
            <div class="line-card__head">
              <span class="line-code">{{ line.lineCode }}</span>
              <span class="line-oee" :class="{ danger: num(line.oee) < 80 }">OEE {{ fmt(line.oee) }}%</span>
            </div>
            <div class="line-qty">
              <span class="num">{{ num(line.doneQty) }}</span>
              <em>/ {{ num(line.planQty) }} 台</em>
            </div>
            <div class="bar">
              <div class="bar__fill" :class="{ 'is-low': rate(line) < 80 }" :style="{ width: rate(line) + '%' }"></div>
            </div>
            <div class="line-foot">
              <span>达成 {{ rate(line) }}%</span>
              <span :class="{ danger: num(line.ngQty) > 0 }">不良 {{ num(line.ngQty) }}</span>
            </div>
          </div>
          <el-empty v-if="!lines.length" description="暂无产线数据" :image-size="60" />
        </div>
      </div>

      <div class="board-panel board-panel--wip">
        <div class="panel-head">
          <span>在制工单</span>
          <span class="panel-head__meta">{{ wipOrders.length }} 单</span>
        </div>
        <el-table :data="wipOrders" size="small" height="326">
          <el-table-column prop="orderCode" label="工单号" width="128" />
          <el-table-column prop="productName" label="产品" min-width="120" show-overflow-tooltip />
          <el-table-column prop="lineCode" label="产线" width="70" />
          <el-table-column label="进度" width="96">
            <template #default="{ row }">
              <div class="mini-bar"><div class="mini-bar__fill" :style="{ width: progress(row) + '%' }"></div></div>
              <span class="mini-bar__text">{{ progress(row) }}%</span>
            </template>
          </el-table-column>
          <el-table-column label="状态" width="78">
            <template #default="{ row }"><MesStatusTag :value="row.status" type="workOrder" /></template>
          </el-table-column>
        </el-table>
      </div>

      <div class="board-panel board-panel--alarm">
        <div class="panel-head">
          <span>异常与报警</span>
          <span class="panel-head__meta danger">{{ alarms.length }} 条未处理</span>
        </div>
        <div class="alarm-list">
          <div v-for="a in alarms" :key="a.alarmCode" class="alarm-item" :class="{ 'is-serious': a.level === 'serious' }">
            <div class="alarm-item__head">
              <span class="alarm-code">{{ a.alarmCode }}</span>
              <span class="alarm-level" :class="'lv-' + a.level">{{ levelText(a.level) }}</span>
            </div>
            <div class="alarm-item__equip">{{ a.equipmentName }}</div>
            <div class="alarm-item__content">{{ a.content }}</div>
            <div class="alarm-item__time">{{ a.alarmTime }}</div>
          </div>
          <el-empty v-if="!alarms.length" description="当前无异常" :image-size="60" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup name="MesBoard">
import { computed, getCurrentInstance, onMounted, onUnmounted, ref } from 'vue'
import { getBoard } from '@/api/mes/dashboard'
import MesStatusTag from '../components/MesStatusTag.vue'

const { proxy } = getCurrentInstance()

const loading = ref(false)
const data = ref({})
const clock = ref('')
const fullscreen = ref(false)
const pageRef = ref(null)

let clockTimer = null
let refreshTimer = null

const num = v => (v === null || v === undefined || v === '' ? 0 : Number(v))
const fmt = v => num(v).toFixed(1)

const lines = computed(() => data.value.lines || [])
const wipOrders = computed(() => data.value.wipOrders || [])
const alarms = computed(() => data.value.alarms || [])

const leds = computed(() => {
  const k = data.value.kpis || {}
  const ok = num(k.okQty)
  const ng = num(k.ngQty)
  const ftt = ok + ng ? ((ok / (ok + ng)) * 100).toFixed(1) : '0.0'
  return [
    { label: '当班产出', value: num(k.output), unit: '台', foot: '最近报工日合格产出', tone: 'ok' },
    { label: '计划数量', value: num(k.planQty), unit: '台', foot: '未完工工单计划', tone: '' },
    { label: 'OEE', value: fmt(k.oee), unit: '%', foot: num(k.oee) >= 80 ? '达标' : '低于 80% 目标', tone: num(k.oee) >= 80 ? 'ok' : 'warn' },
    { label: 'FTT', value: ftt, unit: '%', foot: '一次合格率', tone: Number(ftt) >= 97.5 ? 'ok' : 'warn' },
    { label: '在制 WIP', value: num(k.wip), unit: '台', foot: '运行 / 待料 / 预警工单', tone: 'accent' },
    { label: '报警数', value: num(k.activeAlarms), unit: '条', foot: '未处理设备报警', tone: 'danger' }
  ]
})

function rate(row) {
  const plan = num(row.planQty)
  return plan ? Math.min(Math.round((num(row.doneQty) / plan) * 100), 100) : 0
}

function progress(row) {
  const qty = num(row.qty)
  return qty ? Math.min(Math.round((num(row.completedQty) / qty) * 100), 100) : 0
}

function levelText(level) {
  return { serious: '严重', normal: '一般', info: '提示' }[level] || level || '-'
}

function pad(n) {
  return String(n).padStart(2, '0')
}

function tick() {
  const d = new Date()
  clock.value = d.getFullYear() + '-' + pad(d.getMonth() + 1) + '-' + pad(d.getDate()) + ' ' +
    pad(d.getHours()) + ':' + pad(d.getMinutes()) + ':' + pad(d.getSeconds())
}

async function load() {
  loading.value = true
  try {
    const res = await getBoard()
    data.value = res.data || {}
  } finally {
    loading.value = false
  }
}

function syncFullscreen() {
  fullscreen.value = !!document.fullscreenElement
}

async function toggleFullscreen() {
  try {
    if (document.fullscreenElement) {
      await document.exitFullscreen()
    } else {
      await (pageRef.value || document.documentElement).requestFullscreen()
    }
    syncFullscreen()
  } catch (e) {
    proxy.$modal.msgWarning('当前浏览器不支持全屏显示')
  }
}

onMounted(() => {
  tick()
  clockTimer = setInterval(tick, 1000)
  load()
  refreshTimer = setInterval(load, 60000)
  document.addEventListener('fullscreenchange', syncFullscreen)
})

onUnmounted(() => {
  clearInterval(clockTimer)
  clearInterval(refreshTimer)
  document.removeEventListener('fullscreenchange', syncFullscreen)
})
</script>

<style scoped>
.app-container.board-page {
  min-height: calc(100vh - 84px);
  padding: 16px 20px 20px;
  color: #d8e9f0;
  background:
    radial-gradient(1200px 420px at 50% -10%, rgba(28, 116, 148, 0.35), transparent 70%),
    linear-gradient(180deg, #0d2430 0%, #0b1c26 46%, #08151d 100%);
  font-family: 'DIN Alternate', 'Roboto Mono', 'Microsoft YaHei', sans-serif;
}
.board-head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
  padding-bottom: 12px;
  border-bottom: 1px solid rgba(94, 158, 180, 0.28);
}
.board-title h1 {
  margin: 0;
  font-size: 26px;
  letter-spacing: 3px;
  font-weight: 600;
  color: #e8f6fb;
  text-shadow: 0 0 18px rgba(64, 178, 214, 0.45);
}
.board-sub { font-size: 12px; color: #6d9aac; letter-spacing: 1px; }
.board-tools { display: flex; align-items: center; gap: 12px; }
.auto-refresh { display: flex; align-items: center; gap: 6px; font-size: 12px; color: #6fbfa8; }
.auto-refresh .dot {
  width: 8px; height: 8px; border-radius: 50%; background: #35d39a;
  box-shadow: 0 0 8px #35d39a; animation: pulse 1.6s infinite;
}
@keyframes pulse { 0%, 100% { opacity: 1; } 50% { opacity: 0.25; } }
.clock { font-size: 20px; color: #9fe0f4; letter-spacing: 1px; }
.ghost-btn {
  color: #a9cfdd;
  background: rgba(29, 79, 96, 0.35);
  border-color: rgba(94, 158, 180, 0.45);
}
.ghost-btn:hover { color: #e8f6fb; border-color: #4fa8c6; background: rgba(37, 98, 118, 0.5); }

.led-strip {
  display: grid;
  grid-template-columns: repeat(6, minmax(0, 1fr));
  gap: 12px;
  margin: 14px 0;
}
.led {
  position: relative;
  padding: 12px 16px;
  border: 1px solid rgba(64, 132, 156, 0.35);
  border-left: 3px solid #2a7f99;
  border-radius: 6px;
  background: linear-gradient(180deg, rgba(18, 51, 65, 0.9), rgba(10, 30, 40, 0.9));
  overflow: hidden;
}
.led::after {
  content: '';
  position: absolute; inset: 0;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0.05), transparent 55%);
  pointer-events: none;
}
.led.tone-ok { border-left-color: #2fbf8f; }
.led.tone-warn { border-left-color: #e0a12a; }
.led.tone-danger { border-left-color: #e1543f; }
.led.tone-accent { border-left-color: #3d9dc4; }
.led__label { font-size: 12px; color: #7ea7b7; letter-spacing: 1px; }
.led__value {
  font-size: 38px; line-height: 1.15; font-weight: 700; color: #f0fbff;
  text-shadow: 0 0 16px rgba(76, 190, 226, 0.55);
}
.led__value em { font-size: 12px; font-style: normal; color: #7ea7b7; margin-left: 5px; }
.led__foot { font-size: 11px; color: #5f8595; }

.board-body {
  display: grid;
  grid-template-columns: 5fr 4.2fr 3fr;
  gap: 12px;
  min-height: 420px;
}
.board-panel {
  display: flex;
  flex-direction: column;
  border: 1px solid rgba(64, 132, 156, 0.32);
  border-radius: 6px;
  background: linear-gradient(180deg, rgba(15, 41, 53, 0.92), rgba(9, 26, 35, 0.92));
  padding: 0 12px 12px;
}
.panel-head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 0;
  margin-bottom: 4px;
  font-size: 14px;
  letter-spacing: 1px;
  color: #c6e6f0;
  border-bottom: 1px solid rgba(64, 132, 156, 0.25);
}
.panel-head__meta { font-size: 12px; color: #6d9aac; }
.danger { color: #ff6b52 !important; }
.num { font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.line-cards { display: grid; grid-template-columns: repeat(2, minmax(0, 1fr)); gap: 10px; }
.line-card {
  border: 1px solid rgba(64, 132, 156, 0.28);
  border-radius: 5px;
  padding: 10px 12px;
  background: rgba(17, 47, 60, 0.72);
}
.line-card__head { display: flex; align-items: center; justify-content: space-between; }
.line-code { font-size: 15px; font-weight: 700; color: #a9e2f3; letter-spacing: 1px; }
.line-oee { font-size: 12px; color: #4fd6a4; }
.line-qty { margin: 6px 0 8px; font-size: 26px; font-weight: 700; color: #f0fbff; font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.line-qty em { font-size: 12px; font-style: normal; color: #6d9aac; margin-left: 4px; }
.bar { height: 8px; border-radius: 4px; background: rgba(70, 120, 140, 0.28); overflow: hidden; }
.bar__fill { height: 100%; border-radius: 4px; background: linear-gradient(90deg, #1f7fa1, #3fd0bd); transition: width .5s; }
.bar__fill.is-low { background: linear-gradient(90deg, #a84a22, #e0a12a); }
.line-foot { display: flex; justify-content: space-between; margin-top: 6px; font-size: 11px; color: #6d9aac; }
.mini-bar { height: 6px; border-radius: 3px; background: rgba(70, 120, 140, 0.28); overflow: hidden; }
.mini-bar__fill { height: 100%; background: linear-gradient(90deg, #1f7fa1, #3fd0bd); }
.mini-bar__text { font-size: 11px; color: #8fb6c4; }

.alarm-list { flex: 1; overflow-y: auto; max-height: 330px; padding-right: 2px; }
.alarm-item {
  border: 1px solid rgba(224, 161, 42, 0.32);
  border-left: 3px solid #e0a12a;
  border-radius: 5px;
  padding: 8px 10px;
  margin-bottom: 8px;
  background: rgba(66, 47, 16, 0.28);
}
.alarm-item.is-serious { border-color: rgba(225, 84, 63, 0.5); border-left-color: #e1543f; background: rgba(74, 24, 18, 0.42); }
.alarm-item__head { display: flex; align-items: center; justify-content: space-between; }
.alarm-code { font-size: 12px; color: #f0c987; font-family: 'Roboto Mono', monospace; }
.alarm-level { font-size: 11px; padding: 0 6px; border-radius: 3px; }
.alarm-level.lv-serious { color: #ffd9d1; background: rgba(225, 84, 63, 0.45); }
.alarm-level.lv-normal { color: #ffe6b0; background: rgba(224, 161, 42, 0.35); }
.alarm-level.lv-info { color: #c3dbe4; background: rgba(84, 132, 150, 0.35); }
.alarm-item__equip { margin-top: 4px; font-size: 12px; color: #d8e9f0; }
.alarm-item__content { font-size: 12px; color: #9fc0cd; line-height: 1.5; }
.alarm-item__time { margin-top: 2px; font-size: 11px; color: #5f8595; }

.board-page :deep(.el-table) {
  --el-table-bg-color: transparent;
  --el-table-tr-bg-color: transparent;
  --el-table-header-bg-color: rgba(23, 68, 84, 0.85);
  --el-table-border-color: rgba(64, 132, 156, 0.28);
  --el-table-row-hover-bg-color: rgba(29, 84, 102, 0.55);
  --el-table-header-text-color: #8fb6c4;
  --el-table-text-color: #cfe3ea;
  background: transparent;
}
.board-page :deep(.el-table th.el-table__cell) { background: rgba(23, 68, 84, 0.85); color: #8fb6c4; }
.board-page :deep(.el-table td.el-table__cell) { border-bottom-color: rgba(64, 132, 156, 0.22); }
.board-page :deep(.el-table__inner-wrapper::before) { background-color: rgba(64, 132, 156, 0.28); }
.board-page :deep(.el-table__body tr:hover > td.el-table__cell) { background: rgba(29, 84, 102, 0.55) !important; }
.board-page :deep(.el-empty__description p) { color: #5f8595; }

@media (max-width: 1600px) {
  .led-strip { grid-template-columns: repeat(3, minmax(0, 1fr)); }
  .board-body { grid-template-columns: 1fr 1fr; }
  .board-panel--alarm { grid-column: 1 / span 2; }
}
@media (max-width: 1100px) {
  .led-strip { grid-template-columns: repeat(2, minmax(0, 1fr)); }
  .board-body { grid-template-columns: 1fr; }
  .board-panel--alarm { grid-column: auto; }
  .line-cards { grid-template-columns: 1fr; }
}
</style>
