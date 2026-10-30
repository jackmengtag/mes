<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>供应链总览</h2>
        <span class="sub">采购 · 库存 · 生产 · 销售 · U8C 凭证协同运行状态</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="load">刷新</el-button>
        <el-button type="primary" plain @click="$router.push('/mes/overview')">生产总览</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-card shadow="never" class="panel">
      <template #header><span>业务总图</span></template>
      <div class="flow">
        <div v-for="(node, i) in nodes" :key="node.name" class="flow-node" :class="'is-' + node.tone" @click="go(node.path)">
          <div class="flow-node__name">{{ node.name }}</div>
          <div class="flow-node__meta">{{ node.meta }}</div>
          <div class="flow-node__value">{{ node.value }}<em>{{ node.unit }}</em></div>
          <div v-if="i < nodes.length - 1" class="flow-arrow">→</div>
        </div>
      </div>
    </el-card>

    <el-row :gutter="12" style="margin-top: 12px">
      <el-col :span="16">
        <el-card shadow="never" class="panel">
          <template #header><span>采购 / 销售月度趋势</span></template>
          <MesChart :option="trendOption" :height="248" />
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="never" class="panel">
          <template #header><span>供应链待办</span></template>
          <el-table :data="pending" size="small">
            <el-table-column prop="collabCode" label="协同单号" width="130" />
            <el-table-column prop="supplierName" label="供应商" min-width="150" show-overflow-tooltip />
            <el-table-column label="类型" width="90">
              <template #default="{ row }">{{ collabTypeText(row.collabType) }}</template>
            </el-table-column>
            <el-table-column label="状态" width="90">
              <template #default="{ row }"><MesStatusTag :value="row.status" type="collab" /></template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="12" style="margin-top: 12px">
      <el-col :span="12">
        <el-card shadow="never" class="panel">
          <template #header><span>交付异常</span></template>
          <el-table :data="exceptions" size="small" class="link-table" @row-click="row => goPurchase(row)">
            <el-table-column prop="collabCode" label="协同单号" width="130" />
            <el-table-column prop="supplierName" label="供应商" min-width="150" show-overflow-tooltip />
            <el-table-column prop="materialName" label="物料" min-width="110" show-overflow-tooltip />
            <el-table-column prop="planDate" label="计划日期" width="100" />
            <el-table-column label="状态" width="90">
              <template #default="{ row }"><MesStatusTag :value="row.status" type="collab" /></template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="never" class="panel">
          <template #header><span>库存预警</span></template>
          <el-table :data="warnings" size="small" class="link-table" @row-click="row => goStock(row)">
            <el-table-column prop="materialCode" label="物料编码" width="120" />
            <el-table-column prop="materialName" label="物料名称" min-width="130" show-overflow-tooltip />
            <el-table-column prop="warehouseName" label="仓库" width="100" />
            <el-table-column label="库存 / 安全" width="120" align="right">
              <template #default="{ row }">{{ num(row.qty) }} / {{ num(row.safetyQty) }}</template>
            </el-table-column>
            <el-table-column label="状态" width="90">
              <template #default="{ row }"><MesStatusTag :value="row.status" type="stock" /></template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>

    <el-card shadow="never" class="panel" style="margin-top: 12px">
      <template #header><span>U8C 凭证同步</span></template>
      <el-table :data="vouchers" size="small">
        <el-table-column prop="voucherCode" label="凭证号" width="150" />
        <el-table-column label="业务类型" width="110">
          <template #default="{ row }">{{ bizTypeText(row.bizType) }}</template>
        </el-table-column>
        <el-table-column label="金额" width="130" align="right">
          <template #default="{ row }">{{ money(row.amount) }}</template>
        </el-table-column>
        <el-table-column label="记账状态" width="100">
          <template #default="{ row }"><MesStatusTag :value="row.status" type="voucher" /></template>
        </el-table-column>
        <el-table-column label="同步状态" width="100">
          <template #default="{ row }"><MesStatusTag :value="row.u8cStatus" type="voucher" /></template>
        </el-table-column>
        <el-table-column prop="message" label="信息" min-width="180" show-overflow-tooltip />
        <el-table-column prop="syncTime" label="同步时间" width="140" />
        <el-table-column label="操作" width="100" fixed="right">
          <template #default="{ row }">
            <el-button
              v-if="row.u8cStatus !== 'success'"
              link
              type="primary"
              :loading="syncingId === row.voucherId"
              v-hasPermi="['mes:voucher:sync']"
              @click="resync(row)"
            >重新推送</el-button>
            <span v-else class="muted">已同步</span>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup name="MesScm">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import { getScm } from '@/api/mes/dashboard'
import { syncVoucher } from '@/api/mes/biz'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'
import MesChart from '../components/MesChart.vue'

const { proxy } = getCurrentInstance()
const router = useRouter()

const loading = ref(false)
const syncingId = ref(null)
const data = ref({})

const num = v => (v === null || v === undefined ? 0 : Number(v))
const money = v => (v === null || v === undefined ? '0.00' : Number(v).toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 }))

const kpis = computed(() => {
  const k = data.value.kpis || {}
  return [
    { label: '采购在途数量', value: num(k.purchaseInTransit), unit: '件', foot: '采购订单未到货', tone: 'warn' },
    { label: '待交付订单', value: num(k.pendingDelivery), unit: '单', foot: '已下单 / 部分 / 逾期', tone: 'accent' },
    { label: '库存金额', value: (num(k.stockAmount) / 10000).toFixed(1), unit: '万元', foot: '实时库存合计', tone: '' },
    { label: '交付准时率', value: num(k.onTimeRate).toFixed(1), unit: '%', foot: '供应商平均准时率', tone: num(k.onTimeRate) >= 95 ? 'ok' : 'warn' },
    { label: '本月采购额', value: (num(k.monthPurchase) / 10000).toFixed(1), unit: '万元', foot: '按订单日期统计', tone: '' },
    { label: '本月销售额', value: (num(k.monthSales) / 10000).toFixed(1), unit: '万元', foot: '按订单日期统计', tone: 'ok' },
    { label: 'U8C 待同步', value: num(k.u8cPending), unit: '条', foot: '其中失败 ' + num(k.u8cFailed) + ' 条', tone: num(k.u8cPending) > 0 ? 'danger' : 'ok' },
    { label: '交付异常数', value: num(k.exceptions), unit: '项', foot: '异常 / 逾期协同单', tone: 'danger' }
  ]
})

const nodes = computed(() => {
  const k = data.value.kpis || {}
  return [
    { name: '供应商协同', meta: '交付计划 · 异常', value: (data.value.pending || []).length, unit: '项待办', tone: 'accent', path: '/mes/collab' },
    { name: '采购', meta: '订单 · 到货 · 入库', value: num(k.purchaseInTransit), unit: '件在途', tone: '', path: '/mes/purchase' },
    { name: '库存', meta: '实时库存 · 预警', value: (num(k.stockAmount) / 10000).toFixed(1), unit: '万元', tone: 'warn', path: '/mes/inventory' },
    { name: '生产制造', meta: '计划 · 排产 · 报工', value: 'MES', unit: '车间执行', tone: 'ok', path: '/mes/manufacturing' },
    { name: '销售', meta: '订单 · 出库 · 开票', value: (num(k.monthSales) / 10000).toFixed(1), unit: '万元', tone: 'accent', path: '/mes/sales' },
    { name: 'U8C', meta: '存货凭证 · 同步', value: num(k.u8cPending), unit: '条待同步', tone: 'danger', path: '/mes/system' }
  ]
})

const trendMonths = computed(() => {
  const set = new Set([
    ...(data.value.purchaseTrend || []).map(i => i.m),
    ...(data.value.salesTrend || []).map(i => i.m)
  ])
  return Array.from(set).sort()
})

const trendOption = computed(() => {
  const pMap = {}
  const sMap = {}
  ;(data.value.purchaseTrend || []).forEach(i => { pMap[i.m] = Number(i.v) || 0 })
  ;(data.value.salesTrend || []).forEach(i => { sMap[i.m] = Number(i.v) || 0 })
  const months = trendMonths.value
  return {
    tooltip: { trigger: 'axis' },
    legend: { right: 0, top: 0, itemWidth: 10, itemHeight: 10 },
    grid: { left: 44, right: 16, top: 34, bottom: 26 },
    xAxis: { type: 'category', data: months, axisLine: { lineStyle: { color: '#c9d3d9' } }, axisLabel: { color: '#5b6b74' } },
    yAxis: { type: 'value', name: '万元', nameTextStyle: { color: '#8a98a0' }, splitLine: { lineStyle: { color: '#eef2f4' } }, axisLabel: { color: '#5b6b74' } },
    series: [
      { name: '采购额', type: 'bar', barMaxWidth: 22, itemStyle: { color: '#12495f' }, data: months.map(m => +((pMap[m] || 0) / 10000).toFixed(1)) },
      { name: '销售额', type: 'bar', barMaxWidth: 22, itemStyle: { color: '#e07b1a' }, data: months.map(m => +((sMap[m] || 0) / 10000).toFixed(1)) }
    ]
  }
})

const pending = computed(() => data.value.pending || [])
const exceptions = computed(() => data.value.exceptions || [])
const warnings = computed(() => data.value.warnings || [])
const vouchers = computed(() => data.value.vouchers || [])

const collabTypeMap = { delivery: '交付计划', shipping: '送货', sample: '样品', exception: '异常', material: '车间物料' }
const bizTypeMap = { purchase_in: '采购入库', product_in: '成品入库', sales_out: '销售出库', production_pick: '生产领料', other: '其他出入库', check_adjust: '盘点调整' }

function collabTypeText(type) {
  return collabTypeMap[type] || type || '-'
}

function bizTypeText(type) {
  return bizTypeMap[type] || type || '-'
}

function go(path) {
  router.push(path)
}

function goPurchase(row) {
  router.push('/mes/purchase?keyword=' + encodeURIComponent(row.supplierName || ''))
}

function goStock(row) {
  router.push('/mes/inventory?keyword=' + encodeURIComponent(row.materialCode || ''))
}

async function resync(row) {
  syncingId.value = row.voucherId
  try {
    const res = await syncVoucher(row.voucherId)
    proxy.$modal.msgSuccess(res.msg || '同步成功')
    await load()
  } finally {
    syncingId.value = null
  }
}

async function load() {
  loading.value = true
  try {
    const res = await getScm()
    data.value = res.data || {}
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
.flow-node.is-danger { border-top-color: #b23a2e; }
.flow-node.is-accent { border-top-color: #e07b1a; }
.flow-node__name { font-size: 14px; font-weight: 600; color: #16242b; }
.flow-node__meta { font-size: 11px; color: #8a98a0; margin: 2px 0 6px; }
.flow-node__value { font-size: 22px; font-weight: 600; color: #12495f; font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.flow-node__value em { font-size: 11px; font-style: normal; color: #8a98a0; margin-left: 3px; }
.flow-arrow { position: absolute; right: -15px; top: 50%; transform: translateY(-50%); color: #b9c5cc; }
.muted { font-size: 12px; color: #8a98a0; }
.link-table :deep(.el-table__row) { cursor: pointer; }
</style>
