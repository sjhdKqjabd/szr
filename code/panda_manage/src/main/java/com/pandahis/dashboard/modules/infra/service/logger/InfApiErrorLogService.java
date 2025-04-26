package com.pandahis.dashboard.modules.infra.service.logger;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.logger.apilog.core.service.ApiErrorLogFrameworkService;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogExportReqVO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogPageReqVO;
import com.pandahis.dashboard.modules.infra.dal.dataobject.logger.InfApiErrorLogDO;

import java.util.List;

/**
 * API 错误日志 Service 接口
 *
 * @author 源码乐园
 */
public interface InfApiErrorLogService extends ApiErrorLogFrameworkService {

    /**
     * 获得 API 错误日志分页
     *
     * @param pageReqVO 分页查询
     * @return API 错误日志分页
     */
    PageResult<InfApiErrorLogDO> getApiErrorLogPage(InfApiErrorLogPageReqVO pageReqVO);

    /**
     * 获得 API 错误日志列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return API 错误日志分页
     */
    List<InfApiErrorLogDO> getApiErrorLogList(InfApiErrorLogExportReqVO exportReqVO);

    /**
     * 更新 API 错误日志已处理
     *
     * @param id API 日志编号
     * @param processStatus 处理结果
     * @param processUserId 处理人
     */
    void updateApiErrorLogProcess(Long id, Integer processStatus, Long processUserId);

}
