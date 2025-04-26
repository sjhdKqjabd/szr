package com.pandahis.dashboard.modules.dev.regiterInfo.dal.mysql.registerInfo;

import java.util.*;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.mybatis.core.query.QueryWrapperX;
import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoExportReqVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoPageReqVO;

import com.pandahis.dashboard.modules.dev.regiterInfo.dal.dataobject.registerInfo.PandaRegisterInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 挂号表 Mapper
 *
 * @author 源码乐园
 */
@Mapper
public interface PandaRegisterInfoMapper extends BaseMapperX<PandaRegisterInfoDO> {

    default PageResult<PandaRegisterInfoDO> selectPage(PandaRegisterInfoPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<PandaRegisterInfoDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .likeIfPresent("pet_name", reqVO.getPetName())
                .eqIfPresent("pet_id", reqVO.getPetId())
                .likeIfPresent("dept_name", reqVO.getDeptName())
                .likeIfPresent("doc_name", reqVO.getDocName())
                .orderByDesc("id")        );
    }

    default List<PandaRegisterInfoDO> selectList(PandaRegisterInfoExportReqVO reqVO) {
        return selectList(new QueryWrapperX<PandaRegisterInfoDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .likeIfPresent("pet_name", reqVO.getPetName())
                .eqIfPresent("pet_id", reqVO.getPetId())
                .likeIfPresent("dept_name", reqVO.getDeptName())
                .likeIfPresent("doc_name", reqVO.getDocName())
                .orderByDesc("id")        );
    }

}
