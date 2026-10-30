import request from '@/utils/request'

// 查询出入库流水列表
export function listStockTxn(query) {
  return request({ url: '/mes/stockTxn/list', method: 'get', params: query })
}

// 查询出入库流水详细
export function getStockTxn(txnId) {
  return request({ url: '/mes/stockTxn/' + txnId, method: 'get' })
}

// 新增出入库流水
export function addStockTxn(data) {
  return request({ url: '/mes/stockTxn', method: 'post', data: data })
}

// 修改出入库流水
export function updateStockTxn(data) {
  return request({ url: '/mes/stockTxn', method: 'put', data: data })
}

// 删除出入库流水
export function delStockTxn(txnId) {
  return request({ url: '/mes/stockTxn/' + txnId, method: 'delete' })
}
