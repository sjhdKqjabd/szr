import request from '@/utils/request'

// 创建联系人表
export function createSendContact(data) {
  return request({
    url: '/panda/send-contact/create',
    method: 'post',
    data: data
  })
}

// 更新联系人表
export function updateSendContact(data) {
  return request({
    url: '/panda/send-contact/update',
    method: 'put',
    data: data
  })
}

// 删除联系人表
export function deleteSendContact(id) {
  return request({
    url: '/panda/send-contact/delete?id=' + id,
    method: 'delete'
  })
}

// 获得联系人表
export function getSendContact(id) {
  return request({
    url: '/panda/send-contact/get?id=' + id,
    method: 'get'
  })
}

// 获得联系人表分页
export function getSendContactPage(query) {
  return request({
    url: '/panda/send-contact/page',
    method: 'get',
    params: query
  })
}

// 导出联系人表 Excel
export function exportSendContactExcel(query) {
  return request({
    url: '/panda/send-contact/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}


export function getSendContactList(data) {
  return request({
    url: '/panda/send-contact/getSendContactList',
    method: 'post',
    data
  })
}

export function getSendMsgWithEachOther(data) {
  return request({
    url: '/panda/send-contact/getSendMsgWithEachOther',
    method: 'post',
    data
  })
}

export function sendMsgChat(data) {
  return request({
    url: '/panda/send-msg/chat',
    method: 'post',
    data
  })
}
