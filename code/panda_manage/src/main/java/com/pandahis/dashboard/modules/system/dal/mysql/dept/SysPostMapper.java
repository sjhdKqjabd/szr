package com.pandahis.dashboard.modules.system.dal.mysql.dept;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.framework.mybatis.core.query.QueryWrapperX;
import com.pandahis.dashboard.modules.system.controller.dept.vo.post.SysPostExportReqVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.post.SysPostPageReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysPostDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface SysPostMapper extends BaseMapperX<SysPostDO> {

    default List<SysPostDO> selectList(Collection<Long> ids, Collection<Integer> statuses) {
        return selectList(new QueryWrapperX<SysPostDO>().inIfPresent("id", ids)
                .inIfPresent("status", statuses));
    }

    default PageResult<SysPostDO> selectPage(SysPostPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<SysPostDO>()
                .likeIfPresent("name", reqVO.getName())
                .eqIfPresent("status", reqVO.getStatus()));
    }

    default List<SysPostDO> selectList(SysPostExportReqVO reqVO) {
        return selectList(new QueryWrapperX<SysPostDO>().likeIfPresent("name", reqVO.getName())
                .eqIfPresent("status", reqVO.getStatus()));
    }

    default SysPostDO selectByName(String name) {
        return selectOne(new QueryWrapper<SysPostDO>().eq("name", name));
    }

    default SysPostDO selectByCode(String code) {
        return selectOne(new QueryWrapper<SysPostDO>().eq("code", code));
    }

}
