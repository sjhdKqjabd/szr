import request from '@/utils/request'

// 创建就诊
export function createPatRecord(data) {
  return request({
    url: '/panda/pat-record/create',
    method: 'post',
    data: data
  })
}

// 更新就诊
export function updatePatRecord(data) {
  return request({
    url: '/panda/pat-record/update',
    method: 'put',
    data: data
  })
}

// 删除就诊
export function deletePatRecord(id) {
  return request({
    url: '/panda/pat-record/delete?id=' + id,
    method: 'delete'
  })
}

// 获得就诊
export function getPatRecord(id) {
  return request({
    url: '/panda/pat-record/get?id=' + id,
    method: 'get'
  })
}

// 获得就诊分页
export function getPatRecordPage(query) {
  return request({
    url: '/panda/pat-record/page',
    method: 'get',
    params: query
  })
}

// 导出就诊 Excel
export function exportPatRecordExcel(query) {
  return request({
    url: '/panda/pat-record/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
