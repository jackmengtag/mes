import request from '@/utils/request'

// 查询质量问题列表
export function listQualityIssue(query) {
  return request({ url: '/mes/quality/list', method: 'get', params: query })
}

// 查询质量问题详细
export function getQualityIssue(issueId) {
  return request({ url: '/mes/quality/' + issueId, method: 'get' })
}

// 新增质量问题
export function addQualityIssue(data) {
  return request({ url: '/mes/quality', method: 'post', data: data })
}

// 修改质量问题
export function updateQualityIssue(data) {
  return request({ url: '/mes/quality', method: 'put', data: data })
}

// 删除质量问题
export function delQualityIssue(issueId) {
  return request({ url: '/mes/quality/' + issueId, method: 'delete' })
}
