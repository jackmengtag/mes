<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>系统集成</h2>
        <span class="sub">接口监控 · U8C 凭证同步 · 消息日志 · 系统参数与角色权限</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="refresh">刷新</el-button>
        <el-button icon="Download" v-hasPermi="['mes:voucher:export']" @click="handleExport">导出消息日志</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-tabs v-model="activeTab" @tab-change="onTabChange">
      <el-tab-pane label="接口监控" name="interface" />
      <el-tab-pane label="U8C 凭证同步" name="voucher" />
      <el-tab-pane label="消息日志" name="log" />
      <el-tab-pane label="参数与权限" name="params" />
    </el-tabs>

    <el-form v-if="activeTab !== 'params'" :inline="true" class="query-bar">
      <el-form-item label="关键字">
        <el-input v-model="keyword" :placeholder="keywordPlaceholder" clearable style="width: 240px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item v-if="activeTab === 'interface'" label="状态">
        <el-select v-model="status" clearable placeholder="全部" style="width: 150px" @change="handleQuery">
          <el-option label="运行" value="running" />
          <el-option label="报警" value="alarm" />
          <el-option label="停用" value="stopped" />
        </el-select>
      </el-form-item>
      <el-form-item v-else label="U8C 状态">
        <el-select v-model="u8cStatus" clearable placeholder="全部" style="width: 150px" @change="handleQuery">
          <el-option label="待同步" value="pending" />
          <el-option label="已同步" value="success" />
          <el-option label="同步失败" value="failed" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-if="activeTab === 'interface'" v-loading="loading" :data="rows" size="small" border>
      <el-table-column prop="interfaceCode" label="接口编码" width="150" />
      <el-table-column prop="interfaceName" label="接口名称" min-width="220" show-overflow-tooltip />
      <el-table-column prop="targetSystem" label="目标系统" width="120" />
      <el-table-column label="状态" width="100">
        <template #default="{ row }"><MesStatusTag :value="row.status" type="common" /></template>
      </el-table-column>
      <el-table-column prop="lastSync" label="最近同步" width="170">
        <template #default="{ row }">{{ row.lastSync || '-' }}</template>
      </el-table-column>
      <el-table-column label="成功率" width="110" align="right">
        <template #default="{ row }">
          <span class="num" :class="{ danger: num(row.successRate) < 95 }">{{ fmt(row.successRate) }}%</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="120" fixed="right">
        <template #default="{ row }">
          <el-button link type="primary" v-hasPermi="['mes:interface:test']" @click="testConnection(row)">测试连接</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-table v-else-if="activeTab === 'voucher'" v-loading="loading" :data="rows" size="small" border>
      <el-table-column prop="voucherCode" label="凭证号" width="150" />
      <el-table-column label="业务类型" width="120">
        <template #default="{ row }">{{ bizTypeText(row.bizType) }}</template>
      </el-table-column>
      <el-table-column prop="bizCode" label="来源单号" width="150" />
      <el-table-column label="金额" width="130" align="right">
        <template #default="{ row }"><span class="num">{{ money(row.amount) }}</span></template>
      </el-table-column>
      <el-table-column label="记账状态" width="100">
        <template #default="{ row }"><MesStatusTag :value="row.status" type="voucher" /></template>
      </el-table-column>
      <el-table-column label="U8C 状态" width="110">
        <template #default="{ row }"><MesStatusTag :value="row.u8cStatus" type="voucher" /></template>
      </el-table-column>
      <el-table-column prop="syncTime" label="同步时间" width="170">
        <template #default="{ row }">{{ row.syncTime || '-' }}</template>
      </el-table-column>
      <el-table-column prop="message" label="同步信息" min-width="200" show-overflow-tooltip>
        <template #default="{ row }">{{ row.message || '-' }}</template>
      </el-table-column>
      <el-table-column label="操作" width="110" fixed="right">
        <template #default="{ row }">
          <el-button v-if="row.u8cStatus !== 'success'" link type="primary" v-hasPermi="['mes:voucher:sync']" @click="repush(row)">重新推送</el-button>
          <span v-else class="muted">已同步</span>
        </template>
      </el-table-column>
    </el-table>

    <el-table v-else-if="activeTab === 'log'" v-loading="loading" :data="logRows" size="small" border>
      <el-table-column prop="voucherCode" label="凭证号" width="150" />
      <el-table-column prop="bizCode" label="来源单号" width="150" />
      <el-table-column prop="message" label="同步信息" min-width="240" show-overflow-tooltip>
        <template #default="{ row }">{{ row.message || '-' }}</template>
      </el-table-column>
      <el-table-column prop="syncTime" label="同步时间" width="170">
        <template #default="{ row }">{{ row.syncTime || '-' }}</template>
      </el-table-column>
      <el-table-column label="状态" width="110">
        <template #default="{ row }"><MesStatusTag :value="row.u8cStatus" type="voucher" /></template>
      </el-table-column>
      <el-table-column label="操作" width="110" fixed="right">
        <template #default="{ row }">
          <el-button v-if="row.u8cStatus === 'failed'" link type="primary" v-hasPermi="['mes:voucher:sync']" @click="repush(row)">重试</el-button>
          <span v-else class="muted">-</span>
        </template>
      </el-table-column>
    </el-table>

    <template v-else>
      <el-row :gutter="12">
        <el-col :span="12">
          <el-card shadow="never" class="panel">
            <template #header><span>系统参数</span></template>
            <el-table :data="paramRows" size="small" border>
              <el-table-column prop="name" label="参数名" width="150" />
              <el-table-column prop="key" label="参数键" min-width="190" show-overflow-tooltip />
              <el-table-column prop="value" label="值" width="110" />
              <el-table-column prop="desc" label="说明" min-width="170" show-overflow-tooltip />
            </el-table>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card shadow="never" class="panel">
            <template #header><span>角色权限</span></template>
            <el-table :data="roleRows" size="small" border>
              <el-table-column prop="role" label="角色" width="130" />
              <el-table-column prop="scope" label="数据范围" width="110" />
              <el-table-column prop="permission" label="功能权限" min-width="220" show-overflow-tooltip />
            </el-table>
          </el-card>
        </el-col>
      </el-row>
    </template>

    <pagination v-show="activeTab !== 'log' && activeTab !== 'params' && total > 0" :total="total" v-model:page="page" v-model:limit="size" @pagination="load" />
  </div>
</template>

<script setup name="MesSystem">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { listInterface } from '@/api/mes/integration'
import { listVoucher } from '@/api/mes/voucher'
import { testInterface, syncVoucher } from '@/api/mes/biz'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'

const { proxy } = getCurrentInstance()

const activeTab = ref('interface')
const loading = ref(false)
const rows = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(10)
const keyword = ref('')
const status = ref('')
const u8cStatus = ref('')

const allInterfaces = ref([])
const allVouchers = ref([])

const bizTypeMap = {
  purchase_in: '采购入库',
  product_in: '成品入库',
  sales_out: '销售出库',
  production_pick: '生产领料',
  other: '其他',
  check_adjust: '盘点调整'
}

const paramRows = [
  { name: '看板自动刷新', key: 'mes.board.refresh.seconds', value: '60', desc: '车间大屏自动刷新间隔（秒）' },
  { name: 'U8C 接口地址', key: 'mes.u8c.endpoint', value: 'http://u8c:8080/api', desc: 'U8C 凭证同步服务地址' },
  { name: 'U8C 自动同步', key: 'mes.u8c.sync.enabled', value: 'true', desc: '记账后是否自动推送 U8C' },
  { name: '计划锁定天数', key: 'mes.plan.lock.days', value: '3', desc: 'D+3 锁定，D+7 滚动' },
  { name: 'OEE 目标值', key: 'mes.oee.target', value: '80', desc: '低于目标值高亮预警' },
  { name: '报警自动处理', key: 'mes.alarm.autoHandle', value: 'false', desc: '是否允许系统自动关闭报警' }
]

const roleRows = [
  { role: '系统管理员', scope: '全部工厂', permission: '基础数据 / 系统集成 / 参数与角色' },
  { role: '制造运营经理', scope: '全部工厂', permission: '生产总览 / 车间大屏 / 统计分析' },
  { role: '车间主管', scope: '本车间', permission: '生产执行 / 质量管理 / 设备管理' },
  { role: '设备工程师', scope: '本车间', permission: '设备台账 / 报警处理 / 点检记录' },
  { role: '成本会计', scope: '集团', permission: '存货核算 / 系统集成 / U8C 凭证同步' }
]

const num = v => (v === null || v === undefined || v === '' ? 0 : Number(v))
const fmt = v => num(v).toFixed(1)
const money = v => num(v).toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 })

const keywordPlaceholder = computed(() => {
  if (activeTab.value === 'interface') return '接口编码 / 名称'
  return '凭证号 / 来源单号 / 同步信息'
})

const kpis = computed(() => [
  { label: '接口总数', value: allInterfaces.value.length, unit: '个', foot: '集成接口在册', tone: '' },
  { label: '运行中', value: allInterfaces.value.filter(i => i.status === 'running').length, unit: '个', foot: '状态为运行', tone: 'ok' },
  { label: '异常接口', value: allInterfaces.value.filter(i => i.status === 'alarm').length, unit: '个', foot: '状态为报警', tone: 'danger' },
  { label: 'U8C 失败凭证', value: allVouchers.value.filter(i => i.u8cStatus === 'failed').length, unit: '张', foot: '需重新推送', tone: 'danger' }
])

const logRows = computed(() => {
  const kw = keyword.value.trim()
  return allVouchers.value.filter(i => {
    if (u8cStatus.value && i.u8cStatus !== u8cStatus.value) return false
    if (!kw) return true
    return (i.voucherCode || '').includes(kw) || (i.bizCode || '').includes(kw) || (i.message || '').includes(kw)
  })
})

function bizTypeText(v) {
  return bizTypeMap[v] || v || '-'
}

function buildQuery() {
  const q = { pageNum: page.value, pageSize: size.value }
  if (keyword.value) q.params = { keyword: keyword.value }
  if (activeTab.value === 'interface' && status.value) q.status = status.value
  if (activeTab.value !== 'interface' && u8cStatus.value) q.u8cStatus = u8cStatus.value
  return q
}

async function load() {
  loading.value = true
  try {
    if (activeTab.value === 'interface') {
      const res = await listInterface(buildQuery())
      rows.value = res.rows || []
      total.value = res.total || 0
    } else if (activeTab.value === 'voucher') {
      const res = await listVoucher(buildQuery())
      rows.value = res.rows || []
      total.value = res.total || 0
    } else {
      rows.value = []
      total.value = 0
    }
  } finally {
    loading.value = false
  }
}

async function loadMeta() {
  const i = await listInterface({ pageNum: 1, pageSize: 500 })
  allInterfaces.value = i.rows || []
  const v = await listVoucher({ pageNum: 1, pageSize: 500 })
  allVouchers.value = v.rows || []
}

function refresh() {
  loadMeta()
  load()
}

function onTabChange() {
  keyword.value = ''
  status.value = ''
  u8cStatus.value = ''
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
  u8cStatus.value = ''
  page.value = 1
  load()
}

async function testConnection(row) {
  const res = await testInterface(row.interfaceId)
  proxy.$modal.msgSuccess(res.msg || '连接正常')
  await loadMeta()
  load()
}

async function repush(row) {
  const res = await syncVoucher(row.voucherId)
  proxy.$modal.msgSuccess(res.msg || '同步成功')
  await loadMeta()
  load()
}

function handleExport() {
  proxy.download('mes/voucher/export', buildQuery(), `message_log_${Date.now()}.xlsx`)
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
.num { font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.danger { color: #b23a2e; }
.muted { color: #8a98a0; font-size: 12px; }
</style>
