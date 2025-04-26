import request from '@/utils/request'

// 创建挂号表
export function createRegisterInfo(data) {
  return request({
    url: '/panda/register-info/create',
    method: 'post',
    data: data
  })
}

// 更新挂号表
export function updateRegisterInfo(data) {
  return request({
    url: '/panda/register-info/update',
    method: 'put',
    data: data
  })
}

// 删除挂号表
export function deleteRegisterInfo(id) {
  return request({
    url: '/panda/register-info/delete?id=' + id,
    method: 'delete'
  })
}

// 获得挂号表
export function getRegisterInfo(id) {
  return request({
    url: '/panda/register-info/get?id=' + id,
    method: 'get'
  })
}

// 获得挂号表分页
export function getRegisterInfoPage(query) {
  return request({
    url: '/panda/register-info/page',
    method: 'get',
    params: query
  })
}

// 导出挂号表 Excel
export function exportRegisterInfoExcel(query) {
  return request({
    url: '/panda/register-info/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
