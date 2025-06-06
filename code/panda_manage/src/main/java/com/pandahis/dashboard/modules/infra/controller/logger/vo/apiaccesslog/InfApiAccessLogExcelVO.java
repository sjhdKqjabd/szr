package com.pandahis.dashboard.modules.infra.controller.logger.vo.apiaccesslog;

import com.pandahis.dashboard.framework.excel.core.annotations.DictFormat;
import com.pandahis.dashboard.framework.excel.core.convert.DictConvert;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

import static com.pandahis.dashboard.modules.system.enums.dict.SysDictTypeEnum.USER_TYPE;

/**
 * API 访问日志 Excel VO
 *
 * @author 源码乐园
 */
@Data
public class InfApiAccessLogExcelVO {

    @ExcelProperty("日志主键")
    private Long id;

    @ExcelProperty("链路追踪编号")
    private String traceId;

    @ExcelProperty("用户编号")
    private Long userId;

    @ExcelProperty(value = "用户类型", converter = DictConvert.class)
    @DictFormat(USER_TYPE)
    private Integer userType;

    @ExcelProperty("应用名")
    private String applicationName;

    @ExcelProperty("请求方法名")
    private String requestMethod;

    @ExcelProperty("请求地址")
    private String requestUrl;

    @ExcelProperty("请求参数")
    private String requestParams;

    @ExcelProperty("用户 IP")
    private String userIp;

    @ExcelProperty("浏览器 UA")
    private String userAgent;

    @ExcelProperty("开始请求时间")
    private Date beginTime;

    @ExcelProperty("结束请求时间")
    private Date endTime;

    @ExcelProperty("执行时长")
    private Integer duration;

    @ExcelProperty("结果码")
    private Integer resultCode;

    @ExcelProperty("结果提示")
    private String resultMsg;

}
