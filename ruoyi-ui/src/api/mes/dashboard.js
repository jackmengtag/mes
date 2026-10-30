import request from '@/utils/request'

// 生产总览
export function getOverview() {
  return request({ url: '/mes/dashboard/overview', method: 'get' })
}

// 供应链总览
export function getScm() {
  return request({ url: '/mes/dashboard/scm', method: 'get' })
}

// 车间大屏
export function getBoard() {
  return request({ url: '/mes/dashboard/board', method: 'get' })
}

// 统计分析
export function getAnalysis(range) {
  return request({ url: '/mes/dashboard/analysis', method: 'get', params: { range } })
}
