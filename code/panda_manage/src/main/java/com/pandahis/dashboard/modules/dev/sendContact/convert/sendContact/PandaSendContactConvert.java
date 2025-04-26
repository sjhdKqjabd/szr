package com.pandahis.dashboard.modules.dev.sendContact.convert.sendContact;

import java.util.*;

import com.pandahis.dashboard.common.pojo.PageResult;

import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactCreateReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactExcelVO;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactRespVO;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactUpdateReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.dal.dataobject.sendContact.PandaSendContactDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 联系人表 Convert
 *
 * @author 源码乐园
 */
@Mapper
public interface PandaSendContactConvert {

    PandaSendContactConvert INSTANCE = Mappers.getMapper(PandaSendContactConvert.class);

    PandaSendContactDO convert(PandaSendContactCreateReqVO bean);

    PandaSendContactDO convert(PandaSendContactUpdateReqVO bean);

    PandaSendContactRespVO convert(PandaSendContactDO bean);

    List<PandaSendContactRespVO> convertList(List<PandaSendContactDO> list);

    PageResult<PandaSendContactRespVO> convertPage(PageResult<PandaSendContactDO> page);

    List<PandaSendContactExcelVO> convertList02(List<PandaSendContactDO> list);

}
