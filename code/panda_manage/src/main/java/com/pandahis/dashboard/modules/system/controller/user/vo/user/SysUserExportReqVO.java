package com.pandahis.dashboard.modules.system.controller.user.vo.user;

import com.pandahis.dashboard.util.date.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel(value = "用户导出 Request VO", description = "参数和 SysUserPageReqVO 是一致的")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserExportReqVO {

    @ApiModelProperty(value = "用户账号", example = "system", notes = "模糊匹配")
    private String username;

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

}
