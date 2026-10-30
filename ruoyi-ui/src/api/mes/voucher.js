import request from '@/utils/request'

// 查询存货核算凭证列表
export function listVoucher(query) {
  return request({ url: '/mes/voucher/list', method: 'get', params: query })
}

// 查询存货核算凭证详细
export function getVoucher(voucherId) {
  return request({ url: '/mes/voucher/' + voucherId, method: 'get' })
}

// 新增存货核算凭证
export function addVoucher(data) {
  return request({ url: '/mes/voucher', method: 'post', data: data })
}

// 修改存货核算凭证
export function updateVoucher(data) {
  return request({ url: '/mes/voucher', method: 'put', data: data })
}

// 删除存货核算凭证
export function delVoucher(voucherId) {
  return request({ url: '/mes/voucher/' + voucherId, method: 'delete' })
}
