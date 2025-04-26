package com.pandahis.dashboard.modules.infra.convert.logger;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.logger.apilog.core.service.dto.ApiAccessLogCreateDTO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogExcelVO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogRespVO;
import com.pandahis.dashboard.modules.infra.dal.dataobject.logger.InfApiAccessLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * API 访问日志 Convert
 *
 * @author 源码乐园
 */
@Mapper
public interface InfApiAccessLogConvert {

    InfApiAccessLogConvert INSTANCE = Mappers.getMapper(InfApiAccessLogConvert.class);

    InfApiAccessLogDO convert(ApiAccessLogCreateDTO bean);

    InfApiAccessLogRespVO convert(InfApiAccessLogDO bean);

    List<InfApiAccessLogRespVO> convertList(List<InfApiAccessLogDO> list);

    PageResult<InfApiAccessLogRespVO> convertPage(PageResult<InfApiAccessLogDO> page);

    List<InfApiAccessLogExcelVO> convertList02(List<InfApiAccessLogDO> list);

}
