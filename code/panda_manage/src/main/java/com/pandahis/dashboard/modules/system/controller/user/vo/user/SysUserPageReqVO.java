package com.pandahis.dashboard.modules.system.controller.user.vo.user;

import com.pandahis.dashboard.common.pojo.PageParam;
import com.pandahis.dashboard.util.date.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel("用户分页 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SysUserPageReqVO extends PageParam {

    @ApiModelProperty(value = "用户账号", example = "system", notes = "模糊匹配")
    private String username;

    private Long id;

    @ApiModelProperty(value = "手机号码", example = "system", notes = "模糊匹配")
    private String mobile;

    @ApiModelProperty(value = "展示状态", example = "1", notes = "参见 SysCommonStatusEnum 枚举类")
    private Integer status;

    @ApiModelProperty(value = "开始时间", example = "2020-10-24")
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date beginTime;

    @ApiModelProperty(value = "结束时间", example = "2020-10-24")
    @DateTimeFormat(pattern = DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date endTime;

    @ApiModelProperty(value = "科室编号", example = "1024", notes = "同时筛选子科室")
    private Long deptId;


    private String roleCode;
}
