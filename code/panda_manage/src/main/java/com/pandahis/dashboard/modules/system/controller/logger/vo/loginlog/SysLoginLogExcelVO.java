package com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog;

import com.pandahis.dashboard.framework.excel.core.annotations.DictFormat;
import com.pandahis.dashboard.framework.excel.core.convert.DictConvert;
import com.pandahis.dashboard.modules.system.enums.dict.SysDictTypeEnum;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 登陆日志 Excel 导出响应 VO
 */
@Data
public class SysLoginLogExcelVO {

    @ExcelProperty("日志主键")
    private Long id;

    @ExcelProperty("用户账号")
    private String username;

    @ExcelProperty(value = "登陆结果", converter = DictConvert.class)
    @DictFormat(SysDictTypeEnum.SYS_LOGIN_RESULT)
    private Integer result;

    @ExcelProperty("登陆 IP")
    private String userIp;

    @ExcelProperty("浏览器 UA")
    private String userAgent;

    @ExcelProperty("登陆时间")
    private Date createTime;

}
