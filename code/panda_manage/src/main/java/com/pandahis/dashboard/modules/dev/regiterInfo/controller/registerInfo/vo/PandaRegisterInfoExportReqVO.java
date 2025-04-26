package com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo;

import com.pandahis.dashboard.util.date.DateUtils;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel(value = "挂号表 Excel 导出 Request VO", description = "参数和 PandaRegisterInfoPageReqVO 是一致的")
@Data
public class PandaRegisterInfoExportReqVO {

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

    @ApiModelProperty(value = "就诊人")
    private String petName;

    @ApiModelProperty(value = "就诊人Id")
    private String petId;

    @ApiModelProperty(value = "科室名")
    private String deptName;

    @ApiModelProperty(value = "医生名")
    private String docName;

}
