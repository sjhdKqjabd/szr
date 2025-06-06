package com.pandahis.dashboard.modules.system.controller.dict;

import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.excel.core.util.ExcelUtils;
import com.pandahis.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import com.pandahis.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum;
import com.pandahis.dashboard.modules.system.controller.dict.vo.type.*;
import com.pandahis.dashboard.modules.system.convert.dict.SysDictTypeConvert;
import com.pandahis.dashboard.modules.system.dal.dataobject.dict.SysDictTypeDO;
import com.pandahis.dashboard.modules.system.service.dict.SysDictTypeService;
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

@Api(tags = "字典类型")
@RestController
@RequestMapping("/system/dict-type")
@Validated
public class SysDictTypeController {

    @Resource
    private SysDictTypeService dictTypeService;

    @PostMapping("/create")
    @ApiOperation("创建字典类型")

    public CommonResult<Long> createDictType(@Valid @RequestBody SysDictTypeCreateReqVO reqVO) {
        Long dictTypeId = dictTypeService.createDictType(reqVO);
        return CommonResult.success(dictTypeId);
    }

    @PostMapping("update")
    @ApiOperation("修改字典类型")

    public CommonResult<Boolean> updateDictType(@Valid @RequestBody SysDictTypeUpdateReqVO reqVO) {
        dictTypeService.updateDictType(reqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除字典类型")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)

    public CommonResult<Boolean> deleteDictType(Long id) {
        dictTypeService.deleteDictType(id);
        return CommonResult.success(true);
    }

    @ApiOperation("/获得字典类型的分页列表")
    @GetMapping("/page")

    public CommonResult<PageResult<SysDictTypeRespVO>> pageDictTypes(@Valid SysDictTypePageReqVO reqVO) {
        return CommonResult.success(SysDictTypeConvert.INSTANCE.convertPage(dictTypeService.getDictTypePage(reqVO)));
    }

    @ApiOperation("/查询字典类型详细")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @GetMapping(value = "/get")

    public CommonResult<SysDictTypeRespVO> getDictType(@RequestParam("id") Long id) {
        return CommonResult.success(SysDictTypeConvert.INSTANCE.convert(dictTypeService.getDictType(id)));
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获得全部字典类型列表", notes = "包括开启 + 禁用的字典类型，主要用于前端的下拉选项")
    // 无需添加权限认证，因为前端全局都需要
    public CommonResult<List<SysDictTypeSimpleRespVO>> listSimpleDictTypes() {
        List<SysDictTypeDO> list = dictTypeService.getDictTypeList();
        return CommonResult.success(SysDictTypeConvert.INSTANCE.convertList(list));
    }

    @ApiOperation("导出数据类型")
    @GetMapping("/export")

    @OperateLog(type = OperateTypeEnum.EXPORT)
    public void export(HttpServletResponse response, @Valid SysDictTypeExportReqVO reqVO) throws IOException {
        List<SysDictTypeDO> list = dictTypeService.getDictTypeList(reqVO);
        List<SysDictTypeExcelVO> data = SysDictTypeConvert.INSTANCE.convertList02(list);
        // 输出
        ExcelUtils.write(response, "字典类型.xls", "类型列表", SysDictTypeExcelVO.class, data);
    }

}
