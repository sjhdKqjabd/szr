import request from '@/utils/request'

export function  getDeptCouns(data){
   return request({
      url:'/panda/statistic/getDeptCounts',
      method:'post',
      data:data
   })
}
