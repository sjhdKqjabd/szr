package com.pandahis.dashboard.modules.system.convert.logger;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog.SysLoginLogCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog.SysLoginLogExcelVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog.SysLoginLogRespVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.logger.SysLoginLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysLoginLogConvert {

    SysLoginLogConvert INSTANCE = Mappers.getMapper(SysLoginLogConvert.class);

    SysLoginLogDO convert(SysLoginLogCreateReqVO bean);

    PageResult<SysLoginLogRespVO> convertPage(PageResult<SysLoginLogDO> page);

    List<SysLoginLogExcelVO> convertList(List<SysLoginLogDO> list);

}
