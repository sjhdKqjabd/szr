package com.pandahis.dashboard.modules.tool.controller.test;

import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.excel.core.util.ExcelUtils;
import com.pandahis.dashboard.framework.logger.operatelog.core.annotations.OperateLog;
import com.pandahis.dashboard.framework.logger.operatelog.core.enums.OperateTypeEnum;
import com.pandahis.dashboard.modules.tool.controller.test.vo.*;
import com.pandahis.dashboard.modules.tool.convert.test.ToolTestDemoConvert;
import com.pandahis.dashboard.modules.tool.dal.dataobject.test.ToolTestDemoDO;
import com.pandahis.dashboard.modules.tool.service.test.ToolTestDemoService;
import cn.hutool.core.thread.ThreadUtil;
import com.baomidou.lock.annotation.Lock4j;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Api(tags = "测试示例")
@RestController
@RequestMapping("/tool/test-demo")
@Validated
public class ToolTestDemoController {

    @Resource
    private ToolTestDemoService testDemoService;

    @PostMapping("/create")
    @ApiOperation("创建测试示例")
    @PreAuthorize("@ss.hasPermission('tool:test-demo:create')")
    public CommonResult<Long> createTestDemo(@Valid @RequestBody ToolTestDemoCreateReqVO createReqVO) {
        return CommonResult.success(testDemoService.createTestDemo(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新测试示例")
    @PreAuthorize("@ss.hasPermission('tool:test-demo:update')")
    public CommonResult<Boolean> updateTestDemo(@Valid @RequestBody ToolTestDemoUpdateReqVO updateReqVO) {
        testDemoService.updateTestDemo(updateReqVO);
        return CommonResult.success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除测试示例")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('tool:test-demo:delete')")
    public CommonResult<Boolean> deleteTestDemo(@RequestParam("id") Long id) {
        testDemoService.deleteTestDemo(id);
        return CommonResult.success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得测试示例")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('tool:test-demo:query')")
    @Lock4j // 分布式锁
    public CommonResult<ToolTestDemoRespVO> getTestDemo(@RequestParam("id") Long id) {
        if (true) { // 测试分布式锁
            ThreadUtil.sleep(5, TimeUnit.SECONDS);
        }
        ToolTestDemoDO testDemo = testDemoService.getTestDemo(id);
        return CommonResult.success(ToolTestDemoConvert.INSTANCE.convert(testDemo));
    }

    @GetMapping("/list")
    @ApiOperation("获得测试示例列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('tool:test-demo:query')")
    @RateLimiter(name = "backendA")
    public CommonResult<List<ToolTestDemoRespVO>> getTestDemoList(@RequestParam("ids") Collection<Long> ids) {
        List<ToolTestDemoDO> list = testDemoService.getTestDemoList(ids);
        return CommonResult.success(ToolTestDemoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得测试示例分页")
    @PreAuthorize("@ss.hasPermission('tool:test-demo:query')")
    public CommonResult<PageResult<ToolTestDemoRespVO>> getTestDemoPage(@Valid ToolTestDemoPageReqVO pageVO) {
        PageResult<ToolTestDemoDO> pageResult = testDemoService.getTestDemoPage(pageVO);
        return CommonResult.success(ToolTestDemoConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出测试示例 Excel")
    @PreAuthorize("@ss.hasPermission('tool:test-demo:export')")
    @OperateLog(type = OperateTypeEnum.EXPORT)
    public void exportTestDemoExcel(@Valid ToolTestDemoExportReqVO exportReqVO,
                                    HttpServletResponse response) throws IOException {
        List<ToolTestDemoDO> list = testDemoService.getTestDemoList(exportReqVO);
        // 导出 Excel
        List<ToolTestDemoExcelVO> datas = ToolTestDemoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "测试示例.xls", "数据", ToolTestDemoExcelVO.class, datas);
    }

}
