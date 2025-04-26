package com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo;

import com.pandahis.dashboard.common.pojo.PageParam;
import com.pandahis.dashboard.util.date.DateUtils;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel("导医信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PandaImportConditionPageReqVO extends PageParam {

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
