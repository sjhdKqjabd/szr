package com.pandahis.dashboard.modules.infra.dal.mysql.file;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.framework.mybatis.core.query.QueryWrapperX;
import com.pandahis.dashboard.modules.infra.controller.file.vo.InfFilePageReqVO;
import com.pandahis.dashboard.modules.infra.dal.dataobject.file.InfFileDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InfFileMapper extends BaseMapperX<InfFileDO> {

    default Integer selectCountById(String id) {
        return selectCount("id", id);
    }

    default PageResult<InfFileDO> selectPage(InfFilePageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<InfFileDO>()
                .likeIfPresent("id", reqVO.getId())
                .likeIfPresent("type", reqVO.getType())
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc("create_time"));
    }

}
