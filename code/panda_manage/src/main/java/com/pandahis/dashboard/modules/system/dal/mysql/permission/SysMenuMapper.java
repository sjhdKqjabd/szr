package com.pandahis.dashboard.modules.system.dal.mysql.permission;

import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.framework.mybatis.core.query.QueryWrapperX;
import com.pandahis.dashboard.modules.system.controller.permission.vo.menu.SysMenuListReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysMenuDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface SysMenuMapper extends BaseMapperX<SysMenuDO> {

    default SysMenuDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(new QueryWrapper<SysMenuDO>().eq("parent_id", parentId)
                .eq("name", name));
    }

    default Integer selectCountByParentId(Long parentId) {
        return selectCount(new QueryWrapper<SysMenuDO>().eq("parent_id", parentId));
    }

    default List<SysMenuDO> selectList(SysMenuListReqVO reqVO) {
        return selectList(new QueryWrapperX<SysMenuDO>().likeIfPresent("name", reqVO.getName())
            .eqIfPresent("status", reqVO.getStatus()));
    }

    default boolean selectExistsByUpdateTimeAfter(Date maxUpdateTime) {
        return selectOne(new QueryWrapper<SysMenuDO>().select("id")
                .gt("update_time", maxUpdateTime).last("LIMIT 1")) != null;
    }

}
