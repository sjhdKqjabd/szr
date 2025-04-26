package com.pandahis.dashboard.modules.dev.patRecord.dal.mysql.patRecord;

import java.util.*;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.framework.mybatis.core.query.QueryWrapperX;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordExportReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordPageReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.dal.dataobject.patRecord.PandaPatRecordDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 就诊 Mapper
 *
 * @author 源码乐园
 */
@Mapper
public interface PandaPatRecordMapper extends BaseMapperX<PandaPatRecordDO> {

    default PageResult<PandaPatRecordDO> selectPage(PandaPatRecordPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<PandaPatRecordDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .eqIfPresent("result", reqVO.getResult())
                .eqIfPresent("desc_info", reqVO.getDescInfo())
                .eqIfPresent("pet_id", reqVO.getPetId())
                .likeIfPresent("doc_name", reqVO.getDocName())
                .likeIfPresent("dept_name", reqVO.getDeptName())
                .likeIfPresent("pet_name", reqVO.getPetName())
                .orderByDesc("id")        );
    }

    default List<PandaPatRecordDO> selectList(PandaPatRecordExportReqVO reqVO) {
        return selectList(new QueryWrapperX<PandaPatRecordDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .eqIfPresent("result", reqVO.getResult())
                .eqIfPresent("desc_info", reqVO.getDescInfo())
                .likeIfPresent("doc_name", reqVO.getDocName())
                .likeIfPresent("dept_name", reqVO.getDeptName())
                .likeIfPresent("pet_name", reqVO.getPetName())
                .orderByDesc("id")        );
    }

}
