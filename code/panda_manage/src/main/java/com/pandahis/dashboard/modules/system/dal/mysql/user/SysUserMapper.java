package com.pandahis.dashboard.modules.system.dal.mysql.user;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.framework.mybatis.core.query.QueryWrapperX;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import com.pandahis.dashboard.modules.system.controller.user.vo.user.SysUserExportReqVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.user.SysUserPageReqVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapperX<SysUserDO> {

    default SysUserDO selectByUsername(String username) {
        return selectOne(new QueryWrapper<SysUserDO>().eq("username", username));
    }

    default SysUserDO selectByMobile(String mobile) {
        return selectOne(new QueryWrapper<SysUserDO>().eq("mobile", mobile));
    }

    default SysUserDO selectByEmail(String email) {
        return selectOne(new QueryWrapper<SysUserDO>().eq("email", email));
    }

    default PageResult<SysUserDO> selectPage(SysUserPageReqVO reqVO, Collection<Long> deptIds) {
        return selectPage(reqVO, new QueryWrapperX<SysUserDO>()
                .likeIfPresent("username", reqVO.getUsername())
                .likeIfPresent("mobile", reqVO.getMobile())
                .eqIfPresent("status", reqVO.getStatus())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime())
                .inIfPresent("dept_id", deptIds));
    }
    default PageResult<SysUserDO> selectPage(SysUserPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<SysUserDO>()
                .likeIfPresent("username", reqVO.getUsername())
                );
    }

    default List<SysUserDO> selectList(SysUserExportReqVO reqVO, Collection<Long> deptIds) {
        return selectList(new QueryWrapperX<SysUserDO>().likeIfPresent("username", reqVO.getUsername())
                .likeIfPresent("mobile", reqVO.getMobile())
                .eqIfPresent("status", reqVO.getStatus())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime())
                .inIfPresent("dept_id", deptIds));
    }

    default List<SysUserDO> selectListByNickname(String nickname) {
        return selectList(new QueryWrapperX<SysUserDO>().like("nickname", nickname));
    }

    default List<SysUserDO> selectListByUsername(String username) {
        return selectList(new QueryWrapperX<SysUserDO>().like("username", username));
    }


    default  List<SysUserDO> selectListBYUsernameOrId(String username){
        return  selectList(new QueryWrapperX<SysUserDO>().like("username",username)
              .or().eq("id",username));
    };

    List<SysUserDO> queryUserByRoleCode(@Param("vo") SysUserPageReqVO vo);

}

