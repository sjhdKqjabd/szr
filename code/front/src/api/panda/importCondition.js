import request from '@/utils/request'

// 创建导医信息
export function createImportCondition(data) {
  return request({
    url: '/panda/import-condition/create',
    method: 'post',
    data: data
  })
}

// 更新导医信息
export function updateImportCondition(data) {
  return request({
    url: '/panda/import-condition/update',
    method: 'put',
    data: data
  })
}

// 删除导医信息
export function deleteImportCondition(id) {
  return request({
    url: '/panda/import-condition/delete?id=' + id,
    method: 'delete'
  })
}

// 获得导医信息
export function getImportCondition(id) {
  return request({
    url: '/panda/import-condition/get?id=' + id,
    method: 'get'
  })
}

// 获得导医信息分页
export function getImportConditionPage(query) {
  return request({
    url: '/panda/import-condition/page',
    method: 'get',
    params: query
  })
}

// 导出导医信息 Excel
export function exportImportConditionExcel(query) {
  return request({
    url: '/panda/import-condition/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
