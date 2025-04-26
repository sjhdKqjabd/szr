package com.pandahis.dashboard.modules.dev.regiterInfo.convert.registerInfo;

import java.util.*;

import com.pandahis.dashboard.common.pojo.PageResult;

import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoCreateReqVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoExcelVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoRespVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoUpdateReqVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.dal.dataobject.registerInfo.PandaRegisterInfoDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 挂号表 Convert
 *
 * @author 源码乐园
 */
@Mapper
public interface PandaRegisterInfoConvert {

    PandaRegisterInfoConvert INSTANCE = Mappers.getMapper(PandaRegisterInfoConvert.class);

    PandaRegisterInfoDO convert(PandaRegisterInfoCreateReqVO bean);

    PandaRegisterInfoDO convert(PandaRegisterInfoUpdateReqVO bean);

    PandaRegisterInfoRespVO convert(PandaRegisterInfoDO bean);

    List<PandaRegisterInfoRespVO> convertList(List<PandaRegisterInfoDO> list);

    PageResult<PandaRegisterInfoRespVO> convertPage(PageResult<PandaRegisterInfoDO> page);

    List<PandaRegisterInfoExcelVO> convertList02(List<PandaRegisterInfoDO> list);

}
