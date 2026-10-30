<template>
  <div class="app-container mes-page">
    <div class="page-head">
      <div>
        <h2>基础数据</h2>
        <span class="sub">工厂建模 · 物料主数据 · 供应商档案 · 客户档案 · 编码规则</span>
      </div>
      <div class="head-actions">
        <el-button icon="Refresh" :loading="loading" @click="refresh">刷新</el-button>
        <el-button
          v-if="activeTab === 'material'"
          type="primary"
          icon="Plus"
          v-hasPermi="['mes:material:add']"
          @click="openForm()"
        >新增物料</el-button>
      </div>
    </div>

    <MesKpi :items="kpis" :cols="4" />

    <el-tabs v-model="activeTab" @tab-change="onTabChange">
      <el-tab-pane label="物料主数据" name="material" />
      <el-tab-pane label="供应商" name="supplier" />
      <el-tab-pane label="客户档案" name="customer" />
    </el-tabs>

    <el-form :inline="true" class="query-bar">
      <el-form-item label="关键字">
        <el-input v-model="keyword" :placeholder="keywordPlaceholder" clearable style="width: 230px" @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="rows" size="small" border>
      <template v-if="activeTab === 'material'">
        <el-table-column prop="materialCode" label="物料编码" width="140" />
        <el-table-column prop="materialName" label="物料名称" min-width="180" show-overflow-tooltip />
        <el-table-column prop="spec" label="规格" width="110" />
        <el-table-column prop="unit" label="单位" width="70" />
        <el-table-column prop="category" label="分类" width="90" />
        <el-table-column label="类型" width="80">
          <template #default="{ row }">
            <el-tag :type="row.productFlag === '1' ? 'success' : 'info'" size="small">{{ row.productFlag === '1' ? '产品' : '物料' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="安全库存" width="100" align="right">
          <template #default="{ row }"><span class="num">{{ num(row.safetyStock) }}</span></template>
        </el-table-column>
        <el-table-column label="最高库存" width="100" align="right">
          <template #default="{ row }"><span class="num">{{ num(row.maxStock) }}</span></template>
        </el-table-column>
        <el-table-column label="单价" width="100" align="right">
          <template #default="{ row }"><span class="num">{{ money(row.price) }}</span></template>
        </el-table-column>
        <el-table-column label="状态" width="90">
          <template #default="{ row }">
            <el-tag :type="row.status === '0' ? 'success' : 'info'" size="small">{{ row.status === '0' ? '启用' : '停用' }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="130" fixed="right">
          <template #default="{ row }">
            <el-button link type="primary" v-hasPermi="['mes:material:edit']" @click="openForm(row)">编辑</el-button>
            <el-button link type="danger" v-hasPermi="['mes:material:remove']" @click="removeMaterial(row)">删除</el-button>
          </template>
        </el-table-column>
      </template>

      <template v-else-if="activeTab === 'supplier'">
        <el-table-column prop="supplierCode" label="编码" width="110" />
        <el-table-column prop="supplierName" label="供应商名称" min-width="200" show-overflow-tooltip />
        <el-table-column prop="category" label="分类" width="90" />
        <el-table-column prop="level" label="等级" width="70" />
        <el-table-column prop="contact" label="联系人" width="90" />
        <el-table-column prop="phone" label="电话" width="140" />
        <el-table-column label="准时率" width="90" align="right">
          <template #default="{ row }"><span class="num">{{ fmt(row.onTimeRate) }}%</span></template>
        </el-table-column>
        <el-table-column label="综合评分" width="90" align="right">
          <template #default="{ row }"><span class="num">{{ fmt(row.score) }}</span></template>
        </el-table-column>
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="supplierTone(row.status)" size="small">{{ supplierText(row.status) }}</el-tag>
          </template>
        </el-table-column>
      </template>

      <template v-else>
        <el-table-column prop="customerCode" label="编码" width="150" />
        <el-table-column prop="customerName" label="客户名称" min-width="200" show-overflow-tooltip />
        <el-table-column prop="contact" label="联系人" width="100" />
        <el-table-column prop="phone" label="电话" width="150" />
        <el-table-column label="信用额度" width="140" align="right">
          <template #default="{ row }"><span class="num">{{ money(row.creditLimit) }}</span></template>
        </el-table-column>
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === '0' ? 'success' : 'info'" size="small">{{ row.status === '0' ? '正常' : '停用' }}</el-tag>
          </template>
        </el-table-column>
      </template>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="page" v-model:limit="size" @pagination="load" />

    <el-row :gutter="12" class="bottom-row">
      <el-col :span="14">
        <el-card shadow="never" class="panel">
          <template #header><span>工厂建模层级</span></template>
          <div class="org-path">工厂 → 车间 → 产线 → 工位</div>
          <el-tree :data="orgTree" node-key="id" default-expand-all :expand-on-click-node="false" class="org-tree" />
        </el-card>
      </el-col>
      <el-col :span="10">
        <el-card shadow="never" class="panel">
          <template #header><span>编码规则说明</span></template>
          <ul class="rule-list">
            <li>
              <strong>供应商编码</strong>
              <span>分类 2 位 + 3 位流水，如 <em>01-001</em>（01 整车 / 02 物料 / 03 外协 / 04 服务）。</span>
            </li>
            <li>
              <strong>存货编码</strong>
              <span>统一使用 18 位机号编码，产品、物料、半成品共用一套存货档案。</span>
            </li>
            <li>
              <strong>物料 / 产品 / 工序</strong>
              <span>统一在基础档案维护，物料与产品以“产品标识”区分，禁止跨页签重复建档。</span>
            </li>
            <li>
              <strong>变更控制</strong>
              <span>产品、BOM 与工艺路线变更需评审后生效，试产版本不得批量投产。</span>
            </li>
          </ul>
        </el-card>
      </el-col>
    </el-row>

    <el-dialog v-model="formOpen" :title="form.materialId ? '编辑物料' : '新增物料'" width="620px" append-to-body>
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="物料编码" prop="materialCode">
          <el-input v-model="form.materialCode" placeholder="如 M-ST-0801 / P-SD3-300" />
        </el-form-item>
        <el-form-item label="物料名称" prop="materialName">
          <el-input v-model="form.materialName" placeholder="请输入物料名称" />
        </el-form-item>
        <el-form-item label="规格" prop="spec">
          <el-input v-model="form.spec" placeholder="请输入规格" />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="件 / 台 / 套" />
        </el-form-item>
        <el-form-item label="分类" prop="category">
          <el-select v-model="form.category" filterable allow-create placeholder="请选择分类" style="width: 100%">
            <el-option v-for="item in categoryOptions" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item label="产品标识" prop="productFlag">
          <el-radio-group v-model="form.productFlag">
            <el-radio value="0">物料</el-radio>
            <el-radio value="1">产品</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="安全库存" prop="safetyStock">
          <el-input-number v-model="form.safetyStock" :min="0" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="最高库存" prop="maxStock">
          <el-input-number v-model="form.maxStock" :min="0" :precision="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="单价" prop="price">
          <el-input-number v-model="form.price" :min="0" :precision="2" style="width: 100%" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" style="width: 100%">
            <el-option label="启用" value="0" />
            <el-option label="停用" value="1" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="formOpen = false">取消</el-button>
        <el-button type="primary" :loading="saving" @click="submitForm">确定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="MesMasterdata">
import { computed, getCurrentInstance, onMounted, ref } from 'vue'
import { listMaterial, addMaterial, updateMaterial, delMaterial } from '@/api/mes/material'
import { listSupplier } from '@/api/mes/supplier'
import { listCustomer } from '@/api/mes/customer'
import MesKpi from '../components/MesKpi.vue'

const { proxy } = getCurrentInstance()

const activeTab = ref('material')
const loading = ref(false)
const saving = ref(false)
const rows = ref([])
const total = ref(0)
const page = ref(1)
const size = ref(10)
const keyword = ref('')

const allMaterials = ref([])
const allSuppliers = ref([])
const allCustomers = ref([])

const formOpen = ref(false)
const form = ref({})
const formRef = ref()

const categoryOptions = ['成品', '机加件', '电子件', '机电件', '标准件', '辅料']

const rules = {
  materialCode: [{ required: true, message: '请输入物料编码', trigger: 'blur' }],
  materialName: [{ required: true, message: '请输入物料名称', trigger: 'blur' }],
  unit: [{ required: true, message: '请输入单位', trigger: 'blur' }]
}

const num = v => (v === null || v === undefined || v === '' ? 0 : Number(v))
const fmt = v => num(v).toFixed(1)
const money = v => num(v).toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 })

const keywordPlaceholder = computed(() => {
  if (activeTab.value === 'supplier') return '供应商编码 / 名称'
  if (activeTab.value === 'customer') return '客户编码 / 名称'
  return '物料编码 / 名称'
})

const kpis = computed(() => [
  { label: '物料数', value: allMaterials.value.filter(i => i.productFlag !== '1').length, unit: '项', foot: '非产品类物料', tone: '' },
  { label: '产品数', value: allMaterials.value.filter(i => i.productFlag === '1').length, unit: '项', foot: '成品与半成品', tone: 'ok' },
  { label: '供应商数', value: allSuppliers.value.length, unit: '家', foot: '供应商档案在册', tone: 'accent' },
  { label: '客户数', value: allCustomers.value.length, unit: '家', foot: '客户档案在册', tone: 'ok' }
])

const orgTree = [
  {
    id: 'F-HD',
    label: '华东智造基地（工厂）',
    children: [
      {
        id: 'WS-MC',
        label: '机加车间',
        children: [
          { id: 'L-MC-01', label: 'MC-01 产线', children: stationNodes('MC-01') },
          { id: 'L-MC-02', label: 'MC-02 产线', children: stationNodes('MC-02') }
        ]
      },
      {
        id: 'WS-AS',
        label: '装配车间',
        children: [
          { id: 'L-AS-01', label: 'AS-01 产线', children: stationNodes('AS-01') },
          { id: 'L-AS-02', label: 'AS-02 产线', children: stationNodes('AS-02') }
        ]
      },
      {
        id: 'WS-TS',
        label: '测试包装车间',
        children: [
          { id: 'L-TS-01', label: 'TS-01 产线', children: stationNodes('TS-01') },
          { id: 'L-PK-01', label: 'PK-01 产线', children: stationNodes('PK-01') }
        ]
      }
    ]
  }
]

function stationNodes(line) {
  return ['OP10', 'OP20', 'OP30', 'OP40', 'OP50'].map(op => ({ id: line + '-' + op, label: line + ' / ' + op }))
}

function buildQuery() {
  const q = { pageNum: page.value, pageSize: size.value }
  if (keyword.value) q.params = { keyword: keyword.value }
  return q
}

async function load() {
  loading.value = true
  try {
    const api = activeTab.value === 'supplier' ? listSupplier : activeTab.value === 'customer' ? listCustomer : listMaterial
    const res = await api(buildQuery())
    rows.value = res.rows || []
    total.value = res.total || 0
  } finally {
    loading.value = false
  }
}

async function loadMeta() {
  const m = await listMaterial({ pageNum: 1, pageSize: 500 })
  allMaterials.value = m.rows || []
  const s = await listSupplier({ pageNum: 1, pageSize: 500 })
  allSuppliers.value = s.rows || []
  const c = await listCustomer({ pageNum: 1, pageSize: 500 })
  allCustomers.value = c.rows || []
}

function refresh() {
  loadMeta()
  load()
}

function onTabChange() {
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
  page.value = 1
  load()
}

function supplierText(status) {
  return { '0': '待审核', '1': '合作中', '2': '停用' }[status] || status || '-'
}

function supplierTone(status) {
  return { '0': 'warning', '1': 'success', '2': 'info' }[status] || 'info'
}

function openForm(row) {
  form.value = row
    ? { ...row }
    : {
        materialCode: '',
        materialName: '',
        spec: '',
        unit: '件',
        category: '机加件',
        productFlag: '0',
        safetyStock: 0,
        maxStock: 0,
        price: 0,
        status: '0'
      }
  formOpen.value = true
}

function submitForm() {
  formRef.value.validate(async valid => {
    if (!valid) return
    saving.value = true
    try {
      if (form.value.materialId) {
        await updateMaterial(form.value)
        proxy.$modal.msgSuccess('修改成功')
      } else {
        await addMaterial(form.value)
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

async function removeMaterial(row) {
  try {
    await proxy.$modal.confirm('确认删除物料「' + row.materialName + '」？')
  } catch (e) {
    return
  }
  await delMaterial(row.materialId)
  proxy.$modal.msgSuccess('删除成功')
  await loadMeta()
  load()
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
.num { font-family: 'DIN Alternate', 'Roboto Mono', monospace; }
.bottom-row { margin-top: 12px; }
.panel { border-color: #d3dbe0; height: 100%; }
.panel :deep(.el-card__header) { padding: 10px 14px; font-weight: 600; color: #16242b; background: #f5f7f8; }
.org-path { margin-bottom: 8px; font-size: 12px; letter-spacing: 2px; color: #2a6f97; }
.org-tree { font-size: 13px; }
.org-tree :deep(.el-tree-node__content) { height: 30px; }
.rule-list { margin: 0; padding-left: 18px; }
.rule-list li { margin-bottom: 10px; font-size: 12px; color: #5b6b74; line-height: 1.7; }
.rule-list strong { display: block; color: #16242b; font-size: 13px; }
.rule-list em { font-style: normal; color: #2a6f97; font-family: 'Roboto Mono', monospace; }
</style>
