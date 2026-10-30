import request from '@/utils/request'

// 查询销售出库列表
export function listShipment(query) {
  return request({ url: '/mes/shipment/list', method: 'get', params: query })
}

// 查询销售出库详细
export function getShipment(shipmentId) {
  return request({ url: '/mes/shipment/' + shipmentId, method: 'get' })
}

// 新增销售出库
export function addShipment(data) {
  return request({ url: '/mes/shipment', method: 'post', data: data })
}

// 修改销售出库
export function updateShipment(data) {
  return request({ url: '/mes/shipment', method: 'put', data: data })
}

// 删除销售出库
export function delShipment(shipmentId) {
  return request({ url: '/mes/shipment/' + shipmentId, method: 'delete' })
}
