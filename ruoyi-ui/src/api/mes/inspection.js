import request from '@/utils/request'

// 查询质量检验列表
export function listInspection(query) {
  return request({ url: '/mes/inspection/list', method: 'get', params: query })
}

// 查询质量检验详细
export function getInspection(inspectionId) {
  return request({ url: '/mes/inspection/' + inspectionId, method: 'get' })
}

// 新增质量检验
export function addInspection(data) {
  return request({ url: '/mes/inspection', method: 'post', data: data })
}

// 修改质量检验
export function updateInspection(data) {
  return request({ url: '/mes/inspection', method: 'put', data: data })
}

// 删除质量检验
export function delInspection(inspectionId) {
  return request({ url: '/mes/inspection/' + inspectionId, method: 'delete' })
}
