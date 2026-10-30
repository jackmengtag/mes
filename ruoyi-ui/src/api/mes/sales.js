import request from '@/utils/request'

// 查询销售订单列表
export function listSalesOrder(query) {
  return request({ url: '/mes/sales/list', method: 'get', params: query })
}

// 查询销售订单详细
export function getSalesOrder(salesId) {
  return request({ url: '/mes/sales/' + salesId, method: 'get' })
}

// 新增销售订单
export function addSalesOrder(data) {
  return request({ url: '/mes/sales', method: 'post', data: data })
}

// 修改销售订单
export function updateSalesOrder(data) {
  return request({ url: '/mes/sales', method: 'put', data: data })
}

// 删除销售订单
export function delSalesOrder(salesId) {
  return request({ url: '/mes/sales/' + salesId, method: 'delete' })
}
