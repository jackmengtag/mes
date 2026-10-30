import request from '@/utils/request'

// 查询采购订单列表
export function listPurchaseOrder(query) {
  return request({ url: '/mes/purchase/list', method: 'get', params: query })
}

// 查询采购订单详细
export function getPurchaseOrder(purchaseId) {
  return request({ url: '/mes/purchase/' + purchaseId, method: 'get' })
}

// 新增采购订单
export function addPurchaseOrder(data) {
  return request({ url: '/mes/purchase', method: 'post', data: data })
}

// 修改采购订单
export function updatePurchaseOrder(data) {
  return request({ url: '/mes/purchase', method: 'put', data: data })
}

// 删除采购订单
export function delPurchaseOrder(purchaseId) {
  return request({ url: '/mes/purchase/' + purchaseId, method: 'delete' })
}
