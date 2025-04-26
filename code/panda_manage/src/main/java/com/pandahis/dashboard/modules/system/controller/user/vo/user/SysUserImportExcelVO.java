package com.pandahis.dashboard.modules.system.controller.user.vo.user;

import com.pandahis.dashboard.framework.excel.core.annotations.DictFormat;
import com.pandahis.dashboard.framework.excel.core.convert.DictConvert;
import com.pandahis.dashboard.modules.system.enums.dict.SysDictTypeEnum;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户 Excel 导入 VO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUserImportExcelVO {

    @ExcelProperty("登录名称")
    private String username;

    @ExcelProperty("用户名称")
    private String nickname;

    @ExcelProperty("科室编号")
    private Long deptId;

    @ExcelProperty("用户邮箱")
    private String email;

    @ExcelProperty("手机号码")
    private String mobile;

    @ExcelProperty(value = "用户性别", converter = DictConvert.class)
    @DictFormat(SysDictTypeEnum.SYS_USER_SEX)
    private Integer sex;

    @ExcelProperty(value = "账号状态", converter = DictConvert.class)
    @DictFormat(SysDictTypeEnum.SYS_COMMON_STATUS)
    private Integer status;

}
