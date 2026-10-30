import request from '@/utils/request'

// 采购到货登记（到货单 + 入库 + 库存 + 凭证）
export function registerArrival(data) {
  return request({ url: '/mes/biz/arrival', method: 'post', data: data })
}

// 销售出库（库存扣减 + 凭证）
export function shipOrder(data) {
  return request({ url: '/mes/biz/shipment', method: 'post', data: data })
}

// 生产报工（工单进度 + 质量问题）
export function submitReport(data) {
  return request({ url: '/mes/biz/report', method: 'post', data: data })
}

// 不合格品处置
export function disposeIssue(data) {
  return request({ url: '/mes/biz/issue/dispose', method: 'post', data: data })
}

// 批量记账
export function postVouchers(ids) {
  return request({ url: '/mes/biz/voucher/post', method: 'post', data: ids })
}

// U8C 凭证重推
export function syncVoucher(voucherId) {
  return request({ url: '/mes/biz/voucher/sync/' + voucherId, method: 'post' })
}

// 其他入库 / 其他出库
export function adjustStock(data) {
  return request({ url: '/mes/biz/stock/adjust', method: 'post', data: data })
}

// 仓库调拨
export function transferStock(data) {
  return request({ url: '/mes/biz/stock/transfer', method: 'post', data: data })
}

// 计划发布
export function publishPlan(planId) {
  return request({ url: '/mes/biz/plan/publish/' + planId, method: 'post' })
}

// 计划生成工单
export function planToWorkOrder(planId) {
  return request({ url: '/mes/biz/plan/workOrder/' + planId, method: 'post' })
}

// 协同异常生成质量问题
export function collabToQuality(collabId) {
  return request({ url: '/mes/biz/collab/quality/' + collabId, method: 'post' })
}

// 设备指令
export function equipmentCommand(data) {
  return request({ url: '/mes/biz/equipment/command', method: 'post', data: data })
}

// 报警处理
export function handleAlarm(alarmId) {
  return request({ url: '/mes/biz/alarm/handle/' + alarmId, method: 'put' })
}

// 接口测试连接
export function testInterface(interfaceId) {
  return request({ url: '/mes/biz/interface/test/' + interfaceId, method: 'post' })
}

// 质量追溯详情
export function traceDetail(keyword) {
  return request({ url: '/mes/biz/trace/detail', method: 'get', params: { keyword } })
}
