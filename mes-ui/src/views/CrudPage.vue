<template>
  <div class="crud-page">
    <!-- 查询表单 -->
    <el-card shadow="never" class="search-card">
      <el-form :model="queryParams" inline @submit.prevent>
        <el-form-item
          v-for="field in config.form"
          :key="'q-' + field.prop"
          :label="field.label"
        >
          <el-select
            v-if="field.type === 'select'"
            v-model="queryParams[field.prop]"
            clearable
            filterable
            placeholder="请选择"
            style="width: 180px"
            @keyup.enter="handleQuery"
          >
            <el-option
              v-for="opt in field.options"
              :key="opt.value"
              :label="opt.label"
              :value="opt.value"
            />
          </el-select>
          <el-date-picker
            v-else-if="field.type === 'date'"
            v-model="queryParams[field.prop]"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择时间"
            style="width: 200px"
          />
          <el-input
            v-else
            v-model="queryParams[field.prop]"
            clearable
            placeholder="请输入"
            style="width: 180px"
            @keyup.enter="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="handleQuery">搜索</el-button>
          <el-button :icon="Refresh" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card shadow="never">
      <!-- 操作栏 -->
      <div class="toolbar">
        <div class="toolbar-left">
          <el-button
            v-if="can('add')"
            type="primary"
            :icon="Plus"
            @click="handleAdd"
          >
            新增
          </el-button>
          <el-button
            v-if="can('remove')"
            type="danger"
            :icon="Delete"
            :disabled="selectedRows.length === 0"
            @click="handleBatchDelete"
          >
            删除
          </el-button>
        </div>
        <div class="toolbar-right">
          <el-button v-if="can('export')" :icon="Download" @click="handleExport">导出</el-button>
        </div>
      </div>

      <!-- 数据表格 -->
      <el-table
        v-loading="loading"
        :data="list"
        border
        stripe
        height="520"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="50" align="center" />
        <el-table-column type="index" label="#" width="55" align="center" />
        <el-table-column
          v-for="column in config.columns"
          :key="column.prop"
          :prop="column.prop"
          :label="column.label"
          :min-width="columnWidth(column.label)"
          show-overflow-tooltip
        />
        <el-table-column label="操作" width="150" fixed="right" align="center">
          <template #default="{ row }">
            <el-button v-if="can('edit')" link type="primary" :icon="Edit" @click="handleEdit(row)">
              修改
            </el-button>
            <el-button v-if="can('remove')" link type="danger" :icon="Delete" @click="handleDelete(row)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <el-pagination
        class="pagination"
        v-model:current-page="queryParams.pageNum"
        v-model:page-size="queryParams.pageSize"
        :page-sizes="[10, 20, 50, 100]"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        background
        @size-change="getList"
        @current-change="getList"
      />
    </el-card>

    <!-- 新增/修改弹窗 -->
    <el-dialog
      v-model="dialog.visible"
      :title="dialog.title"
      width="680px"
      append-to-body
      :close-on-click-modal="false"
    >
      <el-form ref="formRef" :model="form" label-width="120px">
        <el-form-item
          v-for="field in config.form"
          :key="'f-' + field.prop"
          :label="field.label"
          :prop="field.prop"
        >
          <el-select
            v-if="field.type === 'select'"
            v-model="form[field.prop]"
            clearable
            filterable
            placeholder="请选择"
            style="width: 100%"
          >
            <el-option
              v-for="opt in field.options"
              :key="opt.value"
              :label="opt.label"
              :value="opt.value"
            />
          </el-select>
          <el-date-picker
            v-else-if="field.type === 'date'"
            v-model="form[field.prop]"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择时间"
            style="width: 100%"
          />
          <el-input-number
            v-else-if="field.type === 'number'"
            v-model="form[field.prop]"
            :controls-position="'right'"
            style="width: 100%"
          />
          <el-input
            v-else-if="field.type === 'textarea'"
            v-model="form[field.prop]"
            type="textarea"
            :rows="3"
            placeholder="请输入"
          />
          <el-input
            v-else
            v-model="form[field.prop]"
            clearable
            placeholder="请输入"
          />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialog.visible = false">取 消</el-button>
        <el-button type="primary" :loading="dialog.submitting" @click="submitForm">确 定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref, watch } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Refresh, Plus, Delete, Edit, Download } from '@element-plus/icons-vue'
import request from '@/utils/request'
import useUserStore from '@/store/user'

const props = defineProps({
  config: {
    type: Object,
    required: true
  }
})

const userStore = useUserStore()

const loading = ref(false)
const list = ref([])
const total = ref(0)
const selectedRows = ref([])
const formRef = ref()

const dialog = reactive({
  visible: false,
  title: '',
  isEdit: false,
  submitting: false
})

// 根据配置构造空的查询/表单对象
function buildDefaults(config) {
  const obj = {}
  ;(config.form || []).forEach((field) => {
    obj[field.prop] = undefined
  })
  return obj
}

const queryParams = reactive({ pageNum: 1, pageSize: 10 })
const form = reactive({})

// 初始化字段（保证响应式 key 存在）
function initFields(config) {
  Object.assign(queryParams, buildDefaults(config))
  Object.keys(form).forEach((k) => delete form[k])
  Object.assign(form, buildDefaults(config))
}

// 权限判断
function can(action) {
  return userStore.hasPermi(`${props.config.perm}:${action}`)
}

// 去掉空值的查询参数
function cleanParams(params) {
  const result = {}
  Object.keys(params).forEach((key) => {
    const value = params[key]
    if (value !== undefined && value !== null && value !== '') {
      result[key] = value
    }
  })
  return result
}

function columnWidth(label) {
  if (!label) {
    return 120
  }
  return Math.max(110, String(label).length * 16 + 40)
}

function getList() {
  loading.value = true
  const path = props.config.path
  request({
    url: `${path}/list`,
    method: 'get',
    params: cleanParams(queryParams)
  })
    .then((res) => {
      list.value = res.rows || []
      total.value = res.total || 0
    })
    .catch(() => {
      list.value = []
      total.value = 0
    })
    .finally(() => {
      loading.value = false
    })
}

function handleQuery() {
  queryParams.pageNum = 1
  getList()
}

function resetQuery() {
  Object.keys(queryParams).forEach((key) => {
    if (key !== 'pageNum' && key !== 'pageSize') {
      queryParams[key] = undefined
    }
  })
  queryParams.pageNum = 1
  getList()
}

function handleSelectionChange(rows) {
  selectedRows.value = rows
}

function handleAdd() {
  dialog.isEdit = false
  dialog.title = `新增${props.config.title}`
  Object.keys(form).forEach((k) => delete form[k])
  Object.assign(form, buildDefaults(props.config))
  dialog.visible = true
}

function normalizeValue(field, value) {
  if (value === null || value === undefined) {
    return undefined
  }
  if (field.type === 'number') {
    const num = Number(value)
    return Number.isNaN(num) ? undefined : num
  }
  return value
}

function handleEdit(row) {
  const path = props.config.path
  dialog.isEdit = true
  dialog.title = `修改${props.config.title}`
  request({ url: `${path}/${row.id}`, method: 'get' })
    .then((res) => {
      fillForm(res.data || row)
    })
    .catch(() => {
      fillForm(row)
    })
}

function fillForm(data) {
  Object.keys(form).forEach((k) => delete form[k])
  ;(props.config.form || []).forEach((field) => {
    form[field.prop] = normalizeValue(field, data[field.prop])
  })
  // 隐藏主键，用于修改提交
  form.id = data.id
  dialog.visible = true
}

function submitForm() {
  formRef.value.validate((valid) => {
    if (!valid) {
      return
    }
    dialog.submitting = true
    const path = props.config.path
    const isEdit = dialog.isEdit
    const payload = {}
    ;(props.config.form || []).forEach((field) => {
      payload[field.prop] = form[field.prop]
    })
    if (isEdit && form.id !== undefined) {
      payload.id = form.id
    }
    const reqConfig = isEdit
      ? { url: path, method: 'put', data: payload }
      : { url: path, method: 'post', data: payload }
    request(reqConfig)
      .then(() => {
        ElMessage.success(isEdit ? '修改成功' : '新增成功')
        dialog.visible = false
        getList()
      })
      .finally(() => {
        dialog.submitting = false
      })
  })
}

function handleDelete(row) {
  ElMessageBox.confirm(`确认删除选中的数据项吗？`, '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(() => {
      request({ url: `${props.config.path}/${row.id}`, method: 'delete' }).then(() => {
        ElMessage.success('删除成功')
        getList()
      })
    })
    .catch(() => {})
}

function handleBatchDelete() {
  const ids = selectedRows.value.map((row) => row.id).join(',')
  if (!ids) {
    return
  }
  ElMessageBox.confirm(`确认删除选中的 ${selectedRows.value.length} 条数据吗？`, '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(() => {
      request({ url: `${props.config.path}/${ids}`, method: 'delete' }).then(() => {
        ElMessage.success('删除成功')
        getList()
      })
    })
    .catch(() => {})
}

function handleExport() {
  request({
    url: `${props.config.path}/export`,
    method: 'post',
    data: cleanParams(queryParams),
    responseType: 'blob'
  })
    .then((blob) => {
      const url = window.URL.createObjectURL(new Blob([blob]))
      const link = document.createElement('a')
      link.href = url
      link.download = `${props.config.title}_${new Date().getTime()}.xlsx`
      document.body.appendChild(link)
      link.click()
      document.body.removeChild(link)
      window.URL.revokeObjectURL(url)
      ElMessage.success('导出成功')
    })
    .catch(() => {})
}

watch(
  () => props.config,
  (config) => {
    if (config) {
      initFields(config)
      queryParams.pageNum = 1
      getList()
    }
  },
  { immediate: false }
)

onMounted(() => {
  initFields(props.config)
  getList()
})
</script>

<style scoped>
.search-card {
  margin-bottom: 16px;
}

.search-card :deep(.el-form-item) {
  margin-bottom: 8px;
}

.toolbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 12px;
}

.pagination {
  margin-top: 16px;
  justify-content: flex-end;
}
</style>
