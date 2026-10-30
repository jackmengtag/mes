import request from '@/utils/request'

// 查询生产工单列表
export function listWorkOrder(query) {
  return request({ url: '/mes/workOrder/list', method: 'get', params: query })
}

// 查询生产工单详细
export function getWorkOrder(workOrderId) {
  return request({ url: '/mes/workOrder/' + workOrderId, method: 'get' })
}

// 新增生产工单
export function addWorkOrder(data) {
  return request({ url: '/mes/workOrder', method: 'post', data: data })
}

// 修改生产工单
export function updateWorkOrder(data) {
  return request({ url: '/mes/workOrder', method: 'put', data: data })
}

// 删除生产工单
export function delWorkOrder(workOrderId) {
  return request({ url: '/mes/workOrder/' + workOrderId, method: 'delete' })
}
