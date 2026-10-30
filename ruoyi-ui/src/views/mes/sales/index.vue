<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>销售管理</h2>
        <span class="sub">客户档案 · 销售订单 · 出库 · 库存与凭证联动</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="load">刷新</el-button>
        <el-button type="primary" icon="Plus" v-hasPermi="['mes:sales:add']" @click="openOrder">新建销售订单</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-tabs v-model="activeTab" @tab-change="onTabChange">
      <el-tab-pane label="销售订单" name="order" />
      <el-tab-pane label="销售出库" name="shipment" />
      <el-tab-pane label="客户档案" name="customer" />
    </el-tabs>

    <el-form :inline="true" class="query-bar">
      <el-form-item label="关键字">
        <el-input v-model="keyword" placeholder="单号 / 客户 / 产品" clearable style="width: 230px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="状态">
        <el-select v-model="status" clearable placeholder="全部" style="width: 150px" @change="handleQuery">
          <el-option v-for="item in statusOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="rows" size="small" border>
      <template v-if="activeTab === 'order'">
        <el-table-column prop="orderCode" label="销售订单号" width="150" />
        <el-table-column prop="customerName" label="客户" min-width="170" show-overflow-tooltip />
        <el-table-column prop="productName" label="产品" min-width="160" show-overflow-tooltip />
        <el-table-column label="订购 / 已发货" width="130">
          <template #default="{ row }">{{ num(row.qty) }} / {{ num(row.shippedQty) }}</template>
        </el-table-column>
        <el-table-column label="金额" width="130" align="right">
          <template #default="{ row }">{{ money(row.amount) }}</template>
        </el-table-column>
        <el-table-column label="状态" width="110">
          <template #default="{ row }"><MesStatusTag :value="row.status" type="sales" /></template>
        </el-table-column>
        <el-table-column prop="orderDate" label="下单日期" width="110" />
        <el-table-column prop="deliveryDate" label="交付日期" width="110" />
        <el-table-column label="操作" width="90" fixed="right">
          <template #default="{ row }">
            <el-button link type="primary" v-hasPermi="['mes:shipment:add']" @click="openShip(row)">出库</el-button>
          </template>
        </el-table-column>
      </template>

      <template v-else-if="activeTab === 'shipment'">
        <el-table-column prop="shipmentCode" label="出库单号" width="150" />
        <el-table-column prop="orderCode" label="销售订单" width="150" />
        <el-table-column prop="customerName" label="客户" min-width="170" show-overflow-tooltip />
        <el-table-column prop="productName" label="产品" min-width="150" show-overflow-tooltip />
        <el-table-column label="出库数量" width="100" align="right">
          <template #default="{ row }">{{ num(row.qty) }}</template>
        </el-table-column>
        <el-table-column label="金额" width="130" align="right">
          <template #default="{ row }">{{ money(row.amount) }}</template>
        </el-table-column>
        <el-table-column prop="warehouseCode" label="出库仓库" width="100" />
        <el-table-column prop="shipDate" label="出库日期" width="110" />
        <el-table-column label="状态" width="100">
          <template #default="{ row }"><MesStatusTag :value="row.status" type="sales" /></template>
        </el-table-column>
      </template>

      <template v-else>
        <el-table-column prop="customerCode" label="客户编码" width="130" />
        <el-table-column prop="customerName" label="客户名称" min-width="200" show-overflow-tooltip />
        <el-table-column prop="contact" label="联系人" width="100" />
        <el-table-column prop="phone" label="电话" width="150" />
        <el-table-column label="信用额度" width="140" align="right">
          <template #default="{ row }">{{ money(row.creditLimit) }}</template>
        </el-table-column>
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="customerTone(row.status)" size="small">{{ customerText(row.status) }}</el-tag>
          </template>
        </el-table-column>
      </template>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="page" v-model:limit="size" @pagination="load" />

    <!-- 新建销售订单 -->
    <el-dialog v-model="orderOpen" title="新建销售订单" width="620px" append-to-body>
      <el-form ref="orderRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="订单号" prop="orderCode">
          <el-input v-model="form.orderCode" placeholder="如 SO-261030-001" />
        </el-form-item>
        <el-form-item label="客户" prop="customerName">
          <el-select v-model="form.customerName" filterable placeholder="请选择客户" style="width: 100%" @change="pickCustomer">
            <el-option v-for="c in customers" :key="c.customerId" :label="c.customerName" :value="c.customerName" />
          </el-select>
        </el-form-item>
        <el-form-item label="产品" prop="productCode">
          <el-select v-model="form.productCode" filterable placeholder="请选择产品" style="width: 100%" @change="pickProduct">
            <el-option v-for="m in products" :key="m.materialId" :label="m.materialName + '（' + m.materialCode + '）'" :value="m.materialCode" />
          </el-select>
        </el-form-item>
        <el-form-item label="数量" prop="qty">
          <el-input-number v-model="form.qty" :min="1" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="单价" prop="price">
          <el-input-number v-model="form.price" :min="0.01" :precision="2" style="width: 100%" />
        </el-form-item>
        <el-form-item label="下单日期">
          <el-date-picker v-model="form.orderDate" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
        <el-form-item label="交付日期">
          <el-date-picker v-model="form.deliveryDate" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="orderOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitOrder">确定</el-button>
      </template>
    </el-dialog>

    <!-- 销售出库 -->
    <el-dialog v-model="shipOpen" title="销售出库" width="560px" append-to-body>
      <el-form ref="shipRef" :model="ship" :rules="shipRules" label-width="100px">
        <el-form-item label="销售订单">
          <el-input v-model="ship.orderCode" disabled />
        </el-form-item>
        <el-form-item label="客户">
          <el-input v-model="ship.customerName" disabled />
        </el-form-item>
        <el-form-item label="产品">
          <el-input v-model="ship.productName" disabled />
        </el-form-item>
        <el-form-item label="剩余可出库">
          <el-input :model-value="ship.remain" disabled />
        </el-form-item>
        <el-form-item label="出库数量" prop="qty">
          <el-input-number v-model="ship.qty" :min="1" :max="ship.remain" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="出库仓库">
          <el-select v-model="ship.warehouseCode" style="width: 100%">
            <el-option label="成品仓" value="WH-FG" />
            <el-option label="半成品仓" value="WH-SUB" />
            <el-option label="原材料仓" value="WH-RM" />
          </el-select>
        </el-form-item>
      </el-form>
      <el-alert type="info" :closable="false" show-icon title="出库将扣减成品库存，并生成存货凭证与 U8C 待同步记录。" />
      <template #footer>
        <el-button @click="shipOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitShip">确认出库</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="MesSales">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { listSalesOrder, addSalesOrder } from '@/api/mes/sales'
import { listShipment } from '@/api/mes/shipment'
import { listCustomer } from '@/api/mes/customer'
import { listMaterial } from '@/api/mes/material'
import { shipOrder } from '@/api/mes/biz'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'

const { proxy } = getCurrentInstance()

const activeTab = ref('order')
const loading = ref(false)
const saving = ref(false)
const rows = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(10)
const keyword = ref('')
const status = ref('')
const allOrders = ref([])
const customers = ref([])
const products = ref([])

const orderOpen = ref(false)
const shipOpen = ref(false)
const form = ref({})
const ship = ref({})

const rules = {
  orderCode: [{ required: true, message: '请输入订单号', trigger: 'blur' }],
  customerName: [{ required: true, message: '请选择客户', trigger: 'change' }],
  productCode: [{ required: true, message: '请选择产品', trigger: 'change' }],
  qty: [{ required: true, message: '请输入数量', trigger: 'blur' }],
  price: [{ required: true, message: '请输入单价', trigger: 'blur' }]
}
const shipRules = {
  qty: [{ required: true, message: '请输入出库数量', trigger: 'blur' }]
}

const num = v => (v === null || v === undefined ? 0 : Number(v))
const money = v => (v === null || v === undefined ? '0.00' : Number(v).toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 }))

const kpis = computed(() => {
  const list = allOrders.value
  const month = new Date().toISOString().slice(0, 7)
  const monthAmount = list.filter(i => (i.orderDate || '').slice(0, 7) === month).reduce((s, i) => s + num(i.amount), 0)
  const pending = list.filter(i => Math.max(num(i.qty) - num(i.shippedQty), 0) > 0).length
  const shipped = list.reduce((s, i) => s + num(i.shippedQty), 0)
  const customerCount = new Set(list.map(i => i.customerName).filter(Boolean)).size
  return [
    { label: '本月销售额', value: (monthAmount / 10000).toFixed(1), unit: '万元', foot: '按订单日期统计', tone: 'ok' },
    { label: '待发货订单', value: pending, unit: '单', foot: '订购数量未发完', tone: pending > 0 ? 'warn' : 'ok' },
    { label: '已发货数量', value: shipped, unit: '件', foot: '订单累计已发货', tone: '' },
    { label: '客户数', value: customerCount, unit: '家', foot: '订单覆盖客户', tone: 'accent' }
  ]
})

const statusOptions = computed(() => {
  if (activeTab.value === 'order') {
    return [
      { value: 'pending', label: '待发货' }, { value: 'producing', label: '生产中' },
      { value: 'partial', label: '部分发货' }, { value: 'shipped', label: '已发货' },
      { value: 'invoiced', label: '已开票' }, { value: 'received', label: '已收款' }
    ]
  }
  if (activeTab.value === 'shipment') {
    return [{ value: 'shipped', label: '已发货' }]
  }
  return [{ value: '0', label: '正常' }, { value: '1', label: '停用' }]
})

function buildQuery() {
  const q = { pageNum: page.value, pageSize: size.value }
  if (keyword.value) q.params = { keyword: keyword.value }
  if (status.value) q.status = status.value
  return q
}

async function load() {
  loading.value = true
  try {
    if (activeTab.value === 'order') {
      const res = await listSalesOrder(buildQuery())
      rows.value = res.rows || []
      total.value = res.total || 0
    } else if (activeTab.value === 'shipment') {
      const res = await listShipment(buildQuery())
      rows.value = res.rows || []
      total.value = res.total || 0
    } else {
      const res = await listCustomer(buildQuery())
      rows.value = res.rows || []
      total.value = res.total || 0
    }
  } finally {
    loading.value = false
  }
}

async function loadMeta() {
  const so = await listSalesOrder({ pageNum: 1, pageSize: 500 })
  allOrders.value = so.rows || []
  const c = await listCustomer({ pageNum: 1, pageSize: 200 })
  customers.value = c.rows || []
  const m = await listMaterial({ pageNum: 1, pageSize: 500 })
  products.value = (m.rows || []).filter(i => i.productFlag === '1')
}

function onTabChange() {
  status.value = ''
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
  page.value = 1
  load()
}

function openOrder() {
  form.value = {
    orderCode: '',
    customerName: '',
    customerCode: '',
    productCode: '',
    productName: '',
    qty: 100,
    price: 1,
    orderDate: new Date().toISOString().slice(0, 10),
    deliveryDate: ''
  }
  orderOpen.value = true
}

function pickCustomer(name) {
  const hit = customers.value.find(i => i.customerName === name)
  form.value.customerCode = hit ? hit.customerCode : ''
}

function pickProduct(code) {
  const hit = products.value.find(i => i.materialCode === code)
  if (hit) {
    form.value.productName = hit.materialName
    form.value.price = Number(hit.price) || 1
  }
}

function submitOrder() {
  proxy.$refs.orderRef.validate(async valid => {
    if (!valid) return
    if (!form.value.deliveryDate) {
      proxy.$modal.msgWarning('请选择交付日期')
      return
    }
    saving.value = true
    try {
      const payload = { ...form.value }
      payload.amount = Number(payload.qty) * Number(payload.price)
      payload.status = 'pending'
      payload.shippedQty = 0
      await addSalesOrder(payload)
      proxy.$modal.msgSuccess('销售订单已创建')
      orderOpen.value = false
      await loadMeta()
      activeTab.value = 'order'
      load()
    } finally {
      saving.value = false
    }
  })
}

function openShip(row) {
  const remain = Math.max(num(row.qty) - num(row.shippedQty), 0)
  if (remain <= 0) {
    proxy.$modal.msgWarning('该订单已全部发货')
    return
  }
  ship.value = {
    orderCode: row.orderCode,
    customerName: row.customerName,
    productName: row.productName,
    remain,
    qty: remain,
    warehouseCode: 'WH-FG'
  }
  shipOpen.value = true
}

function submitShip() {
  proxy.$refs.shipRef.validate(async valid => {
    if (!valid) return
    if (!ship.value.qty || ship.value.qty <= 0) {
      proxy.$modal.msgWarning('出库数量必须大于 0')
      return
    }
    if (ship.value.qty > ship.value.remain) {
      proxy.$modal.msgError('出库数量不得超过剩余可出库数量 ' + ship.value.remain)
      return
    }
    saving.value = true
    try {
      const res = await shipOrder({
        orderCode: ship.value.orderCode,
        qty: ship.value.qty,
        warehouseCode: ship.value.warehouseCode
      })
      proxy.$modal.msgSuccess(res.msg || '出库成功')
      shipOpen.value = false
      await loadMeta()
      load()
    } finally {
      saving.value = false
    }
  })
}

function customerText(status) {
  return { '0': '正常', '1': '停用' }[status] || status || '-'
}

function customerTone(status) {
  return { '0': 'success', '1': 'info' }[status] || 'info'
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
</style>
