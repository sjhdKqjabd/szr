package com.pandahis.dashboard.modules.system.service.logger.impl;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.controller.logger.vo.operatelog.SysOperateLogCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.operatelog.SysOperateLogExportReqVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.operatelog.SysOperateLogPageReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.logger.SysOperateLogDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import com.pandahis.dashboard.modules.system.dal.mysql.logger.SysOperateLogMapper;
import com.pandahis.dashboard.modules.system.service.logger.SysOperateLogService;
import com.pandahis.dashboard.modules.system.service.user.SysUserService;
import com.pandahis.dashboard.util.collection.CollectionUtils;
import com.pandahis.dashboard.util.string.StrUtils;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.pandahis.dashboard.modules.system.convert.logger.SysOperateLogConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

@Service
@Slf4j
public class SysOperateLogServiceImpl implements SysOperateLogService {

    @Resource
    private SysOperateLogMapper operateLogMapper;

    @Resource
    private SysUserService userService;

    @Override
    @Async
    public Future<Boolean> createOperateLogAsync(SysOperateLogCreateReqVO reqVO) {
        boolean success = false;
        try {
            SysOperateLogDO logDO = SysOperateLogConvert.INSTANCE.convert(reqVO);
            logDO.setJavaMethodArgs(StrUtils.maxLength(logDO.getJavaMethodArgs(), SysOperateLogDO.JAVA_METHOD_ARGS_MAX_LENGTH));
            logDO.setResultData(StrUtils.maxLength(logDO.getResultData(), SysOperateLogDO.RESULT_MAX_LENGTH));
            success = operateLogMapper.insert(logDO) == 1;
        } catch (Throwable throwable) {
            // 仅仅打印日志，不对外抛出。原因是，还是要保留现场数据。
            log.error("[createOperateLogAsync][记录操作日志异常，日志为 ({})]", reqVO, throwable);
        }
        return new AsyncResult<>(success);
    }

    @Override
    public PageResult<SysOperateLogDO> getOperateLogPage(SysOperateLogPageReqVO reqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
        if (StrUtil.isNotEmpty(reqVO.getUserNickname())) {
            userIds = CollectionUtils.convertSet(userService.getUsersByNickname(reqVO.getUserNickname()), SysUserDO::getId);
            if (CollUtil.isEmpty(userIds)) {
                return PageResult.empty();
            }
        }
        // 查询分页
        return operateLogMapper.selectPage(reqVO, userIds);
    }

    @Override
    public List<SysOperateLogDO> getOperateLogs(SysOperateLogExportReqVO reqVO) {
        // 处理基于用户昵称的查询
        Collection<Long> userIds = null;
        if (StrUtil.isNotEmpty(reqVO.getUserNickname())) {
            userIds = CollectionUtils.convertSet(userService.getUsersByNickname(reqVO.getUserNickname()), SysUserDO::getId);
            if (CollUtil.isEmpty(userIds)) {
                return Collections.emptyList();
            }
        }
        // 查询列表
        return operateLogMapper.selectList(reqVO, userIds);
    }

}
