package com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition;

import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.*;
import com.pandahis.dashboard.modules.dev.importCondition.convert.importCondition.PandaImportConditionConvert;
import com.pandahis.dashboard.modules.dev.importCondition.dal.dataobject.importCondition.PandaImportConditionDO;
import com.pandahis.dashboard.modules.dev.importCondition.service.importCondition.PandaImportConditionService;
import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.excel.core.util.ExcelUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import io.swagger.annotations.*;

import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.pandahis.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import static com.pandahis.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum.*;


@Api(tags = "导医信息")
@RestController
@RequestMapping("/panda/import-condition")
@Validated
public class PandaImportConditionController {

    @Resource
    private PandaImportConditionService importConditionService;

    @PostMapping("/create")
    @ApiOperation("创建导医信息")
    public CommonResult<Long> createImportCondition(@Valid @RequestBody PandaImportConditionCreateReqVO createReqVO) {
        return CommonResult.success(importConditionService.createImportCondition(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新导医信息")
    public CommonResult<Boolean> updateImportCondition(@Valid @RequestBody PandaImportConditionUpdateReqVO updateReqVO) {
        importConditionService.updateImportCondition(updateReqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除导医信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true)

    public CommonResult<Boolean> deleteImportCondition(@RequestParam("id") Long id) {
        importConditionService.deleteImportCondition(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得导医信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)

    public CommonResult<PandaImportConditionRespVO> getImportCondition(@RequestParam("id") Long id) {
        PandaImportConditionDO importCondition = importConditionService.getImportCondition(id);
        return CommonResult.success(PandaImportConditionConvert.INSTANCE.convert(importCondition));
    }

    @GetMapping("/list")
    @ApiOperation("获得导医信息列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)

    public CommonResult<List<PandaImportConditionRespVO>> getImportConditionList(@RequestParam("ids") Collection<Long> ids) {
        List<PandaImportConditionDO> list = importConditionService.getImportConditionList(ids);
        return CommonResult.success(PandaImportConditionConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得导医信息分页")

    public CommonResult<PageResult<PandaImportConditionRespVO>> getImportConditionPage(@Valid PandaImportConditionPageReqVO pageVO) {
        PageResult<PandaImportConditionDO> pageResult = importConditionService.getImportConditionPage(pageVO);
        return CommonResult.success(PandaImportConditionConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出导医信息 Excel")

    @OperateLog(type = EXPORT)
    public void exportImportConditionExcel(@Valid PandaImportConditionExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PandaImportConditionDO> list = importConditionService.getImportConditionList(exportReqVO);
        // 导出 Excel
        List<PandaImportConditionExcelVO> datas = PandaImportConditionConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "导医信息.xls", "数据", PandaImportConditionExcelVO.class, datas);
    }

}
