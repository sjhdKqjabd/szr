package com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord;

import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.*;
import com.pandahis.dashboard.modules.dev.patRecord.convert.patRecord.PandaPatRecordConvert;
import com.pandahis.dashboard.modules.dev.patRecord.dal.dataobject.patRecord.PandaPatRecordDO;
import com.pandahis.dashboard.modules.dev.patRecord.service.patRecord.PandaPatRecordService;
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



@Api(tags = "就诊")
@RestController
@RequestMapping("/panda/pat-record")
@Validated
public class PandaPatRecordController {

    @Resource
    private PandaPatRecordService patRecordService;

    @PostMapping("/create")
    @ApiOperation("创建就诊")

    public CommonResult<Long> createPatRecord(@Valid @RequestBody PandaPatRecordCreateReqVO createReqVO) {
        return CommonResult.success(patRecordService.createPatRecord(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新就诊")

    public CommonResult<Boolean> updatePatRecord(@Valid @RequestBody PandaPatRecordUpdateReqVO updateReqVO) {
        patRecordService.updatePatRecord(updateReqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除就诊")
    @ApiImplicitParam(name = "id", value = "编号", required = true)

    public CommonResult<Boolean> deletePatRecord(@RequestParam("id") Long id) {
        patRecordService.deletePatRecord(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得就诊")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)

    public CommonResult<PandaPatRecordRespVO> getPatRecord(@RequestParam("id") Long id) {
        PandaPatRecordDO patRecord = patRecordService.getPatRecord(id);
        return CommonResult.success(PandaPatRecordConvert.INSTANCE.convert(patRecord));
    }

    @GetMapping("/list")
    @ApiOperation("获得就诊列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    public CommonResult<List<PandaPatRecordRespVO>> getPatRecordList(@RequestParam("ids") Collection<Long> ids) {
        List<PandaPatRecordDO> list = patRecordService.getPatRecordList(ids);
        return CommonResult.success(PandaPatRecordConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得就诊分页")
    public CommonResult<PageResult<PandaPatRecordRespVO>> getPatRecordPage(@Valid PandaPatRecordPageReqVO pageVO) {
        PageResult<PandaPatRecordDO> pageResult = patRecordService.getPatRecordPage(pageVO);
        return CommonResult.success(PandaPatRecordConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出就诊 Excel")

    @OperateLog(type = EXPORT)
    public void exportPatRecordExcel(@Valid PandaPatRecordExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PandaPatRecordDO> list = patRecordService.getPatRecordList(exportReqVO);
        // 导出 Excel
        List<PandaPatRecordExcelVO> datas = PandaPatRecordConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "就诊.xls", "数据", PandaPatRecordExcelVO.class, datas);
    }

}
