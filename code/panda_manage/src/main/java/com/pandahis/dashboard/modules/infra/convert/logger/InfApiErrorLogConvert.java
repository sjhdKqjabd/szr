package com.pandahis.dashboard.modules.infra.convert.logger;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.logger.apilog.core.service.dto.ApiErrorLogCreateDTO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogExcelVO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogRespVO;
import com.pandahis.dashboard.modules.infra.dal.dataobject.logger.InfApiErrorLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * API 错误日志 Convert
 *
 * @author 源码乐园
 */
@Mapper
public interface InfApiErrorLogConvert {

    InfApiErrorLogConvert INSTANCE = Mappers.getMapper(InfApiErrorLogConvert.class);

    InfApiErrorLogDO convert(ApiErrorLogCreateDTO bean);

    InfApiErrorLogRespVO convert(InfApiErrorLogDO bean);

    PageResult<InfApiErrorLogRespVO> convertPage(PageResult<InfApiErrorLogDO> page);

    List<InfApiErrorLogExcelVO> convertList02(List<InfApiErrorLogDO> list);

}
