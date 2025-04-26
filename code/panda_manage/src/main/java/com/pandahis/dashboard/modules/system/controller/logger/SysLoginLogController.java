package com.pandahis.dashboard.modules.system.controller.logger;

import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.excel.core.util.ExcelUtils;
import com.pandahis.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog.SysLoginLogExcelVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog.SysLoginLogExportReqVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog.SysLoginLogPageReqVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog.SysLoginLogRespVO;
import com.pandahis.dashboard.modules.system.convert.logger.SysLoginLogConvert;
import com.pandahis.dashboard.modules.system.dal.dataobject.logger.SysLoginLogDO;
import com.pandahis.dashboard.modules.system.service.logger.SysLoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.pandahis.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Api(tags = "登陆日志")
@RestController
@RequestMapping("/system/login-log")
@Validated
public class SysLoginLogController {

    @Resource
    private SysLoginLogService loginLogService;

    @GetMapping("/page")
    @ApiOperation("获得登陆日志分页列表")
    @PreAuthorize("@ss.hasPermission('system:login-log:query')")
    public CommonResult<PageResult<SysLoginLogRespVO>> getLoginLogPage(@Valid SysLoginLogPageReqVO reqVO) {
        PageResult<SysLoginLogDO> page = loginLogService.getLoginLogPage(reqVO);
        return CommonResult.success(SysLoginLogConvert.INSTANCE.convertPage(page));
    }

    @GetMapping("/export")
    @ApiOperation("导出登陆日志 Excel")
    @PreAuthorize("@ss.hasPermission('system:login-log:export')")
    @OperateLog(type = EXPORT)
    public void exportLoginLog(HttpServletResponse response, @Valid SysLoginLogExportReqVO reqVO) throws IOException {
        List<SysLoginLogDO> list = loginLogService.getLoginLogList(reqVO);
        // 拼接数据
        List<SysLoginLogExcelVO> data = SysLoginLogConvert.INSTANCE.convertList(list);
        // 输出
        ExcelUtils.write(response, "登陆日志.xls", "数据列表", SysLoginLogExcelVO.class, data);
    }

}
