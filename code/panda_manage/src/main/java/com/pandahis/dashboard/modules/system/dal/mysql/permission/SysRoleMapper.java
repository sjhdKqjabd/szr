package com.pandahis.dashboard.modules.system.dal.mysql.permission;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.framework.mybatis.core.query.QueryWrapperX;
import com.pandahis.dashboard.modules.system.controller.permission.vo.role.SysRoleExportReqVO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.role.SysRolePageReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysRoleDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapperX<SysRoleDO> {

    default PageResult<SysRoleDO> selectPage(SysRolePageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<SysRoleDO>().likeIfPresent("name", reqVO.getName())
                .likeIfPresent("code", reqVO.getCode())
                .eqIfPresent("status", reqVO.getStatus())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime()));
    }

    default List<SysRoleDO> listRoles(SysRoleExportReqVO reqVO) {
        return selectList(new QueryWrapperX<SysRoleDO>().likeIfPresent("name", reqVO.getName())
                .likeIfPresent("code", reqVO.getCode())
                .eqIfPresent("status", reqVO.getStatus())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime()));
    }

    default SysRoleDO selectByName(String name) {
        return selectOne(new QueryWrapperX<SysRoleDO>().eq("name", name));
    }

    default SysRoleDO selectByCode(String code) {
        return selectOne(new QueryWrapperX<SysRoleDO>().eq("code", code));
    }

    default List<SysRoleDO> selectListByStatus(@Nullable Collection<Integer> statuses) {
        return selectList(new QueryWrapperX<SysRoleDO>().in("status", statuses));
    }

    default boolean selectExistsByUpdateTimeAfter(Date maxUpdateTime) {
        return selectOne(new QueryWrapper<SysRoleDO>().select("id")
                .gt("update_time", maxUpdateTime).last("LIMIT 1")) != null;
    }

}
