import request from '@/utils/request'

// 查询采购到货列表
export function listArrival(query) {
  return request({ url: '/mes/arrival/list', method: 'get', params: query })
}

// 查询采购到货详细
export function getArrival(arrivalId) {
  return request({ url: '/mes/arrival/' + arrivalId, method: 'get' })
}

// 新增采购到货
export function addArrival(data) {
  return request({ url: '/mes/arrival', method: 'post', data: data })
}

// 修改采购到货
export function updateArrival(data) {
  return request({ url: '/mes/arrival', method: 'put', data: data })
}

// 删除采购到货
export function delArrival(arrivalId) {
  return request({ url: '/mes/arrival/' + arrivalId, method: 'delete' })
}
