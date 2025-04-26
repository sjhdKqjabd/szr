package com.pandahis.dashboard.modules.system.service.logger;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.logger.operatelog.core.service.OperateLogFrameworkService;
import com.pandahis.dashboard.modules.system.controller.logger.vo.operatelog.SysOperateLogExportReqVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.operatelog.SysOperateLogPageReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.logger.SysOperateLogDO;

import java.util.List;

/**
 * 操作日志 Service 接口
 */
public interface SysOperateLogService extends OperateLogFrameworkService {

    /**
     * 获得操作日志分页列表
     *
     * @param reqVO 分页条件
     * @return 操作日志分页列表
     */
    PageResult<SysOperateLogDO> getOperateLogPage(SysOperateLogPageReqVO reqVO);

    /**
     * 获得操作日志列表
     *
     * @param reqVO 列表条件
     * @return 日志列表
     */
    List<SysOperateLogDO> getOperateLogs(SysOperateLogExportReqVO reqVO);

}
