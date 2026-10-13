<template>
  <div class="dashboard">
    <div class="dashboard-header">
      <h2>首页看板</h2>
      <el-button type="primary" :icon="Refresh" @click="loadAll">刷新</el-button>
    </div>

    <el-row :gutter="16">
      <el-col v-for="card in cards" :key="card.key" :xs="24" :sm="12" :lg="6">
        <el-card class="kpi-card" v-loading="states[card.key].loading" shadow="hover">
          <div class="kpi-head">
            <div class="kpi-icon" :style="{ backgroundColor: card.color }">
              <el-icon :size="22"><component :is="card.icon" /></el-icon>
            </div>
            <div class="kpi-title">
              <div class="name">{{ card.title }}</div>
              <div class="sub">{{ card.subTitle }}</div>
            </div>
          </div>

          <el-divider style="margin: 12px 0" />

          <div v-if="states[card.key].error" class="kpi-empty">
            <el-icon><WarningFilled /></el-icon>
            <span>{{ states[card.key].error }}</span>
          </div>
          <div v-else-if="metrics[card.key].length === 0" class="kpi-empty">
            <span>暂无数据</span>
          </div>
          <div v-else class="kpi-metrics">
            <div v-for="item in metrics[card.key]" :key="item.key" class="metric-row">
              <span class="metric-label">{{ item.label }}</span>
              <span class="metric-value">{{ item.value }}</span>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-card class="tip-card" shadow="never">
      <template #header>
        <span>使用说明</span>
      </template>
      <p>本页面调用后端 BI 指标接口：</p>
      <ul>
        <li>GET /mes/bi/kpi/production（生产指标）</li>
        <li>GET /mes/bi/kpi/quality（质量指标）</li>
        <li>GET /mes/bi/kpi/equipment（设备指标）</li>
        <li>GET /mes/bi/kpi/material（物料指标）</li>
      </ul>
      <p class="tip-muted">左侧菜单可进入各 MES 业务模块进行数据维护。</p>
    </el-card>
  </div>
</template>

<script setup>
import { onMounted, reactive } from 'vue'
import { Refresh } from '@element-plus/icons-vue'
import request from '@/utils/request'

const cards = [
  {
    key: 'production',
    title: '生产看板',
    subTitle: 'Production KPI',
    endpoint: '/mes/bi/kpi/production',
    icon: 'DataLine',
    color: '#409eff'
  },
  {
    key: 'quality',
    title: '质量看板',
    subTitle: 'Quality KPI',
    endpoint: '/mes/bi/kpi/quality',
    icon: 'CircleCheck',
    color: '#67c23a'
  },
  {
    key: 'equipment',
    title: '设备看板',
    subTitle: 'Equipment KPI',
    endpoint: '/mes/bi/kpi/equipment',
    icon: 'Cpu',
    color: '#e6a23c'
  },
  {
    key: 'material',
    title: '物料看板',
    subTitle: 'Material KPI',
    endpoint: '/mes/bi/kpi/material',
    icon: 'Box',
    color: '#f56c6c'
  }
]

// 常见字段的中文标题映射
const LABELS = {
  totalOrders: '工单总数',
  completedOrders: '已完成工单',
  inProgressOrders: '执行中工单',
  planQty: '计划数量',
  completedQty: '完成数量',
  qualifiedQty: '合格数量',
  unqualifiedQty: '不合格数量',
  scrapQty: '报废数量',
  outputQty: '产出数量',
  passRate: '一次合格率',
  defectRate: '不良率',
  scrapRate: '报废率',
  inspectionCount: '检验单数',
  nonconformityCount: '不合格数',
  reworkCount: '返工数',
  totalEquipment: '设备总数',
  runningCount: '运行设备数',
  faultCount: '故障设备数',
  alarmCount: '报警次数',
  oee: 'OEE',
  availability: '开动率',
  performance: '性能效率',
  quality: '良品率',
  runMinutes: '运行时长(分)',
  stopMinutes: '停机时长(分)',
  totalMaterials: '物料总数',
  shortageCount: '缺料项数',
  consumptionQty: '消耗数量',
  requirementQty: '需求数量',
  kitRate: '齐套率',
  wipQty: '在制数量'
}

const states = reactive({
  production: { loading: false, error: '' },
  quality: { loading: false, error: '' },
  equipment: { loading: false, error: '' },
  material: { loading: false, error: '' }
})

const metrics = reactive({
  production: [],
  quality: [],
  equipment: [],
  material: []
})

function labelOf(key) {
  return LABELS[key] || key
}

function formatValue(value) {
  if (value === null || value === undefined || value === '') {
    return '-'
  }
  if (typeof value === 'number') {
    return Number.isInteger(value) ? value : Number(value.toFixed(2))
  }
  if (typeof value === 'object') {
    return JSON.stringify(value)
  }
  return value
}

function loadOne(key, endpoint) {
  states[key].loading = true
  states[key].error = ''
  request({ url: endpoint, method: 'get', silent: true })
    .then((res) => {
      const data = res.data || {}
      const rows = []
      Object.keys(data).forEach((k) => {
        const v = data[k]
        if (v !== null && typeof v === 'object') {
          return
        }
        rows.push({ key: k, label: labelOf(k), value: formatValue(v) })
      })
      metrics[key] = rows
    })
    .catch(() => {
      metrics[key] = []
      states[key].error = '指标接口暂不可用'
    })
    .finally(() => {
      states[key].loading = false
    })
}

function loadAll() {
  cards.forEach((card) => loadOne(card.key, card.endpoint))
}

onMounted(() => {
  loadAll()
})
</script>

<style scoped>
.dashboard-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 16px;
}

.dashboard-header h2 {
  margin: 0;
  font-size: 18px;
}

.kpi-card {
  margin-bottom: 16px;
}

.kpi-head {
  display: flex;
  align-items: center;
  gap: 12px;
}

.kpi-icon {
  width: 44px;
  height: 44px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
}

.kpi-title .name {
  font-size: 16px;
  font-weight: 600;
  color: #303133;
}

.kpi-title .sub {
  font-size: 12px;
  color: #a8abb2;
}

.kpi-metrics {
  min-height: 120px;
}

.metric-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 4px 0;
  border-bottom: 1px dashed #f0f0f0;
}

.metric-row:last-child {
  border-bottom: none;
}

.metric-label {
  color: #606266;
  font-size: 13px;
}

.metric-value {
  font-weight: 600;
  color: #303133;
}

.kpi-empty {
  min-height: 120px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 6px;
  color: #c0c4cc;
  font-size: 13px;
}

.tip-card ul {
  margin: 8px 0;
  padding-left: 20px;
  color: #606266;
}

.tip-muted {
  color: #909399;
  font-size: 13px;
}
</style>
