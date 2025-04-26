package com.pandahis.dashboard.modules.dev.stastic.controller;


import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.modules.dev.stastic.dal.dataobject.DeptCountDto;
import com.pandahis.dashboard.modules.dev.stastic.service.StatisticService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "统计类")
@RestController
@RequestMapping("/panda/statistic")
@Validated
public class StatisticController {



    @Autowired
    StatisticService statisticService;


    @RequestMapping("/getDeptCounts")
    public CommonResult<List<DeptCountDto>> getDeptCounts(@RequestBody DeptCountDto deptCountDto){
        List<DeptCountDto> deptCounts = statisticService.getDeptCounts(deptCountDto);
        return CommonResult.success(deptCounts);
    }




}
