package com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo;

import com.pandahis.dashboard.common.pojo.PageParam;
import com.pandahis.dashboard.util.date.DateUtils;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel("就诊分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PandaPatRecordPageReqVO extends PageParam {

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

    @ApiModelProperty(value = "诊断结果")
    private String result;

    @ApiModelProperty(value = "病情描述")
    private String descInfo;

    @ApiModelProperty(value = "接诊医生名")
    private String docName;

    @ApiModelProperty(value = "挂号科室")
    private String deptName;

    @ApiModelProperty(value = "病人名称")
    private String petName;

    @ApiModelProperty(value = "病人id")
    private Long petId;

}
