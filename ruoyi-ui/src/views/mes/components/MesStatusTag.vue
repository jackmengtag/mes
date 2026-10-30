<template>
  <el-tag :type="meta.type" size="small" effect="light">{{ meta.label }}</el-tag>
</template>

<script setup name="MesStatusTag">
import { computed } from 'vue'

const props = defineProps({
  value: { type: String, default: '' },
  type: { type: String, default: 'common' }
})

const dicts = {
  common: {
    draft: ['草稿', 'info'], released: ['已发布', 'primary'], running: ['进行中', 'primary'],
    finished: ['已完成', 'success'], locked: ['已锁定', 'warning'], scheduled: ['已排程', 'info'],
    waiting: ['待料', 'warning'], warning: ['预警', 'warning'], pending: ['待处理', 'info'],
    partial: ['部分完成', 'warning'], received: ['已完成', 'success'], overdue: ['逾期', 'danger'],
    closed: ['已关闭', 'info'], ordered: ['已下单', 'primary'], shipped: ['已发货', 'primary'],
    invoiced: ['已开票', 'success'], producing: ['生产中', 'primary'], abnormal: ['异常', 'danger'],
    open: ['待处理', 'danger'], isolated: ['已隔离', 'warning'], reviewing: ['评审中', 'warning'],
    disposed: ['已处置', 'primary'], pass: ['合格', 'success'], fail: ['不合格', 'danger'],
    concession: ['让步接收', 'warning'], success: ['成功', 'success'], failed: ['失败', 'danger'],
    posted: ['已记账', 'success'], stocked: ['已入库', 'success'], rejected: ['拒收', 'danger'],
    qualified: ['合格', 'success'], normal: ['正常', 'success'], shortage: ['缺料', 'danger'],
    idle: ['待机', 'info'], alarm: ['报警', 'danger'], stop: ['停机', 'info'], run: ['运行', 'success'],
    handled: ['已处理', 'success'], active: ['未处理', 'danger'], stopped: ['停用', 'info'],
    urgent: ['加急', 'danger'], high: ['高', 'danger'], serious: ['严重', 'danger'], info: ['提示', 'info']
  },
  plan: { draft: ['草稿', 'info'], released: ['已发布', 'primary'], running: ['执行中', 'primary'], finished: ['已完成', 'success'], locked: ['已锁定', 'warning'] },
  workOrder: { scheduled: ['已排程', 'info'], running: ['生产中', 'primary'], waiting: ['待料', 'warning'], warning: ['预警', 'warning'], finished: ['已完成', 'success'] },
  purchase: { ordered: ['已下单', 'primary'], partial: ['部分到货', 'warning'], received: ['已到货', 'success'], overdue: ['交付逾期', 'danger'], closed: ['已关闭', 'info'] },
  sales: { pending: ['待发货', 'info'], producing: ['生产中', 'primary'], partial: ['部分发货', 'warning'], shipped: ['已发货', 'success'], invoiced: ['已开票', 'success'], received: ['已收款', 'success'] },
  quality: { open: ['待处理', 'danger'], isolated: ['已隔离', 'warning'], reviewing: ['评审中', 'warning'], disposed: ['已处置', 'primary'], released: ['已放行', 'success'] },
  voucher: { pending: ['待记账', 'warning'], posted: ['已记账', 'success'], success: ['已同步', 'success'], failed: ['同步失败', 'danger'] },
  equipment: { run: ['运行', 'success'], idle: ['待机', 'info'], alarm: ['报警', 'danger'], stop: ['停机', 'info'] },
  collab: { pending: ['待确认', 'info'], running: ['执行中', 'primary'], finished: ['已完成', 'success'], overdue: ['已逾期', 'danger'], abnormal: ['异常', 'danger'] },
  stock: { normal: ['正常', 'success'], warning: ['预警', 'warning'], shortage: ['缺料', 'danger'], idle: ['呆滞', 'info'] }
}

const meta = computed(() => {
  const map = dicts[props.type] || dicts.common
  const hit = map[props.value] || dicts.common[props.value]
  return hit ? { label: hit[0], type: hit[1] } : { label: props.value || '-', type: 'info' }
})
</script>
