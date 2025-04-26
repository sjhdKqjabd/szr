package com.pandahis.dashboard.modules.tool.controller.codegen.vo.table;

import com.pandahis.dashboard.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.pandahis.dashboard.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("表定义分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ToolCodegenTablePageReqVO extends PageParam {

    @ApiModelProperty(value = "表名称", example = "system", notes = "模糊匹配")
    private String tableName;

    @ApiModelProperty(value = "表描述", example = "智慧", notes = "模糊匹配")
    private String tableComment;

    @ApiModelProperty(value = "开始创建时间", example = "2020-10-24 00:00:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date beginCreateTime;

    @ApiModelProperty(value = "结束创建时间", example = "2020-10-24 23:59:59")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date endCreateTime;

}
