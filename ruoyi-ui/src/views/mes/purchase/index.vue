<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>采购管理</h2>
        <span class="sub">供应商档案 · 采购订单 · 到货与入库 · 库存联动</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="load">刷新</el-button>
        <el-button type="primary" icon="Plus" v-hasPermi="['mes:purchase:add']" @click="openOrder()">新建采购订单</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-tabs v-model="activeTab" @tab-change="onTabChange">
      <el-tab-pane label="采购订单" name="order" />
      <el-tab-pane label="到货与入库" name="arrival" />
      <el-tab-pane label="供应商档案" name="supplier" />
    </el-tabs>

    <el-form :inline="true" class="query-bar">
      <el-form-item label="关键字">
        <el-input v-model="keyword" placeholder="单号 / 供应商 / 物料" clearable style="width: 230px" @keyup.enter="handleQuery" />
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
        <el-table-column prop="orderCode" label="采购订单号" width="160" />
        <el-table-column prop="supplierName" label="供应商" min-width="180" show-overflow-tooltip />
        <el-table-column prop="materialName" label="物料" min-width="140" show-overflow-tooltip />
        <el-table-column label="订购 / 到货" width="130">
          <template #default="{ row }">{{ num(row.qty) }} / {{ num(row.receivedQty) }}</template>
        </el-table-column>
        <el-table-column label="单价" width="100" align="right">
          <template #default="{ row }">{{ money(row.price) }}</template>
        </el-table-column>
        <el-table-column label="金额" width="120" align="right">
          <template #default="{ row }">{{ money(row.amount) }}</template>
        </el-table-column>
        <el-table-column label="状态" width="110">
          <template #default="{ row }"><MesStatusTag :value="row.status" type="purchase" /></template>
        </el-table-column>
        <el-table-column prop="expectDate" label="期望到货" width="110" />
        <el-table-column label="操作" width="150" fixed="right">
          <template #default="{ row }">
            <el-button link type="primary" v-hasPermi="['mes:arrival:add']" @click="openArrival(row)">到货登记</el-button>
            <el-button link type="primary" v-hasPermi="['mes:purchase:edit']" @click="openOrder(row)">编辑</el-button>
          </template>
        </el-table-column>
      </template>

      <template v-else-if="activeTab === 'arrival'">
        <el-table-column prop="arrivalCode" label="到货单号" width="150" />
        <el-table-column prop="orderCode" label="采购订单" width="150" />
        <el-table-column prop="supplierName" label="供应商" min-width="170" show-overflow-tooltip />
        <el-table-column prop="materialName" label="物料" min-width="120" />
        <el-table-column label="到货 / 合格 / 拒收" width="160">
          <template #default="{ row }">{{ num(row.qty) }} / {{ num(row.qualifiedQty) }} / {{ num(row.rejectedQty) }}</template>
        </el-table-column>
        <el-table-column prop="warehouseCode" label="仓库" width="100" />
        <el-table-column prop="arrivalDate" label="到货日期" width="110" />
        <el-table-column label="状态" width="100">
          <template #default="{ row }"><MesStatusTag :value="row.status" /></template>
        </el-table-column>
      </template>

      <template v-else>
        <el-table-column prop="supplierCode" label="编码" width="100" />
        <el-table-column prop="supplierName" label="供应商名称" min-width="200" show-overflow-tooltip />
        <el-table-column prop="category" label="分类" width="90" />
        <el-table-column prop="level" label="等级" width="70" />
        <el-table-column prop="contact" label="联系人" width="90" />
        <el-table-column prop="phone" label="电话" width="140" />
        <el-table-column label="准时率" width="90">
          <template #default="{ row }">{{ row.onTimeRate || 0 }}%</template>
        </el-table-column>
        <el-table-column label="综合评分" width="90">
          <template #default="{ row }">{{ row.score || 0 }}</template>
        </el-table-column>
        <el-table-column label="状态" width="110">
          <template #default="{ row }">
            <el-tag :type="supplierTone(row.status)" size="small">{{ supplierText(row.status) }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="140" fixed="right">
          <template #default="{ row }">
            <el-button v-if="row.status === '0'" link type="primary" v-hasPermi="['mes:supplier:audit']" @click="audit(row)">审核通过</el-button>
            <el-button link type="primary" @click="goQuality">绩效</el-button>
          </template>
        </el-table-column>
      </template>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="page" v-model:limit="size" @pagination="load" />

    <!-- 采购订单 -->
    <el-dialog v-model="orderOpen" :title="form.purchaseId ? '编辑采购订单' : '新建采购订单'" width="620px" append-to-body>
      <el-form ref="orderRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="订单号" prop="orderCode">
          <el-input v-model="form.orderCode" placeholder="如 PO-261030-001" />
        </el-form-item>
        <el-form-item label="供应商" prop="supplierName">
          <el-select v-model="form.supplierName" filterable placeholder="请选择供应商" style="width: 100%" @change="pickSupplier">
            <el-option v-for="s in suppliers" :key="s.supplierId" :label="s.supplierName" :value="s.supplierName" />
          </el-select>
        </el-form-item>
        <el-form-item label="物料" prop="materialName">
          <el-select v-model="form.materialCode" filterable placeholder="请选择物料" style="width: 100%" @change="pickMaterial">
            <el-option v-for="m in materials" :key="m.materialId" :label="m.materialName + '（' + m.materialCode + '）'" :value="m.materialCode" />
          </el-select>
        </el-form-item>
        <el-form-item label="数量" prop="qty">
          <el-input-number v-model="form.qty" :min="1" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="单价" prop="price">
          <el-input-number v-model="form.price" :min="0.01" :precision="2" style="width: 100%" />
        </el-form-item>
        <el-form-item label="期望到货">
          <el-date-picker v-model="form.expectDate" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark" type="textarea" :rows="2" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="orderOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitOrder">确定</el-button>
      </template>
    </el-dialog>

    <!-- 到货登记 -->
    <el-dialog v-model="arrivalOpen" title="到货登记" width="620px" append-to-body>
      <el-form ref="arrivalRef" :model="arrival" :rules="arrivalRules" label-width="100px">
        <el-form-item label="采购订单">
          <el-input v-model="arrival.orderCode" disabled />
        </el-form-item>
        <el-form-item label="供应商">
          <el-input v-model="arrival.supplierName" disabled />
        </el-form-item>
        <el-form-item label="物料">
          <el-input v-model="arrival.materialName" disabled />
        </el-form-item>
        <el-form-item label="到货数量" prop="qty">
          <el-input-number v-model="arrival.qty" :min="1" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="合格数量">
          <el-input-number v-model="arrival.qualifiedQty" :min="0" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="拒收数量">
          <el-input-number v-model="arrival.rejectedQty" :min="0" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="入库仓库">
          <el-select v-model="arrival.warehouseCode" style="width: 100%">
            <el-option label="原材料仓" value="WH-RM" />
            <el-option label="半成品仓" value="WH-SUB" />
            <el-option label="成品仓" value="WH-FG" />
          </el-select>
        </el-form-item>
        <el-form-item label="到货日期">
          <el-date-picker v-model="arrival.arrivalDate" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
      </el-form>
      <el-alert type="info" :closable="false" show-icon title="合格数量将自动入库并增加库存，同时生成存货凭证与 U8C 待同步记录。" />
      <template #footer>
        <el-button @click="arrivalOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitArrival">确认登记</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="MesPurchase">
import { computed, onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getCurrentInstance } from 'vue'
import { listPurchaseOrder, addPurchaseOrder, updatePurchaseOrder } from '@/api/mes/purchase'
import { listArrival } from '@/api/mes/arrival'
import { listSupplier, updateSupplier } from '@/api/mes/supplier'
import { listMaterial } from '@/api/mes/material'
import { registerArrival } from '@/api/mes/biz'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'

const { proxy } = getCurrentInstance()
const router = useRouter()
const route = useRoute()

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
const suppliers = ref([])
const materials = ref([])

const orderOpen = ref(false)
const arrivalOpen = ref(false)
const form = ref({})
const arrival = ref({})

const rules = {
  orderCode: [{ required: true, message: '请输入订单号', trigger: 'blur' }],
  supplierName: [{ required: true, message: '请选择供应商', trigger: 'change' }],
  materialCode: [{ required: true, message: '请选择物料', trigger: 'change' }],
  qty: [{ required: true, message: '请输入数量', trigger: 'blur' }],
  price: [{ required: true, message: '请输入单价', trigger: 'blur' }]
}
const arrivalRules = {
  qty: [{ required: true, message: '请输入到货数量', trigger: 'blur' }]
}

const num = v => (v === null || v === undefined ? 0 : Number(v))
const money = v => (v === null || v === undefined ? '0.00' : Number(v).toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 }))

const statusOptions = computed(() => {
  if (activeTab.value === 'order') {
    return [
      { value: 'ordered', label: '已下单' }, { value: 'partial', label: '部分到货' },
      { value: 'received', label: '已到货' }, { value: 'overdue', label: '交付逾期' }
    ]
  }
  if (activeTab.value === 'arrival') {
    return [{ value: 'stocked', label: '已入库' }, { value: 'rejected', label: '拒收' }, { value: 'qualified', label: '合格' }]
  }
  return [{ value: '0', label: '待审核' }, { value: '1', label: '合作中' }, { value: '2', label: '停用' }]
})

const kpis = computed(() => {
  const list = allOrders.value
  const month = new Date().toISOString().slice(0, 7)
  const monthAmount = list.filter(i => (i.orderDate || '').slice(0, 7) === month).reduce((s, i) => s + num(i.amount), 0)
  const inTransit = list.reduce((s, i) => s + Math.max(num(i.qty) - num(i.receivedQty), 0), 0)
  const pendingStock = list.filter(i => ['ordered', 'partial'].includes(i.status)).length
  const pendingSettle = list.filter(i => i.status === 'received').length
  return [
    { label: '本月采购额', value: (monthAmount / 10000).toFixed(1), unit: '万元', foot: '按订单日期统计', tone: '' },
    { label: '在途数量', value: inTransit, unit: '件', foot: '订购 - 已到货', tone: 'warn' },
    { label: '待入库订单', value: pendingStock, unit: '单', foot: '已下单 / 部分到货', tone: 'accent' },
    { label: '待结算订单', value: pendingSettle, unit: '单', foot: '已全部到货', tone: 'ok' }
  ]
})

function buildQuery(extra = {}) {
  const q = { pageNum: page.value, pageSize: size.value, ...extra }
  if (keyword.value) q.params = { keyword: keyword.value }
  if (status.value) q.status = status.value
  return q
}

async function load() {
  loading.value = true
  try {
    if (activeTab.value === 'order') {
      const res = await listPurchaseOrder(buildQuery())
      rows.value = res.rows
      total.value = res.total
    } else if (activeTab.value === 'arrival') {
      const res = await listArrival(buildQuery())
      rows.value = res.rows
      total.value = res.total
    } else {
      const res = await listSupplier(buildQuery())
      rows.value = res.rows
      total.value = res.total
    }
  } finally {
    loading.value = false
  }
}

async function loadMeta() {
  const res = await listPurchaseOrder({ pageNum: 1, pageSize: 500 })
  allOrders.value = res.rows || []
  const s = await listSupplier({ pageNum: 1, pageSize: 200 })
  suppliers.value = s.rows || []
  const m = await listMaterial({ pageNum: 1, pageSize: 200 })
  materials.value = m.rows || []
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

function openOrder(row) {
  form.value = row ? { ...row } : { qty: 100, price: 1, expectDate: '', status: 'ordered' }
  orderOpen.value = true
}

function pickSupplier(name) {
  const hit = suppliers.value.find(i => i.supplierName === name)
  form.value.supplierCode = hit ? hit.supplierCode : ''
}

function pickMaterial(code) {
  const hit = materials.value.find(i => i.materialCode === code)
  if (hit) {
    form.value.materialName = hit.materialName
    form.value.price = Number(hit.price) || 1
  }
}

function submitOrder() {
  proxy.$refs.orderRef.validate(async valid => {
    if (!valid) return
    saving.value = true
    try {
      const payload = { ...form.value }
      payload.amount = Number(payload.qty) * Number(payload.price)
      payload.orderDate = payload.orderDate || new Date().toISOString().slice(0, 10)
      if (payload.purchaseId) {
        await updatePurchaseOrder(payload)
        proxy.$modal.msgSuccess('修改成功')
      } else {
        payload.status = payload.status || 'ordered'
        payload.receivedQty = 0
        await addPurchaseOrder(payload)
        proxy.$modal.msgSuccess('采购订单已创建')
      }
      orderOpen.value = false
      await loadMeta()
      load()
    } finally {
      saving.value = false
    }
  })
}

function openArrival(row) {
  const remain = Math.max(num(row.qty) - num(row.receivedQty), 0)
  arrival.value = {
    orderCode: row.orderCode,
    supplierName: row.supplierName,
    materialName: row.materialName,
    qty: remain || num(row.qty),
    qualifiedQty: remain || num(row.qty),
    rejectedQty: 0,
    warehouseCode: 'WH-RM',
    arrivalDate: new Date().toISOString().slice(0, 10)
  }
  arrivalOpen.value = true
}

function submitArrival() {
  proxy.$refs.arrivalRef.validate(async valid => {
    if (!valid) return
    saving.value = true
    try {
      const res = await registerArrival(arrival.value)
      proxy.$modal.msgSuccess(res.msg || '到货登记成功')
      arrivalOpen.value = false
      await loadMeta()
      activeTab.value = 'arrival'
      load()
    } finally {
      saving.value = false
    }
  })
}

async function audit(row) {
  await updateSupplier({ supplierId: row.supplierId, status: '1' })
  proxy.$modal.msgSuccess('供应商审核通过')
  load()
}

function supplierText(status) {
  return { '0': '待审核', '1': '合作中', '2': '停用' }[status] || status
}

function supplierTone(status) {
  return { '0': 'warning', '1': 'success', '2': 'info' }[status] || 'info'
}

function goQuality() {
  router.push('/mes/collab')
}

onMounted(async () => {
  if (route.query.keyword) {
    keyword.value = String(route.query.keyword)
  }
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
