import request from '@/utils/request'

// 创建发送消息表
export function createSendMsg(data) {
  return request({
    url: '/panda/send-msg/create',
    method: 'post',
    data: data
  })
}

// 更新发送消息表
export function updateSendMsg(data) {
  return request({
    url: '/panda/send-msg/update',
    method: 'put',
    data: data
  })
}

// 删除发送消息表
export function deleteSendMsg(id) {
  return request({
    url: '/panda/send-msg/delete?id=' + id,
    method: 'delete'
  })
}

// 获得发送消息表
export function getSendMsg(id) {
  return request({
    url: '/panda/send-msg/get?id=' + id,
    method: 'get'
  })
}

// 获得发送消息表分页
export function getSendMsgPage(query) {
  return request({
    url: '/panda/send-msg/page',
    method: 'get',
    params: query
  })
}

// 导出发送消息表 Excel
export function exportSendMsgExcel(query) {
  return request({
    url: '/panda/send-msg/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}


export function getSendMsgWithEachOtherUnreaded(data) {
  return request({
    url: '/panda/send-contact/getSendMsgWithEachOtherUnreaded',
    method: 'post',
    data,

  })
}
