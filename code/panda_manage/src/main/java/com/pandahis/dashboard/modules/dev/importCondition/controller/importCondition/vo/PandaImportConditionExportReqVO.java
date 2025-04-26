package com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo;

import com.pandahis.dashboard.util.date.DateUtils;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel(value = "导医信息 Excel 导出 Request VO", description = "参数和 PandaImportConditionPageReqVO 是一致的")
@Data
public class PandaImportConditionExportReqVO {

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

    @ApiModelProperty(value = "病情")
    private String conditionInfo;

    @ApiModelProperty(value = "挂号科室")
    private String deptName;

}
