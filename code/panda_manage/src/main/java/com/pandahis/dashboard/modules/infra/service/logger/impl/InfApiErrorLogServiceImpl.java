package com.pandahis.dashboard.modules.infra.service.logger.impl;

import com.pandahis.dashboard.common.exception.util.ServiceExceptionUtil;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.logger.apilog.core.service.dto.ApiErrorLogCreateDTO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogExportReqVO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogPageReqVO;
import com.pandahis.dashboard.modules.infra.convert.logger.InfApiErrorLogConvert;
import com.pandahis.dashboard.modules.infra.dal.dataobject.logger.InfApiErrorLogDO;
import com.pandahis.dashboard.modules.infra.dal.mysql.logger.InfApiErrorLogMapper;
import com.pandahis.dashboard.modules.infra.enums.InfErrorCodeConstants;
import com.pandahis.dashboard.modules.infra.enums.logger.InfApiErrorLogProcessStatusEnum;
import com.pandahis.dashboard.modules.infra.service.logger.InfApiErrorLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

import static com.pandahis.dashboard.common.exception.util.ServiceExceptionUtil.exception;

/**
 * API 错误日志 Service 实现类
 *
 * @author 源码乐园
 */
@Service
@Validated
public class InfApiErrorLogServiceImpl implements InfApiErrorLogService {

    @Resource
    private InfApiErrorLogMapper apiErrorLogMapper;

    @Override
    @Async
    public Future<Boolean> createApiErrorLogAsync(ApiErrorLogCreateDTO createDTO) {
        InfApiErrorLogDO apiErrorLog = InfApiErrorLogConvert.INSTANCE.convert(createDTO);
        apiErrorLog.setProcessStatus(InfApiErrorLogProcessStatusEnum.INIT.getStatus());
        int insert = apiErrorLogMapper.insert(apiErrorLog);
        return new AsyncResult<>(insert == 1);
    }

    @Override
    public PageResult<InfApiErrorLogDO> getApiErrorLogPage(InfApiErrorLogPageReqVO pageReqVO) {
        return apiErrorLogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<InfApiErrorLogDO> getApiErrorLogList(InfApiErrorLogExportReqVO exportReqVO) {
        return apiErrorLogMapper.selectList(exportReqVO);
    }

    @Override
    public void updateApiErrorLogProcess(Long id, Integer processStatus, Long processUserId) {
        InfApiErrorLogDO errorLog = apiErrorLogMapper.selectById(id);
        if (errorLog == null) {
            throw ServiceExceptionUtil.exception(InfErrorCodeConstants.API_ERROR_LOG_NOT_FOUND);
        }
        if (!InfApiErrorLogProcessStatusEnum.INIT.getStatus().equals(errorLog.getProcessStatus())) {
            throw ServiceExceptionUtil.exception(InfErrorCodeConstants.API_ERROR_LOG_PROCESSED);
        }
        // 标记处理
        apiErrorLogMapper.updateById(InfApiErrorLogDO.builder().id(id).processStatus(processStatus)
                .processUserId(processUserId).processTime(new Date()).build());
    }

}
