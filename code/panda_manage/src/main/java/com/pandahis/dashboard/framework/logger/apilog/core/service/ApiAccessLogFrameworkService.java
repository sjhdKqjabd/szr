package com.pandahis.dashboard.framework.logger.apilog.core.service;

import com.pandahis.dashboard.framework.logger.apilog.core.service.dto.ApiAccessLogCreateDTO;

import javax.validation.Valid;
import java.util.concurrent.Future;

/**
 * API 访问日志 Framework Service 接口
 *
 * @author 源码乐园
 */
public interface ApiAccessLogFrameworkService {

    /**
     * 创建 API 访问日志
     *
     * @param createDTO 创建信息
     * @return 是否创建成功
     */
    Future<Boolean> createApiAccessLogAsync(@Valid ApiAccessLogCreateDTO createDTO);

}
