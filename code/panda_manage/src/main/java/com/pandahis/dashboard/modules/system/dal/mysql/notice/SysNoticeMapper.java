package com.pandahis.dashboard.modules.system.dal.mysql.notice;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.framework.mybatis.core.query.QueryWrapperX;
import com.pandahis.dashboard.modules.system.controller.notice.vo.SysNoticePageReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.notice.SysNoticeDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysNoticeMapper extends BaseMapperX<SysNoticeDO> {

    default PageResult<SysNoticeDO> selectPage(SysNoticePageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<SysNoticeDO>()
                .likeIfPresent("title", reqVO.getTitle())
                .eqIfPresent("status", reqVO.getStatus())
                .notLikeIfPresent("readed",reqVO.getReaded())
                .notLikeIfPresent("readed",reqVO.getReaded())
        );
    }


    IPage<SysNoticeDO> pageNotices(IPage page, @Param("reqVo") SysNoticePageReqVO reqVO);

}
