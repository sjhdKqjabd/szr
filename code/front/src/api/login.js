import  request from '@/utils/request';



export function  login(username,password,code,uuid) {
  const data={
      username,password,code,uuid
  }
   return request({
       url:'/login',
       method:'post',
       data:data
   })

}

export  function  getCodeImg() {
    return request({
       url:'/system/captcha/get-image',
       method:'get'
    })
}


export function logout() {
   return request({
     url: '/logout',
     method: 'post'
   })
}

//获取用户额外信息

export  function getInfo() {
   return request({
     url:'/get-permission-info',
     method:'get'
   })
}
