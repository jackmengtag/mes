<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>库存管理</h2>
        <span class="sub">实时库存 · 出入库流水 · 预警 · 调整与调拨 · 存货核算联动</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="refresh">刷新</el-button>
        <el-button icon="Bottom" v-hasPermi="['mes:stock:adjust']" @click="openAdjust('in')">其他入库</el-button>
        <el-button icon="Top" v-hasPermi="['mes:stock:adjust']" @click="openAdjust('out')">其他出库</el-button>
        <el-button icon="Switch" v-hasPermi="['mes:stock:transfer']" @click="openTransfer">调拨</el-button>
        <el-button type="primary" plain icon="Download" v-hasPermi="['mes:stock:export']" @click="handleExport">导出</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-tabs v-model="activeTab" @tab-change="onTabChange">
      <el-tab-pane label="实时库存" name="stock" />
      <el-tab-pane label="出入库流水" name="txn" />
      <el-tab-pane label="库存预警" name="warning" />
    </el-tabs>

    <el-form :inline="true" class="query-bar">
      <el-form-item label="关键字">
        <el-input v-model="keyword" placeholder="物料 / 批次 / 单号" clearable style="width: 230px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item :label="activeTab === 'txn' ? '流水类型' : '状态'">
        <el-select v-model="status" clearable placeholder="全部" style="width: 150px" @change="handleQuery">
          <el-option v-for="item in statusOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
        <span v-if="activeTab === 'stock' && selectedRows.length" class="selected-tip">已选 {{ selectedRows.length }} 项</span>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="rows" size="small" border @selection-change="onSelectionChange">
      <template v-if="activeTab !== 'txn'">
        <el-table-column type="selection" width="42" />
        <el-table-column prop="materialCode" label="物料编码" width="120" />
        <el-table-column prop="materialName" label="物料名称" min-width="140" show-overflow-tooltip />
        <el-table-column prop="spec" label="规格" width="110" show-overflow-tooltip />
        <el-table-column prop="warehouseName" label="仓库" width="100" />
        <el-table-column prop="batchNo" label="批次" width="110" show-overflow-tooltip />
        <el-table-column label="数量" width="100" align="right">
          <template #default="{ row }">{{ num(row.qty) }}</template>
        </el-table-column>
        <el-table-column label="锁定" width="90" align="right">
          <template #default="{ row }">{{ num(row.lockedQty) }}</template>
        </el-table-column>
        <el-table-column label="安全库存" width="100" align="right">
          <template #default="{ row }">{{ num(row.safetyQty) }}</template>
        </el-table-column>
        <el-table-column label="单价" width="100" align="right">
          <template #default="{ row }">{{ money(row.price) }}</template>
        </el-table-column>
        <el-table-column label="金额" width="120" align="right">
          <template #default="{ row }">{{ money(row.amount) }}</template>
        </el-table-column>
        <el-table-column prop="location" label="库位" width="100" />
        <el-table-column label="状态" width="90">
          <template #default="{ row }"><MesStatusTag :value="row.status" type="stock" /></template>
        </el-table-column>
      </template>

      <template v-else>
        <el-table-column prop="txnCode" label="流水号" width="150" />
        <el-table-column label="类型" width="100">
          <template #default="{ row }">{{ txnTypeText(row.txnType) }}</template>
        </el-table-column>
        <el-table-column label="来源" width="110">
          <template #default="{ row }">{{ sourceTypeText(row.sourceType) }}</template>
        </el-table-column>
        <el-table-column prop="sourceCode" label="来源单号" width="150" show-overflow-tooltip />
        <el-table-column prop="materialName" label="物料" min-width="130" show-overflow-tooltip />
        <el-table-column prop="warehouseCode" label="仓库" width="100" />
        <el-table-column label="数量" width="100" align="right">
          <template #default="{ row }">{{ num(row.qty) }}</template>
        </el-table-column>
        <el-table-column prop="txnTime" label="发生时间" width="160" />
        <el-table-column prop="operator" label="操作人" width="100" />
      </template>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="page" v-model:limit="size" @pagination="load" />

    <!-- 其他入库 / 其他出库 -->
    <el-dialog v-model="adjustOpen" :title="adjustType === 'in' ? '其他入库' : '其他出库'" width="600px" append-to-body>
      <el-form ref="adjustRef" :model="adjust" :rules="adjustRules" label-width="100px">
        <el-form-item label="物料编码" prop="materialCode">
          <el-input v-model="adjust.materialCode" placeholder="请输入物料编码" />
        </el-form-item>
        <el-form-item label="物料名称">
          <el-input v-model="adjust.materialName" placeholder="请输入物料名称" />
        </el-form-item>
        <el-form-item label="仓库" prop="warehouseCode">
          <el-select v-model="adjust.warehouseCode" style="width: 100%">
            <el-option v-for="w in warehouses" :key="w.code" :label="w.name" :value="w.code" />
          </el-select>
        </el-form-item>
        <el-form-item label="批次号">
          <el-input v-model="adjust.batchNo" placeholder="不填默认为 ADJ" />
        </el-form-item>
        <el-form-item label="数量" prop="qty">
          <el-input-number v-model="adjust.qty" :min="1" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="单价">
          <el-input-number v-model="adjust.price" :min="0" :precision="2" style="width: 100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="adjustOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitAdjust">确定</el-button>
      </template>
    </el-dialog>

    <!-- 仓库调拨 -->
    <el-dialog v-model="transferOpen" title="仓库调拨" width="600px" append-to-body>
      <el-form ref="transferRef" :model="transfer" :rules="transferRules" label-width="100px">
        <el-form-item label="物料编码" prop="materialCode">
          <el-input v-model="transfer.materialCode" placeholder="请输入物料编码" />
        </el-form-item>
        <el-form-item label="批次号">
          <el-input v-model="transfer.batchNo" placeholder="请输入批次号" />
        </el-form-item>
        <el-form-item label="源仓库" prop="warehouseCode">
          <el-select v-model="transfer.warehouseCode" style="width: 100%">
            <el-option v-for="w in warehouses" :key="w.code" :label="w.name" :value="w.code" />
          </el-select>
        </el-form-item>
        <el-form-item label="目标仓库" prop="toWarehouse">
          <el-select v-model="transfer.toWarehouse" style="width: 100%">
            <el-option v-for="w in warehouses" :key="w.code" :label="w.name" :value="w.code" />
          </el-select>
        </el-form-item>
        <el-form-item label="数量" prop="qty">
          <el-input-number v-model="transfer.qty" :min="1" :precision="0" style="width: 100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="transferOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitTransfer">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="MesInventory">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'
import { listStock } from '@/api/mes/stock'
import { listStockTxn } from '@/api/mes/stockTxn'
import { adjustStock, transferStock } from '@/api/mes/biz'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'

const { proxy } = getCurrentInstance()
const route = useRoute()

const activeTab = ref('stock')
const loading = ref(false)
const saving = ref(false)
const rows = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(10)
const keyword = ref('')
const status = ref('')
const allStocks = ref([])
const selectedRows = ref([])

const adjustOpen = ref(false)
const transferOpen = ref(false)
const adjustType = ref('in')
const adjust = ref({})
const transfer = ref({})

const warehouses = [
  { code: 'WH-RM', name: '原材料仓' },
  { code: 'WH-SUB', name: '半成品仓' },
  { code: 'WH-FG', name: '成品仓' },
  { code: 'WH-LINE', name: '线边仓' }
]

const adjustRules = {
  materialCode: [{ required: true, message: '请输入物料编码', trigger: 'blur' }],
  warehouseCode: [{ required: true, message: '请选择仓库', trigger: 'change' }],
  qty: [{ required: true, message: '请输入数量', trigger: 'blur' }]
}
const transferRules = {
  materialCode: [{ required: true, message: '请输入物料编码', trigger: 'blur' }],
  warehouseCode: [{ required: true, message: '请选择源仓库', trigger: 'change' }],
  toWarehouse: [{ required: true, message: '请选择目标仓库', trigger: 'change' }],
  qty: [{ required: true, message: '请输入数量', trigger: 'blur' }]
}

const num = v => (v === null || v === undefined ? 0 : Number(v))
const money = v => (v === null || v === undefined ? '0.00' : Number(v).toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 }))

const txnTypeMap = {
  inbound: '入库', outbound: '出库', transfer_in: '调拨入',
  transfer_out: '调拨出', check_in: '盘盈', check_out: '盘亏'
}
const sourceTypeMap = { purchase: '采购到货', sales: '销售出库', other: '其他出入库', transfer: '仓库调拨' }

function txnTypeText(type) {
  return txnTypeMap[type] || type || '-'
}

function sourceTypeText(type) {
  return sourceTypeMap[type] || type || '-'
}

const kpis = computed(() => {
  const list = allStocks.value
  const amount = list.reduce((s, i) => s + num(i.amount), 0)
  const warning = list.filter(i => i.status === 'warning').length
  const shortage = list.filter(i => i.status === 'shortage').length
  const warehouseCount = new Set(list.map(i => i.warehouseCode).filter(Boolean)).size
  return [
    { label: '库存金额', value: (amount / 10000).toFixed(1), unit: '万元', foot: '实时库存合计', tone: '' },
    { label: '预警物料数', value: warning, unit: '项', foot: '低于安全库存', tone: warning > 0 ? 'warn' : 'ok' },
    { label: '缺料物料数', value: shortage, unit: '项', foot: '影响生产齐套', tone: shortage > 0 ? 'danger' : 'ok' },
    { label: '仓库数', value: warehouseCount, unit: '个', foot: '按仓库编码统计', tone: 'accent' }
  ]
})

const statusOptions = computed(() => {
  if (activeTab.value === 'txn') {
    return [
      { value: 'inbound', label: '入库' }, { value: 'outbound', label: '出库' },
      { value: 'transfer_in', label: '调拨入' }, { value: 'transfer_out', label: '调拨出' },
      { value: 'check_in', label: '盘盈' }, { value: 'check_out', label: '盘亏' }
    ]
  }
  if (activeTab.value === 'warning') {
    return [{ value: 'warning', label: '预警' }, { value: 'shortage', label: '缺料' }]
  }
  return [
    { value: 'normal', label: '正常' }, { value: 'warning', label: '预警' },
    { value: 'shortage', label: '缺料' }, { value: 'idle', label: '呆滞' }
  ]
})

function buildQuery() {
  const q = { pageNum: page.value, pageSize: size.value }
  if (keyword.value) q.params = { keyword: keyword.value }
  if (status.value) {
    if (activeTab.value === 'txn') {
      q.txnType = status.value
    } else {
      q.status = status.value
    }
  }
  return q
}

async function loadKpis() {
  const res = await listStock({ pageNum: 1, pageSize: 500 })
  allStocks.value = res.rows || []
}

async function load() {
  loading.value = true
  try {
    if (activeTab.value === 'txn') {
      const res = await listStockTxn(buildQuery())
      rows.value = res.rows || []
      total.value = res.total || 0
    } else if (activeTab.value === 'warning') {
      const q = { pageNum: 1, pageSize: 500 }
      if (keyword.value) q.params = { keyword: keyword.value }
      if (status.value) q.status = status.value
      const res = await listStock(q)
      const filtered = (res.rows || []).filter(i => ['warning', 'shortage'].includes(i.status))
      total.value = filtered.length
      const start = (page.value - 1) * size.value
      rows.value = filtered.slice(start, start + size.value)
    } else {
      const res = await listStock(buildQuery())
      rows.value = res.rows || []
      total.value = res.total || 0
    }
  } finally {
    loading.value = false
  }
}

async function refresh() {
  await loadKpis()
  await load()
}

function onTabChange() {
  status.value = ''
  page.value = 1
  selectedRows.value = []
  if (activeTab.value === 'warning') {
    loadKpis()
  }
  load()
}

function handleQuery() {
  page.value = 1
  load()
}

function resetQuery() {
  keyword.value = ''
  status.value = ''
  page.value = 1
  load()
}

function onSelectionChange(selection) {
  selectedRows.value = selection
}

function openAdjust(type) {
  adjustType.value = type
  const row = selectedRows.value[0] || {}
  adjust.value = {
    materialCode: row.materialCode || '',
    materialName: row.materialName || '',
    warehouseCode: row.warehouseCode || 'WH-RM',
    batchNo: row.batchNo || '',
    qty: 1,
    price: Number(row.price) || 0
  }
  adjustOpen.value = true
}

function submitAdjust() {
  proxy.$refs.adjustRef.validate(async valid => {
    if (!valid) return
    saving.value = true
    try {
      const res = await adjustStock({ type: adjustType.value, ...adjust.value })
      proxy.$modal.msgSuccess(res.msg || '库存调整成功')
      adjustOpen.value = false
      await refresh()
    } finally {
      saving.value = false
    }
  })
}

function openTransfer() {
  const row = selectedRows.value[0] || {}
  transfer.value = {
    materialCode: row.materialCode || '',
    batchNo: row.batchNo || '',
    warehouseCode: row.warehouseCode || 'WH-RM',
    toWarehouse: row.warehouseCode === 'WH-FG' ? 'WH-RM' : 'WH-FG',
    qty: 1
  }
  transferOpen.value = true
}

function submitTransfer() {
  proxy.$refs.transferRef.validate(async valid => {
    if (!valid) return
    if (transfer.value.warehouseCode === transfer.value.toWarehouse) {
      proxy.$modal.msgWarning('源仓库与目标仓库不能相同')
      return
    }
    saving.value = true
    try {
      const res = await transferStock({ ...transfer.value })
      proxy.$modal.msgSuccess(res.msg || '调拨成功')
      transferOpen.value = false
      await refresh()
    } finally {
      saving.value = false
    }
  })
}

function handleExport() {
  proxy.download('mes/stock/export', { ...buildQuery() }, `stock_${Date.now()}.xlsx`)
}

onMounted(() => {
  const kw = route.query.keyword
  if (kw) keyword.value = String(kw)
  refresh()
})
</script>

<style scoped>
.page-head { display: flex; align-items: flex-start; justify-content: space-between; margin-bottom: 12px; }
.page-head h2 { margin: 0; font-size: 20px; color: #16242b; }
.page-head .sub { font-size: 12px; color: #8a98a0; }
.head-actions { display: flex; gap: 8px; flex-wrap: wrap; justify-content: flex-end; }
.query-bar { margin-bottom: 4px; }
.selected-tip { margin-left: 10px; font-size: 12px; color: #2a6f97; }
</style>
