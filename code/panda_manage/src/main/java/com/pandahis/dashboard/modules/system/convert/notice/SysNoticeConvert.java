package com.pandahis.dashboard.modules.system.convert.notice;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.controller.notice.vo.SysNoticeCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.notice.vo.SysNoticeRespVO;
import com.pandahis.dashboard.modules.system.controller.notice.vo.SysNoticeUpdateReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.notice.SysNoticeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysNoticeConvert {

    SysNoticeConvert INSTANCE = Mappers.getMapper(SysNoticeConvert.class);

    PageResult<SysNoticeRespVO> convertPage(PageResult<SysNoticeDO> page);

    SysNoticeRespVO convert(SysNoticeDO bean);

    SysNoticeDO convert(SysNoticeUpdateReqVO bean);

    SysNoticeDO convert(SysNoticeCreateReqVO bean);

}
