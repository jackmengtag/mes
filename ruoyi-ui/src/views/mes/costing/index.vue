<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>存货核算</h2>
        <span class="sub">存货单据记账 · 成本调整 · U8C 凭证同步 · 期末结账</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="refresh">刷新</el-button>
        <el-button type="primary" :disabled="!selected.length" v-hasPermi="['mes:voucher:post']" @click="batchPost">批量记账</el-button>
        <el-button type="primary" plain icon="Plus" v-hasPermi="['mes:voucher:add']" @click="openForm">新增调整单</el-button>
        <el-button type="warning" plain @click="closePeriod">期末结账</el-button>
        <el-button icon="Download" v-hasPermi="['mes:voucher:export']" @click="handleExport">导出</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-card shadow="never" class="panel flow-panel">
      <el-steps :active="stepActive" finish-status="success" align-center>
        <el-step title="待记账" :description="pendingCount + ' 张待处理'" />
        <el-step title="已记账" :description="postedCount + ' 张已记账'" />
        <el-step title="已同步 U8C" :description="syncedCount + ' 张已同步'" />
      </el-steps>
    </el-card>

    <el-tabs v-model="activeTab" @tab-change="onTabChange">
      <el-tab-pane label="待记账" name="pending" />
      <el-tab-pane label="已记账" name="posted" />
      <el-tab-pane label="全部" name="all" />
    </el-tabs>

    <el-form :inline="true" class="query-bar">
      <el-form-item label="关键字">
        <el-input v-model="keyword" placeholder="凭证号 / 来源单号" clearable style="width: 230px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item label="业务类型">
        <el-select v-model="bizType" clearable placeholder="全部" style="width: 150px" @change="handleQuery">
          <el-option v-for="(label, value) in bizTypeMap" :key="value" :label="label" :value="value" />
        </el-select>
      </el-form-item>
      <el-form-item label="U8C 状态">
        <el-select v-model="u8cStatus" clearable placeholder="全部" style="width: 130px" @change="handleQuery">
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

    <el-table v-loading="loading" :data="rows" size="small" border @selection-change="onSelectionChange">
      <el-table-column type="selection" width="44" />
      <el-table-column prop="voucherCode" label="凭证号" width="150" />
      <el-table-column label="业务类型" width="110">
        <template #default="{ row }">{{ bizTypeMap[row.bizType] || row.bizType || '-' }}</template>
      </el-table-column>
      <el-table-column prop="bizCode" label="来源单号" width="150" />
      <el-table-column label="数量" width="100" align="right">
        <template #default="{ row }"><span class="num">{{ fmt(row.qty) }}</span></template>
      </el-table-column>
      <el-table-column label="金额" width="130" align="right">
        <template #default="{ row }"><span class="num">{{ money(row.amount) }}</span></template>
      </el-table-column>
      <el-table-column prop="accountPeriod" label="会计期间" width="100" />
      <el-table-column label="记账状态" width="100">
        <template #default="{ row }"><MesStatusTag :value="row.status" type="voucher" /></template>
      </el-table-column>
      <el-table-column label="U8C 状态" width="110">
        <template #default="{ row }"><MesStatusTag :value="row.u8cStatus" type="voucher" /></template>
      </el-table-column>
      <el-table-column prop="message" label="同步信息" min-width="180" show-overflow-tooltip>
        <template #default="{ row }">{{ row.message || '-' }}</template>
      </el-table-column>
      <el-table-column label="操作" width="110" fixed="right">
        <template #default="{ row }">
          <el-button
            link
            type="primary"
            :disabled="row.status !== 'posted' || row.u8cStatus === 'success'"
            v-hasPermi="['mes:voucher:sync']"
            @click="sync(row)"
          >推送 U8C</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="page" v-model:limit="size" @pagination="load" />

    <el-dialog v-model="formOpen" title="新增调整单" width="560px" append-to-body>
      <el-form ref="voucherFormRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="业务类型">
          <el-input model-value="盘点调整" disabled />
        </el-form-item>
        <el-form-item label="来源单号" prop="bizCode">
          <el-input v-model="form.bizCode" placeholder="如 PD-261024-002" />
        </el-form-item>
        <el-form-item label="数量" prop="qty">
          <el-input-number v-model="form.qty" :min="0" :precision="2" style="width: 100%" />
        </el-form-item>
        <el-form-item label="金额" prop="amount">
          <el-input-number v-model="form.amount" :min="0" :precision="2" style="width: 100%" />
        </el-form-item>
        <el-form-item label="会计期间" prop="accountPeriod">
          <el-date-picker v-model="form.accountPeriod" type="month" value-format="YYYY-MM" placeholder="选择期间" style="width: 100%" />
        </el-form-item>
      </el-form>
      <el-alert type="info" :closable="false" show-icon title="调整单用于金额尾差、盘点差异与暂估回冲，保存后进入待记账。" />
      <template #footer>
        <el-button @click="formOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitForm">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="MesCosting">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { listVoucher, addVoucher } from '@/api/mes/voucher'
import { postVouchers, syncVoucher } from '@/api/mes/biz'
import MesKpi from '../components/MesKpi.vue'
import MesStatusTag from '../components/MesStatusTag.vue'

const { proxy } = getCurrentInstance()

const bizTypeMap = {
  purchase_in: '采购入库',
  product_in: '成品入库',
  sales_out: '销售出库',
  production_pick: '生产领料',
  other: '其他',
  check_adjust: '盘点调整'
}

const activeTab = ref('pending')
const loading = ref(false)
const saving = ref(false)
const rows = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(10)
const keyword = ref('')
const bizType = ref('')
const u8cStatus = ref('')
const selected = ref([])
const allVouchers = ref([])

const formOpen = ref(false)
const form = ref({})
const voucherFormRef = ref()

const rules = {
  bizCode: [{ required: true, message: '请输入来源单号', trigger: 'blur' }],
  qty: [{ required: true, message: '请输入数量', trigger: 'blur' }],
  amount: [{ required: true, message: '请输入金额', trigger: 'blur' }],
  accountPeriod: [{ required: true, message: '请选择会计期间', trigger: 'change' }]
}

const num = v => (v === null || v === undefined ? 0 : Number(v))
const fmt = v => num(v).toLocaleString('zh-CN', { maximumFractionDigits: 2 })
const money = v => num(v).toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 })

function pad(n) {
  return String(n).padStart(2, '0')
}

function currentPeriod() {
  const d = new Date()
  return d.getFullYear() + '-' + pad(d.getMonth() + 1)
}

const pendingCount = computed(() => allVouchers.value.filter(i => i.status === 'pending').length)
const postedCount = computed(() => allVouchers.value.filter(i => i.status === 'posted').length)
const syncedCount = computed(() => allVouchers.value.filter(i => i.u8cStatus === 'success').length)

const kpis = computed(() => {
  const list = allVouchers.value
  const pendingAmount = list.filter(i => i.status === 'pending').reduce((s, i) => s + num(i.amount), 0)
  return [
    { label: '待记账凭证', value: pendingCount.value, unit: '张', foot: '待处理单据', tone: 'warn' },
    { label: '待记账金额', value: (pendingAmount / 10000).toFixed(1), unit: '万元', foot: '按凭证金额汇总', tone: 'accent' },
    { label: '已记账', value: postedCount.value, unit: '张', foot: '已生成记账凭证', tone: 'ok' },
    { label: 'U8C 失败', value: list.filter(i => i.u8cStatus === 'failed').length, unit: '张', foot: '需重新推送', tone: 'danger' }
  ]
})

const stepActive = computed(() => {
  if (!allVouchers.value.length) return 0
  if (pendingCount.value > 0) return 0
  if (allVouchers.value.some(i => i.u8cStatus !== 'success')) return 1
  return 3
})

function buildQuery() {
  const q = { pageNum: page.value, pageSize: size.value }
  if (activeTab.value !== 'all') q.status = activeTab.value
  if (keyword.value) q.params = { keyword: keyword.value }
  if (bizType.value) q.bizType = bizType.value
  if (u8cStatus.value) q.u8cStatus = u8cStatus.value
  return q
}

async function load() {
  loading.value = true
  try {
    const res = await listVoucher(buildQuery())
    rows.value = res.rows || []
    total.value = res.total || 0
  } finally {
    loading.value = false
  }
}

async function loadMeta() {
  const res = await listVoucher({ pageNum: 1, pageSize: 500 })
  allVouchers.value = res.rows || []
}

function refresh() {
  loadMeta()
  load()
}

function onTabChange() {
  page.value = 1
  load()
}

function handleQuery() {
  page.value = 1
  load()
}

function resetQuery() {
  keyword.value = ''
  bizType.value = ''
  u8cStatus.value = ''
  page.value = 1
  load()
}

function onSelectionChange(list) {
  selected.value = list
}

async function batchPost() {
  if (!selected.value.length) {
    proxy.$modal.msgWarning('请选择待记账凭证')
    return
  }
  try {
    await proxy.$modal.confirm('确认对选中的 ' + selected.value.length + ' 张凭证记账？')
  } catch (e) {
    return
  }
  const res = await postVouchers(selected.value.map(i => i.voucherId))
  proxy.$modal.msgSuccess(res.msg || '记账成功')
  await loadMeta()
  load()
}

async function sync(row) {
  const res = await syncVoucher(row.voucherId)
  proxy.$modal.msgSuccess(res.msg || '同步成功')
  await loadMeta()
  load()
}

function openForm() {
  form.value = {
    bizType: 'check_adjust',
    bizCode: '',
    qty: 0,
    amount: 0,
    accountPeriod: currentPeriod()
  }
  formOpen.value = true
}

function submitForm() {
  voucherFormRef.value.validate(async valid => {
    if (!valid) return
    saving.value = true
    try {
      const d = new Date()
      const ymd = String(d.getFullYear()).slice(2) + pad(d.getMonth() + 1) + pad(d.getDate())
      await addVoucher({
        ...form.value,
        voucherCode: 'ADJ-' + ymd + '-' + String(Math.floor(Math.random() * 900) + 100),
        status: 'pending',
        u8cStatus: 'pending',
        message: ''
      })
      proxy.$modal.msgSuccess('调整单已创建，进入待记账')
      formOpen.value = false
      await loadMeta()
      load()
    } finally {
      saving.value = false
    }
  })
}

async function closePeriod() {
  try {
    await proxy.$modal.confirm('结账后当期不可再记账，是否继续？')
  } catch (e) {
    return
  }
  proxy.$modal.msgSuccess('期末结账完成（演示口径）：' + currentPeriod() + ' 期间已关闭')
}

function handleExport() {
  proxy.download('mes/voucher/export', buildQuery(), `voucher_${Date.now()}.xlsx`)
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
.flow-panel { margin-bottom: 12px; }
.flow-panel :deep(.el-card__body) { padding: 18px 12px 8px; }
.num { font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
</style>
