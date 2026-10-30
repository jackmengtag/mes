<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>设备管理</h2>
        <span class="sub">设备台账 · 状态与 OEE · 报警处理 · 点检记录</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="refresh">刷新</el-button>
        <el-button icon="Download" v-hasPermi="['mes:equipment:export']" @click="handleExport">导出</el-button>
        <el-button type="primary" icon="Plus" v-hasPermi="['mes:equipment:add']" @click="openForm()">新增设备</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-card shadow="never" class="panel">
      <template #header><span>OEE 产线对比</span></template>
      <MesChart :option="oeeOption" :height="150" />
    </el-card>

    <el-tabs v-model="activeTab" class="tab-bar" @tab-change="onTabChange">
      <el-tab-pane label="设备台账" name="ledger" />
      <el-tab-pane label="报警记录" name="alarm" />
      <el-tab-pane label="点检记录" name="check" />
    </el-tabs>

    <el-form :inline="true" class="query-bar">
      <el-form-item label="关键字">
        <el-input v-model="keyword" :placeholder="keywordPlaceholder" clearable style="width: 230px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item v-if="activeTab === 'ledger'" label="状态">
        <el-select v-model="status" clearable placeholder="全部" style="width: 150px" @change="handleQuery">
          <el-option label="运行" value="run" />
          <el-option label="待机" value="idle" />
          <el-option label="报警" value="alarm" />
          <el-option label="停机" value="stop" />
        </el-select>
      </el-form-item>
      <el-form-item v-if="activeTab === 'alarm'" label="级别">
        <el-select v-model="level" clearable placeholder="全部" style="width: 150px" @change="handleQuery">
          <el-option label="严重" value="serious" />
          <el-option label="一般" value="normal" />
          <el-option label="提示" value="info" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="rows" size="small" border @row-click="openDrawer">
      <template v-if="activeTab === 'ledger'">
        <el-table-column prop="equipmentCode" label="设备编码" width="130" />
        <el-table-column prop="equipmentName" label="设备名称" min-width="160" show-overflow-tooltip />
        <el-table-column prop="lineCode" label="产线" width="80" />
        <el-table-column prop="equipmentType" label="类型" width="110" />
        <el-table-column label="状态" width="90">
          <template #default="{ row }"><MesStatusTag :value="row.status" type="equipment" /></template>
        </el-table-column>
        <el-table-column label="OEE" width="90" align="right">
          <template #default="{ row }">
            <span class="num" :class="{ danger: num(row.oee) < 80 }">{{ fmt(row.oee) }}%</span>
          </template>
        </el-table-column>
        <el-table-column label="运行小时" width="100" align="right">
          <template #default="{ row }"><span class="num">{{ fmt(row.runHours) }}</span></template>
        </el-table-column>
        <el-table-column prop="location" label="位置" width="120" show-overflow-tooltip />
        <el-table-column prop="lastCheckTime" label="最近点检" width="160" />
        <el-table-column label="操作" width="190" fixed="right">
          <template #default="{ row }">
            <div @click.stop>
              <el-dropdown v-hasPermi="['mes:equipment:command']" @command="cmd => sendCommand(row, cmd)">
                <el-button link type="primary">指令<el-icon class="el-icon--right"><ArrowDown /></el-icon></el-button>
                <template #dropdown>
                  <el-dropdown-menu>
                    <el-dropdown-item command="start">启动</el-dropdown-item>
                    <el-dropdown-item command="stop">停机</el-dropdown-item>
                    <el-dropdown-item command="reset">复位</el-dropdown-item>
                    <el-dropdown-item command="calibrate">校准</el-dropdown-item>
                  </el-dropdown-menu>
                </template>
              </el-dropdown>
              <el-button link type="primary" v-hasPermi="['mes:equipment:edit']" @click="openForm(row)">编辑</el-button>
              <el-button link type="danger" v-hasPermi="['mes:equipment:remove']" @click="remove(row)">删除</el-button>
            </div>
          </template>
        </el-table-column>
      </template>

      <template v-else-if="activeTab === 'alarm'">
        <el-table-column prop="alarmCode" label="报警单号" width="150" />
        <el-table-column prop="equipmentCode" label="设备编码" width="130" />
        <el-table-column prop="equipmentName" label="设备名称" min-width="150" show-overflow-tooltip />
        <el-table-column label="级别" width="90">
          <template #default="{ row }">
            <el-tag :type="levelTone(row.level)" size="small">{{ levelText(row.level) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="content" label="报警内容" min-width="200" show-overflow-tooltip />
        <el-table-column prop="alarmTime" label="报警时间" width="160" />
        <el-table-column label="状态" width="90">
          <template #default="{ row }"><MesStatusTag :value="row.status" /></template>
        </el-table-column>
        <el-table-column prop="handler" label="处理人" width="100">
          <template #default="{ row }">{{ row.handler || '-' }}</template>
        </el-table-column>
        <el-table-column label="操作" width="100" fixed="right">
          <template #default="{ row }">
            <el-button v-if="row.status === 'active'" link type="primary" v-hasPermi="['mes:alarm:handle']" @click.stop="handleAlarmRow(row)">处理</el-button>
            <span v-else class="muted">已处理</span>
          </template>
        </el-table-column>
      </template>

      <template v-else>
        <el-table-column prop="equipmentCode" label="设备编码" width="140" />
        <el-table-column prop="equipmentName" label="设备名称" min-width="180" show-overflow-tooltip />
        <el-table-column prop="lineCode" label="产线" width="90" />
        <el-table-column prop="equipmentType" label="设备类型" width="120" />
        <el-table-column label="点检周期" width="100">
          <template #default="{ row }">{{ checkCycle(row) }}</template>
        </el-table-column>
        <el-table-column prop="lastCheckTime" label="最近点检" width="170">
          <template #default="{ row }">{{ row.lastCheckTime || '-' }}</template>
        </el-table-column>
        <el-table-column label="点检状态" width="110">
          <template #default="{ row }">
            <el-tag :type="checkTone(row)" size="small">{{ checkText(row) }}</el-tag>
          </template>
        </el-table-column>
      </template>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="page" v-model:limit="size" @pagination="load" />

    <el-dialog v-model="formOpen" :title="form.equipmentId ? '编辑设备' : '新增设备'" width="600px" append-to-body>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="设备编码" prop="equipmentCode">
          <el-input v-model="form.equipmentCode" placeholder="如 EQ-MC-001" />
        </el-form-item>
        <el-form-item label="设备名称" prop="equipmentName">
          <el-input v-model="form.equipmentName" placeholder="如 立式加工中心" />
        </el-form-item>
        <el-form-item label="产线" prop="lineCode">
          <el-select v-model="form.lineCode" placeholder="请选择产线" style="width: 100%">
            <el-option v-for="item in lineOptions" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item label="设备类型" prop="equipmentType">
          <el-select v-model="form.equipmentType" placeholder="请选择设备类型" style="width: 100%">
            <el-option v-for="item in typeOptions" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" style="width: 100%">
            <el-option label="运行" value="run" />
            <el-option label="待机" value="idle" />
            <el-option label="报警" value="alarm" />
            <el-option label="停机" value="stop" />
          </el-select>
        </el-form-item>
        <el-form-item label="OEE" prop="oee">
          <el-input-number v-model="form.oee" :min="0" :max="100" :precision="1" style="width: 100%" />
        </el-form-item>
        <el-form-item label="位置" prop="location">
          <el-input v-model="form.location" placeholder="如 机加车间 A 区" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="formOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitForm">确定</el-button>
      </template>
    </el-dialog>

    <el-drawer v-model="drawerOpen" :title="'设备档案 · ' + (current.equipmentCode || '')" size="440px">
      <div v-if="current.equipmentId" class="drawer-body">
        <el-descriptions :column="1" border size="small">
          <el-descriptions-item label="设备编码">{{ current.equipmentCode }}</el-descriptions-item>
          <el-descriptions-item label="设备名称">{{ current.equipmentName }}</el-descriptions-item>
          <el-descriptions-item label="所属产线">{{ current.lineCode || '-' }}</el-descriptions-item>
          <el-descriptions-item label="设备类型">{{ current.equipmentType || '-' }}</el-descriptions-item>
          <el-descriptions-item label="运行状态">
            <MesStatusTag :value="current.status" type="equipment" />
          </el-descriptions-item>
          <el-descriptions-item label="综合效率">{{ fmt(current.oee) }}%</el-descriptions-item>
          <el-descriptions-item label="运行小时">{{ fmt(current.runHours) }} h</el-descriptions-item>
          <el-descriptions-item label="位置">{{ current.location || '-' }}</el-descriptions-item>
          <el-descriptions-item label="最近点检">{{ current.lastCheckTime || '-' }}</el-descriptions-item>
        </el-descriptions>

        <div class="drawer-section">
          <div class="drawer-section__head">近期报警</div>
          <div v-if="recentAlarms.length" class="recent-alarms">
            <div v-for="a in recentAlarms" :key="a.alarmId" class="recent-alarm">
              <div class="recent-alarm__head">
                <span>{{ a.alarmCode }}</span>
                <el-tag :type="levelTone(a.level)" size="small">{{ levelText(a.level) }}</el-tag>
              </div>
              <div class="recent-alarm__content">{{ a.content }}</div>
              <div class="recent-alarm__time">{{ a.alarmTime }}</div>
            </div>
          </div>
          <el-empty v-else description="暂无报警记录" :image-size="60" />
        </div>

        <div class="drawer-section">
          <div class="drawer-section__head">设备指令</div>
          <div class="drawer-actions">
            <el-button size="small" v-hasPermi="['mes:equipment:command']" @click="sendCommand(current, 'start')">启动</el-button>
            <el-button size="small" v-hasPermi="['mes:equipment:command']" @click="sendCommand(current, 'stop')">停机</el-button>
            <el-button size="small" v-hasPermi="['mes:equipment:command']" @click="sendCommand(current, 'reset')">复位</el-button>
            <el-button size="small" v-hasPermi="['mes:equipment:command']" @click="sendCommand(current, 'calibrate')">校准</el-button>
          </div>
        </div>
      </div>
    </el-drawer>
  </div>
</template>

<script setup name="MesEquipment">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { listEquipment, addEquipment, updateEquipment, delEquipment } from '@/api/mes/equipment'
import { listAlarm } from '@/api/mes/alarm'
import { equipmentCommand, handleAlarm } from '@/api/mes/biz'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'
import MesChart from '../components/MesChart.vue'

const { proxy } = getCurrentInstance()

const activeTab = ref('ledger')
const loading = ref(false)
const saving = ref(false)
const rows = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(10)
const keyword = ref('')
const status = ref('')
const level = ref('')

const allEquipment = ref([])
const allAlarms = ref([])

const formOpen = ref(false)
const form = ref({})
const formRef = ref()
const drawerOpen = ref(false)
const current = ref({})

const lineOptions = ['MC-01', 'MC-02', 'AS-01', 'AS-02', 'TS-01', 'PK-01']
const typeOptions = ['加工设备', '装配设备', '检测设备', '包装设备', '辅助设备']

const rules = {
  equipmentCode: [{ required: true, message: '请输入设备编码', trigger: 'blur' }],
  equipmentName: [{ required: true, message: '请输入设备名称', trigger: 'blur' }],
  lineCode: [{ required: true, message: '请选择产线', trigger: 'change' }],
  status: [{ required: true, message: '请选择状态', trigger: 'change' }]
}

const num = v => (v === null || v === undefined || v === '' ? 0 : Number(v))
const fmt = v => num(v).toFixed(1)

const keywordPlaceholder = computed(() => {
  if (activeTab.value === 'alarm') return '报警单号 / 设备编码 / 内容'
  return '设备编码 / 设备名称'
})

const kpis = computed(() => {
  const list = allEquipment.value
  const avg = list.length ? list.reduce((s, i) => s + num(i.oee), 0) / list.length : 0
  return [
    { label: '设备总数', value: list.length, unit: '台', foot: '设备台账在册', tone: '' },
    { label: '运行中', value: list.filter(i => i.status === 'run').length, unit: '台', foot: '状态为运行', tone: 'ok' },
    { label: '报警数', value: allAlarms.value.filter(i => i.status === 'active').length, unit: '条', foot: '未处理设备报警', tone: 'danger' },
    { label: '平均 OEE', value: avg.toFixed(1), unit: '%', foot: avg >= 80 ? '达到 ≥ 80% 目标' : '低于 ≥ 80% 目标', tone: avg >= 80 ? 'ok' : 'warn' }
  ]
})

const oeeOption = computed(() => {
  const group = {}
  allEquipment.value.forEach(i => {
    if (!i.lineCode) return
    if (!group[i.lineCode]) group[i.lineCode] = { sum: 0, count: 0 }
    group[i.lineCode].sum += num(i.oee)
    group[i.lineCode].count += 1
  })
  const codes = Object.keys(group).sort()
  const values = codes.map(c => Number((group[c].sum / group[c].count).toFixed(1)))
  return {
    grid: { left: 30, right: 8, top: 8, bottom: 18 },
    tooltip: { trigger: 'axis', valueFormatter: v => v + '%' },
    xAxis: { type: 'category', data: codes, axisLabel: { color: '#5b6b74', fontSize: 10 }, axisLine: { lineStyle: { color: '#c9d3d9' } } },
    yAxis: { type: 'value', max: 100, show: false },
    series: [{
      type: 'bar',
      barMaxWidth: 14,
      data: values,
      itemStyle: { borderRadius: [2, 2, 0, 0], color: p => (Number(p.value) < 80 ? '#b23a2e' : '#1b7f55') }
    }]
  }
})

function buildQuery() {
  const q = { pageNum: page.value, pageSize: size.value }
  if (keyword.value) q.params = { keyword: keyword.value }
  if (activeTab.value === 'ledger' && status.value) q.status = status.value
  if (activeTab.value === 'alarm' && level.value) q.level = level.value
  return q
}

const checkRows = computed(() => {
  const kw = keyword.value.trim()
  const list = kw
    ? allEquipment.value.filter(i => (i.equipmentCode || '').includes(kw) || (i.equipmentName || '').includes(kw))
    : allEquipment.value
  const start = (page.value - 1) * size.value
  return list.slice(start, start + size.value)
})

const checkTotal = computed(() => {
  const kw = keyword.value.trim()
  return kw
    ? allEquipment.value.filter(i => (i.equipmentCode || '').includes(kw) || (i.equipmentName || '').includes(kw)).length
    : allEquipment.value.length
})

async function load() {
  loading.value = true
  try {
    if (activeTab.value === 'ledger') {
      const res = await listEquipment(buildQuery())
      rows.value = res.rows || []
      total.value = res.total || 0
    } else if (activeTab.value === 'alarm') {
      const res = await listAlarm(buildQuery())
      rows.value = res.rows || []
      total.value = res.total || 0
    } else {
      rows.value = checkRows.value
      total.value = checkTotal.value
    }
  } finally {
    loading.value = false
  }
}

async function loadMeta() {
  const eq = await listEquipment({ pageNum: 1, pageSize: 500 })
  allEquipment.value = eq.rows || []
  const al = await listAlarm({ pageNum: 1, pageSize: 500 })
  allAlarms.value = al.rows || []
}

function refresh() {
  loadMeta()
  load()
}

function onTabChange() {
  status.value = ''
  level.value = ''
  keyword.value = ''
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
  level.value = ''
  page.value = 1
  load()
}

function levelText(v) {
  return { serious: '严重', normal: '一般', info: '提示' }[v] || v || '-'
}

function levelTone(v) {
  return { serious: 'danger', normal: 'warning', info: 'info' }[v] || 'info'
}

function checkCycle(row) {
  return row.equipmentType === '检测设备' ? '每周' : '每日'
}

function checkText(row) {
  if (!row.lastCheckTime) return '未点检'
  const diff = Date.now() - new Date(String(row.lastCheckTime).replace(/-/g, '/')).getTime()
  return diff <= 24 * 3600 * 1000 ? '正常' : '超期'
}

function checkTone(row) {
  const text = checkText(row)
  return text === '正常' ? 'success' : text === '超期' ? 'warning' : 'info'
}

function openForm(row) {
  form.value = row
    ? { ...row }
    : { equipmentCode: '', equipmentName: '', lineCode: '', equipmentType: '加工设备', status: 'idle', oee: 80, location: '' }
  formOpen.value = true
}

function submitForm() {
  formRef.value.validate(async valid => {
    if (!valid) return
    saving.value = true
    try {
      if (form.value.equipmentId) {
        await updateEquipment(form.value)
        proxy.$modal.msgSuccess('修改成功')
      } else {
        await addEquipment(form.value)
        proxy.$modal.msgSuccess('新增成功')
      }
      formOpen.value = false
      await loadMeta()
      load()
    } finally {
      saving.value = false
    }
  })
}

async function remove(row) {
  try {
    await proxy.$modal.confirm('确认删除设备「' + row.equipmentName + '」？')
  } catch (e) {
    return
  }
  await delEquipment(row.equipmentId)
  proxy.$modal.msgSuccess('删除成功')
  await loadMeta()
  load()
}

async function sendCommand(row, command) {
  const res = await equipmentCommand({ equipmentCode: row.equipmentCode, command })
  proxy.$modal.msgSuccess(res.msg || '指令已下发')
  await loadMeta()
  load()
}

async function handleAlarmRow(row) {
  try {
    await proxy.$modal.confirm('确认处理报警「' + row.alarmCode + '」？')
  } catch (e) {
    return
  }
  const res = await handleAlarm(row.alarmId)
  proxy.$modal.msgSuccess(res.msg || '报警已处理')
  await loadMeta()
  load()
}

const recentAlarms = computed(() => {
  if (!current.value.equipmentCode) return []
  return allAlarms.value.filter(i => i.equipmentCode === current.value.equipmentCode).slice(0, 5)
})

function openDrawer(row) {
  if (activeTab.value === 'alarm') return
  current.value = { ...row }
  drawerOpen.value = true
}

function handleExport() {
  proxy.download('mes/equipment/export', buildQuery(), `equipment_${Date.now()}.xlsx`)
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
.panel { border-color: #d3dbe0; margin-bottom: 4px; }
.panel :deep(.el-card__header) { padding: 10px 14px; font-weight: 600; color: #16242b; background: #f5f7f8; }
.tab-bar { margin-top: 8px; }
.num { font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.danger { color: #b23a2e; }
.muted { color: #8a98a0; font-size: 12px; }
:deep(.el-table__row) { cursor: pointer; }
.drawer-body { padding: 0 2px; }
.drawer-section { margin-top: 18px; }
.drawer-section__head { font-weight: 600; color: #16242b; font-size: 13px; margin-bottom: 8px; }
.recent-alarm { border: 1px solid #e2e8eb; border-left: 3px solid #b8790a; border-radius: 4px; padding: 8px 10px; margin-bottom: 8px; background: #fbfcfc; }
.recent-alarm__head { display: flex; align-items: center; justify-content: space-between; font-size: 12px; color: #5b6b74; }
.recent-alarm__content { font-size: 12px; color: #16242b; margin-top: 4px; }
.recent-alarm__time { font-size: 11px; color: #8a98a0; margin-top: 2px; }
.drawer-actions { display: flex; gap: 8px; flex-wrap: wrap; }
</style>
