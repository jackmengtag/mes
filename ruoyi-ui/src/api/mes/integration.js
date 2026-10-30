import request from '@/utils/request'

// 查询系统集成接口列表
export function listInterface(query) {
  return request({ url: '/mes/integration/list', method: 'get', params: query })
}

// 查询系统集成接口详细
export function getInterface(interfaceId) {
  return request({ url: '/mes/integration/' + interfaceId, method: 'get' })
}

// 新增系统集成接口
export function addInterface(data) {
  return request({ url: '/mes/integration', method: 'post', data: data })
}

// 修改系统集成接口
export function updateInterface(data) {
  return request({ url: '/mes/integration', method: 'put', data: data })
}

// 删除系统集成接口
export function delInterface(interfaceId) {
  return request({ url: '/mes/integration/' + interfaceId, method: 'delete' })
}
