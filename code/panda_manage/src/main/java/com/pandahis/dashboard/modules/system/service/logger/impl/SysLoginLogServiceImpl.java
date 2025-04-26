package com.pandahis.dashboard.modules.system.service.logger.impl;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog.SysLoginLogCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog.SysLoginLogExportReqVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog.SysLoginLogPageReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.logger.SysLoginLogDO;
import com.pandahis.dashboard.modules.system.dal.mysql.logger.SysLoginLogMapper;
import com.pandahis.dashboard.modules.system.convert.logger.SysLoginLogConvert;
import com.pandahis.dashboard.modules.system.service.logger.SysLoginLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登陆日志 Service 实现
 */
@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {

    @Resource
    private SysLoginLogMapper loginLogMapper;

    @Override
    public void createLoginLog(SysLoginLogCreateReqVO reqVO) {
        SysLoginLogDO loginLog = SysLoginLogConvert.INSTANCE.convert(reqVO);
        loginLogMapper.insert(loginLog);
    }

    @Override
    public PageResult<SysLoginLogDO> getLoginLogPage(SysLoginLogPageReqVO reqVO) {
        return loginLogMapper.selectPage(reqVO);
    }

    @Override
    public List<SysLoginLogDO> getLoginLogList(SysLoginLogExportReqVO reqVO) {
        return loginLogMapper.selectList(reqVO);
    }

}
