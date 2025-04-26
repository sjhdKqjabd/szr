package com.pandahis.dashboard.modules.system.controller.dict;

import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.excel.core.util.ExcelUtils;
import com.pandahis.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import com.pandahis.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum;
import com.pandahis.dashboard.modules.system.controller.dict.vo.data.*;
import com.pandahis.dashboard.modules.system.convert.dict.SysDictDataConvert;
import com.pandahis.dashboard.modules.system.dal.dataobject.dict.SysDictDataDO;
import com.pandahis.dashboard.modules.system.service.dict.SysDictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Api(tags = "字典数据")
@RestController
@RequestMapping("/system/dict-data")
@Validated
public class SysDictDataController {

    @Resource
    private SysDictDataService dictDataService;

    @PostMapping("/create")
    @ApiOperation("新增字典数据")

    public CommonResult<Long> createDictData(@Valid @RequestBody SysDictDataCreateReqVO reqVO) {
        Long dictDataId = dictDataService.createDictData(reqVO);
        return CommonResult.success(dictDataId);
    }

    @PutMapping("update")
    @ApiOperation("修改字典数据")

    public CommonResult<Boolean> updateDictData(@Valid @RequestBody SysDictDataUpdateReqVO reqVO) {
        dictDataService.updateDictData(reqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除字典数据")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)

    public CommonResult<Boolean> deleteDictData(Long id) {
        dictDataService.deleteDictData(id);
        return CommonResult.success(true);
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获得全部字典数据列表", notes = "一般用于管理后台缓存字典数据在本地")
    // 无需添加权限认证，因为前端全局都需要
    public CommonResult<List<SysDictDataSimpleVO>> getSimpleDictDatas() {
        List<SysDictDataDO> list = dictDataService.getDictDatas();
        return CommonResult.success(SysDictDataConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("/获得字典类型的分页列表")

    public CommonResult<PageResult<SysDictDataRespVO>> getDictTypePage(@Valid SysDictDataPageReqVO reqVO) {
        return CommonResult.success(SysDictDataConvert.INSTANCE.convertPage(dictDataService.getDictDataPage(reqVO)));
    }

    @GetMapping(value = "/get")
    @ApiOperation("/查询字典数据详细")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)

    public CommonResult<SysDictDataRespVO> getDictData(@RequestParam("id") Long id) {
        return CommonResult.success(SysDictDataConvert.INSTANCE.convert(dictDataService.getDictData(id)));
    }

    @GetMapping("/export")
    @ApiOperation("导出字典数据")

    @OperateLog(type = OperateTypeEnum.EXPORT)
    public void export(HttpServletResponse response, @Valid SysDictDataExportReqVO reqVO) throws IOException {
        List<SysDictDataDO> list = dictDataService.getDictDatas(reqVO);
        List<SysDictDataExcelVO> data = SysDictDataConvert.INSTANCE.convertList02(list);
        // 输出
        ExcelUtils.write(response, "字典数据.xls", "数据列表", SysDictDataExcelVO.class, data);
    }

}
