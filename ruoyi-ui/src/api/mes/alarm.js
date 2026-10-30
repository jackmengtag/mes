import request from '@/utils/request'

// 查询设备报警列表
export function listAlarm(query) {
  return request({ url: '/mes/alarm/list', method: 'get', params: query })
}

// 查询设备报警详细
export function getAlarm(alarmId) {
  return request({ url: '/mes/alarm/' + alarmId, method: 'get' })
}

// 新增设备报警
export function addAlarm(data) {
  return request({ url: '/mes/alarm', method: 'post', data: data })
}

// 修改设备报警
export function updateAlarm(data) {
  return request({ url: '/mes/alarm', method: 'put', data: data })
}

// 删除设备报警
export function delAlarm(alarmId) {
  return request({ url: '/mes/alarm/' + alarmId, method: 'delete' })
}
