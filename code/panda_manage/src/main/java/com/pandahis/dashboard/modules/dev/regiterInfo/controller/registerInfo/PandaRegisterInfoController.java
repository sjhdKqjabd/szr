package com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo;

import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.*;
import com.pandahis.dashboard.modules.dev.regiterInfo.convert.registerInfo.PandaRegisterInfoConvert;
import com.pandahis.dashboard.modules.dev.regiterInfo.dal.dataobject.registerInfo.PandaRegisterInfoDO;
import com.pandahis.dashboard.modules.dev.regiterInfo.service.registerInfo.PandaRegisterInfoService;
import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.*;

import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.pandahis.dashboard.framework.excel.core.util.ExcelUtils;

import com.pandahis.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import static com.pandahis.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum.*;


@Api(tags = "挂号表")
@RestController
@RequestMapping("/panda/register-info")
@Validated
public class PandaRegisterInfoController {

    @Resource
    private PandaRegisterInfoService registerInfoService;

    @PostMapping("/create")
    @ApiOperation("创建挂号表")

    public CommonResult<Long> createRegisterInfo(@Valid @RequestBody PandaRegisterInfoCreateReqVO createReqVO) {
        return CommonResult.success(registerInfoService.createRegisterInfo(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新挂号表")

    public CommonResult<Boolean> updateRegisterInfo(@Valid @RequestBody PandaRegisterInfoUpdateReqVO updateReqVO) {
        registerInfoService.updateRegisterInfo(updateReqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除挂号表")
    @ApiImplicitParam(name = "id", value = "编号", required = true)

    public CommonResult<Boolean> deleteRegisterInfo(@RequestParam("id") Long id) {
        registerInfoService.deleteRegisterInfo(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得挂号表")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)

    public CommonResult<PandaRegisterInfoRespVO> getRegisterInfo(@RequestParam("id") Long id) {
        PandaRegisterInfoDO registerInfo = registerInfoService.getRegisterInfo(id);
        return CommonResult.success(PandaRegisterInfoConvert.INSTANCE.convert(registerInfo));
    }

    @GetMapping("/list")
    @ApiOperation("获得挂号表列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)

    public CommonResult<List<PandaRegisterInfoRespVO>> getRegisterInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<PandaRegisterInfoDO> list = registerInfoService.getRegisterInfoList(ids);
        return CommonResult.success(PandaRegisterInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得挂号表分页")

    public CommonResult<PageResult<PandaRegisterInfoRespVO>> getRegisterInfoPage(@Valid PandaRegisterInfoPageReqVO pageVO) {
        PageResult<PandaRegisterInfoDO> pageResult = registerInfoService.getRegisterInfoPage(pageVO);
        return CommonResult.success(PandaRegisterInfoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出挂号表 Excel")

    @OperateLog(type = EXPORT)
    public void exportRegisterInfoExcel(@Valid PandaRegisterInfoExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PandaRegisterInfoDO> list = registerInfoService.getRegisterInfoList(exportReqVO);
        // 导出 Excel
        List<PandaRegisterInfoExcelVO> datas = PandaRegisterInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "挂号表.xls", "数据", PandaRegisterInfoExcelVO.class, datas);
    }

}
