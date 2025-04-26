package com.pandahis.dashboard.modules.system.dal.mysql.auth;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.framework.mybatis.core.query.QueryWrapperX;
import com.pandahis.dashboard.modules.system.controller.auth.vo.session.SysUserSessionPageReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.auth.SysUserSessionDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Mapper
public interface SysUserSessionMapper extends BaseMapperX<SysUserSessionDO> {

    default PageResult<SysUserSessionDO> selectPage(SysUserSessionPageReqVO reqVO, Collection<Long> userIds) {
        return selectPage(reqVO, new QueryWrapperX<SysUserSessionDO>()
                .inIfPresent("user_id", userIds)
                .likeIfPresent("user_ip", reqVO.getUserIp()));
    }

    default List<SysUserSessionDO> selectListBySessionTimoutLt() {
        return selectList(new QueryWrapperX<SysUserSessionDO>().lt("session_timeout",new Date()));
    }

    default List<SysUserSessionDO> selectListBySessionTimoutLtExceptSelf(SysUserDO  sysUserDO) {
        return selectList(new QueryWrapperX<SysUserSessionDO>()
              .lt("session_timeout",new Date())
              .ne("user_id",sysUserDO)
        );
    }
}
