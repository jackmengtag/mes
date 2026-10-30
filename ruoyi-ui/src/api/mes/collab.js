import request from '@/utils/request'

// 查询供应链协同列表
export function listCollab(query) {
  return request({ url: '/mes/collab/list', method: 'get', params: query })
}

// 查询供应链协同详细
export function getCollab(collabId) {
  return request({ url: '/mes/collab/' + collabId, method: 'get' })
}

// 新增供应链协同
export function addCollab(data) {
  return request({ url: '/mes/collab', method: 'post', data: data })
}

// 修改供应链协同
export function updateCollab(data) {
  return request({ url: '/mes/collab', method: 'put', data: data })
}

// 删除供应链协同
export function delCollab(collabId) {
  return request({ url: '/mes/collab/' + collabId, method: 'delete' })
}
