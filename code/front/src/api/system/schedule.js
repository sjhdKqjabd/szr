import request from '@/utils/request'

// 创建
export function createSchedule(data) {
  return request({
    url: '/course/schedule/create',
    method: 'post',
    data: data
  })
}

// 更新
export function updateSchedule(data) {
  return request({
    url: '/course/schedule/update',
    method: 'put',
    data: data
  })
}

// 删除
export function deleteSchedule(id) {
  return request({
    url: '/course/schedule/delete?id=' + id,
    method: 'delete'
  })
}

// 获得
export function getSchedule(id) {
  return request({
    url: '/course/schedule/get?id=' + id,
    method: 'get'
  })
}

// 获得分页
export function getSchedulePage(query) {
  return request({
    url: '/course/schedule/page',
    method: 'get',
    params: query
  })
}

// 导出 Excel
export function exportScheduleExcel(query) {
  return request({
    url: '/course/schedule/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

export  function getClassTable(params) {
  return request({
    url:'/course/schedule/getClassTable',
    method:'post',
    data:params
  })
}
