import request from '@/utils/request'

// 查询质量追溯列表
export function listTrace(query) {
  return request({ url: '/mes/trace/list', method: 'get', params: query })
}

// 查询质量追溯详细
export function getTrace(traceId) {
  return request({ url: '/mes/trace/' + traceId, method: 'get' })
}

// 新增质量追溯
export function addTrace(data) {
  return request({ url: '/mes/trace', method: 'post', data: data })
}

// 修改质量追溯
export function updateTrace(data) {
  return request({ url: '/mes/trace', method: 'put', data: data })
}

// 删除质量追溯
export function delTrace(traceId) {
  return request({ url: '/mes/trace/' + traceId, method: 'delete' })
}
