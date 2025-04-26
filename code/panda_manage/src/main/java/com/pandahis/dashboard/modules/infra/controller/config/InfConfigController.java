package com.pandahis.dashboard.modules.infra.controller.config;

import com.pandahis.dashboard.common.exception.util.ServiceExceptionUtil;
import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.excel.core.util.ExcelUtils;
import com.pandahis.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import com.pandahis.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum;
import com.pandahis.dashboard.modules.infra.controller.config.vo.*;
import com.pandahis.dashboard.modules.infra.dal.dataobject.config.InfConfigDO;
import com.pandahis.dashboard.modules.infra.enums.InfErrorCodeConstants;

import com.pandahis.dashboard.modules.infra.convert.config.InfConfigConvert;
import com.pandahis.dashboard.modules.infra.service.config.InfConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.pandahis.dashboard.common.exception.util.ServiceExceptionUtil.exception;

@Api(tags = "参数配置")
@RestController
@RequestMapping("/infra/config")
@Validated
public class InfConfigController {

    @Resource
    private InfConfigService configService;

    @PostMapping("/create")
    @ApiOperation("创建参数配置")
    @PreAuthorize("@ss.hasPermission('infra:config:create')")
    public CommonResult<Long> createConfig(@Valid @RequestBody InfConfigCreateReqVO reqVO) {
        return CommonResult.success(configService.createConfig(reqVO));
    }

    @PutMapping("/update")
    @ApiOperation("修改参数配置")
    @PreAuthorize("@ss.hasPermission('infra:config:update')")
    public CommonResult<Boolean> updateConfig(@Valid @RequestBody InfConfigUpdateReqVO reqVO) {
        configService.updateConfig(reqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除参数配置")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('infra:config:delete')")
    public CommonResult<Boolean> deleteConfig(@RequestParam("id") Long id) {
        configService.deleteConfig(id);
        return CommonResult.success(true);
    }

    @GetMapping(value = "/get")
    @ApiOperation("获得参数配置")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('infra:config:query')")
    public CommonResult<InfConfigRespVO> getConfig(@RequestParam("id") Long id) {
        return CommonResult.success(InfConfigConvert.INSTANCE.convert(configService.getConfig(id)));
    }

    @GetMapping(value = "/get-value-by-key")
    @ApiOperation(value = "根据参数键名查询参数值", notes = "敏感配置，不允许返回给前端")
    @ApiImplicitParam(name = "key", value = "参数键", required = true, example = "yunai.biz.username", dataTypeClass = String.class)
    public CommonResult<String> getConfigKey(@RequestParam("key") String key) {
        InfConfigDO config = configService.getConfigByKey(key);
        if (config == null) {
            return null;
        }
        if (config.getSensitive()) {
            throw ServiceExceptionUtil.exception(InfErrorCodeConstants.CONFIG_GET_VALUE_ERROR_IF_SENSITIVE);
        }
        return CommonResult.success(config.getValue());
    }

    @GetMapping("/page")
    @ApiOperation("获取参数配置分页")
    @PreAuthorize("@ss.hasPermission('infra:config:query')")
    public CommonResult<PageResult<InfConfigRespVO>> getConfigPage(@Valid InfConfigPageReqVO reqVO) {
        PageResult<InfConfigDO> page = configService.getConfigPage(reqVO);
        return CommonResult.success(InfConfigConvert.INSTANCE.convertPage(page));
    }

    @GetMapping("/export")
    @ApiOperation("导出参数配置")
    @PreAuthorize("@ss.hasPermission('infra:config:export')")
    @OperateLog(type = OperateTypeEnum.EXPORT)
    public void exportSysConfig(@Valid InfConfigExportReqVO reqVO,
                                HttpServletResponse response) throws IOException {
        List<InfConfigDO> list = configService.getConfigList(reqVO);
        // 拼接数据
        List<InfConfigExcelVO> datas = InfConfigConvert.INSTANCE.convertList(list);
        // 输出
        ExcelUtils.write(response, "参数配置.xls", "数据", InfConfigExcelVO.class, datas);
    }

}
