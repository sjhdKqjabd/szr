package com.pandahis.dashboard.modules.dev.importCondition.dal.mysql.importCondition;

import java.util.*;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.mybatis.core.query.QueryWrapperX;
import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionExportReqVO;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionPageReqVO;

import com.pandahis.dashboard.modules.dev.importCondition.dal.dataobject.importCondition.PandaImportConditionDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 导医信息 Mapper
 *
 * @author 源码乐园
 */
@Mapper
public interface PandaImportConditionMapper extends BaseMapperX<PandaImportConditionDO> {

    default PageResult<PandaImportConditionDO> selectPage(PandaImportConditionPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<PandaImportConditionDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .likeIfPresent("condition_info", reqVO.getConditionInfo())
                .likeIfPresent("dept_name", reqVO.getDeptName())
                .orderByDesc("id")        );
    }

    default List<PandaImportConditionDO> selectList(PandaImportConditionExportReqVO reqVO) {
        return selectList(new QueryWrapperX<PandaImportConditionDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .likeIfPresent("condition_info", reqVO.getConditionInfo())
                .likeIfPresent("dept_name", reqVO.getDeptName())
                .orderByDesc("id")        );
    }

}
