import request from '@/utils/request'

// 查询实时库存列表
export function listStock(query) {
  return request({ url: '/mes/stock/list', method: 'get', params: query })
}

// 查询实时库存详细
export function getStock(stockId) {
  return request({ url: '/mes/stock/' + stockId, method: 'get' })
}

// 新增实时库存
export function addStock(data) {
  return request({ url: '/mes/stock', method: 'post', data: data })
}

// 修改实时库存
export function updateStock(data) {
  return request({ url: '/mes/stock', method: 'put', data: data })
}

// 删除实时库存
export function delStock(stockId) {
  return request({ url: '/mes/stock/' + stockId, method: 'delete' })
}
