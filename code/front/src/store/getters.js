const getters = {
  sidebar: state => state.app.sidebar,
  size: state => state.app.size,
  device: state => state.app.device,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  deptId:state =>state.user.deptId,
  userId:state =>state.user.userId,
  roles: state => state.user.roles,
  userInfo: state => state.user,
  permissions: state => state.user.permissions,
  permission_routes:state=>state.permission.routes,
  sidebarRouters:state => state.permission.sidebarRouters,
  dict_datas: state=>state.dict.dictDatas
}
export default getters
