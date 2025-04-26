package com.pandahis.dashboard.modules.system.controller.permission.vo.role;

import com.pandahis.dashboard.common.pojo.PageParam;
import com.pandahis.dashboard.util.date.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel("角色分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRolePageReqVO extends PageParam {

    @ApiModelProperty(value = "角色名称", example = "教研", notes = "模糊匹配")
    private String name;

    @ApiModelProperty(value = "角色标识", example = "system", notes = "模糊匹配")
    private String code;

    @ApiModelProperty(value = "展示状态", example = "1", notes = "参见 SysCommonStatusEnum 枚举类")
    private Integer status;

    @ApiModelProperty(value = "开始时间", example = "2020-10-24")
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date beginTime;

    @ApiModelProperty(value = "结束时间", example = "2020-10-24")
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date endTime;

}
