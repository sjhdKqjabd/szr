package com.pandahis.dashboard.modules.infra.controller.logger;

import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.excel.core.util.ExcelUtils;
import com.pandahis.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import com.pandahis.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum;
import com.pandahis.dashboard.modules.infra.dal.dataobject.logger.InfApiAccessLogDO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogExcelVO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogExportReqVO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogPageReqVO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogRespVO;
import com.pandahis.dashboard.modules.infra.convert.logger.InfApiAccessLogConvert;
import com.pandahis.dashboard.modules.infra.service.logger.InfApiAccessLogService;
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

@Api(tags = "API 访问日志")
@RestController
@RequestMapping("/infra/api-access-log")
@Validated
public class InfApiAccessLogController {

    @Resource
    private InfApiAccessLogService apiAccessLogService;

    @GetMapping("/page")
    @ApiOperation("获得API 访问日志分页")
    @PreAuthorize("@ss.hasPermission('infra:api-access-log:query')")
    public CommonResult<PageResult<InfApiAccessLogRespVO>> getApiAccessLogPage(@Valid InfApiAccessLogPageReqVO pageVO) {
        PageResult<InfApiAccessLogDO> pageResult = apiAccessLogService.getApiAccessLogPage(pageVO);
        return CommonResult.success(InfApiAccessLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出API 访问日志 Excel")
    @PreAuthorize("@ss.hasPermission('infra:api-access-log:export')")
    @OperateLog(type = OperateTypeEnum.EXPORT)
    public void exportApiAccessLogExcel(@Valid InfApiAccessLogExportReqVO exportReqVO,
                                        HttpServletResponse response) throws IOException {
        List<InfApiAccessLogDO> list = apiAccessLogService.getApiAccessLogList(exportReqVO);
        // 导出 Excel
        List<InfApiAccessLogExcelVO> datas = InfApiAccessLogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "API 访问日志.xls", "数据", InfApiAccessLogExcelVO.class, datas);
    }

}
