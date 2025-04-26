package com.pandahis.dashboard.modules.infra.dal.mysql.config;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.framework.mybatis.core.query.QueryWrapperX;
import com.pandahis.dashboard.modules.infra.controller.config.vo.InfConfigExportReqVO;
import com.pandahis.dashboard.modules.infra.controller.config.vo.InfConfigPageReqVO;
import com.pandahis.dashboard.modules.infra.dal.dataobject.config.InfConfigDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InfConfigMapper extends BaseMapperX<InfConfigDO> {

    default InfConfigDO selectByKey(String key) {
        return selectOne(new QueryWrapper<InfConfigDO>().eq("`key`", key));
    }

    default PageResult<InfConfigDO> selectPage(InfConfigPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<InfConfigDO>()
                .likeIfPresent("name", reqVO.getName())
                .likeIfPresent("`key`", reqVO.getKey())
                .eqIfPresent("`type`", reqVO.getType())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime()));
    }

    default List<InfConfigDO> selectList(InfConfigExportReqVO reqVO) {
        return selectList(new QueryWrapperX<InfConfigDO>()
                .likeIfPresent("name", reqVO.getName())
                .likeIfPresent("`key`", reqVO.getKey())
                .eqIfPresent("`type`", reqVO.getType())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime()));
    }

}
