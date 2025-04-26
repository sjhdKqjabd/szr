package com.pandahis.dashboard.modules.system.controller.user.vo.user;

import com.pandahis.dashboard.framework.excel.core.annotations.DictFormat;
import com.pandahis.dashboard.framework.excel.core.convert.DictConvert;
import com.pandahis.dashboard.modules.system.enums.dict.SysDictTypeEnum;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 用户 Excel 导出 VO
 */
@Data
public class SysUserExcelVO {

    @ExcelProperty("用户编号")
    private Long id;

    @ExcelProperty("用户名称")
    private String username;

    @ExcelProperty("用户昵称")
    private String nickname;

    @ExcelProperty("用户邮箱")
    private String email;

    @ExcelProperty("手机号码")
    private String mobile;

    @ExcelProperty(value = "用户性别", converter = DictConvert.class)
    @DictFormat(SysDictTypeEnum.SYS_USER_SEX)
    private Integer sex;

    @ExcelProperty(value = "帐号状态", converter = DictConvert.class)
    @DictFormat(SysDictTypeEnum.SYS_COMMON_STATUS)
    private Integer status;

    @ExcelProperty("最后登录IP")
    private String loginIp;

    @ExcelProperty("最后登录时间")
    private Date loginDate;

    @ExcelProperty("科室名称")
    private String deptName;

    @ExcelProperty("科室负责人")
    private String deptLeader;

}
