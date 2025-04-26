import  request from '@/utils/request';

export  function getUserByUserName(username) {
  return request({
    url:'/system/user/getUserByUsername?username='+username,
    method:'post'
  })

}


export function getUsersByDeptName(data) {
  return request({
    url:'/system/user/getUsersByDeptName',
    data,
    method:'post'
  })
}

export  function getUserByIdCard(data) {
  return request({
    url:'/system/user/getUserByIdCard',
    data,
    method:'post'
  })

}
export  function getUserPagesByUserName(params) {
  return request({
    url:'/system/user/getUserPagesByUserName',
    data:params,
    method:'post'
  })

}

export function registerUser(data) {
 return request({
   url:'/system/user/register',
   method:'post',
   data:data
 })
}

