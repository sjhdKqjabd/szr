package com.pandahis.dashboard.modules.dev.sendContact.dal.mysql.sendContact;

import java.util.*;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.mybatis.core.query.QueryWrapperX;
import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactExportReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactPageReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.dal.dataobject.sendContact.PandaSendContactDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 联系人表 Mapper
 *
 * @author 源码乐园
 */
@Mapper
public interface PandaSendContactMapper extends BaseMapperX<PandaSendContactDO> {

    default PageResult<PandaSendContactDO> selectPage(PandaSendContactPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<PandaSendContactDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .likeIfPresent("display_name", reqVO.getDisplayName())
                .eqIfPresent("avatar", reqVO.getAvatar())
                .eqIfPresent("index", reqVO.getIndex())
                .eqIfPresent("unread", reqVO.getUnread())
                .betweenIfPresent("last_send_time", reqVO.getBeginLastSendTime(), reqVO.getEndLastSendTime())
                .eqIfPresent("last_content", reqVO.getLastContent())
                .orderByDesc("id")        );
    }

    default List<PandaSendContactDO> selectList(PandaSendContactExportReqVO reqVO) {
        return selectList(new QueryWrapperX<PandaSendContactDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .likeIfPresent("display_name", reqVO.getDisplayName())
                .eqIfPresent("avatar", reqVO.getAvatar())
                .eqIfPresent("index", reqVO.getIndex())
                .eqIfPresent("unread", reqVO.getUnread())
                .betweenIfPresent("last_send_time", reqVO.getBeginLastSendTime(), reqVO.getEndLastSendTime())
                .eqIfPresent("last_content", reqVO.getLastContent())
                .orderByDesc("id")        );
    }

}
