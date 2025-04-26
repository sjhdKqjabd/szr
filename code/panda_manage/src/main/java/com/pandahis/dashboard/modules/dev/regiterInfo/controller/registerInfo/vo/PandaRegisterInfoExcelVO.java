package com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 挂号表 Excel VO
 *
 * @author 源码乐园
 */
@Data
public class PandaRegisterInfoExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("就诊人")
    private String petName;

    @ExcelProperty("就诊人Id")
    private String petId;

    @ExcelProperty("科室名")
    private String deptName;

    @ExcelProperty("医生名")
    private String docName;

    @ExcelProperty("应缴金额")
    private String needMoney;

    @ExcelProperty("交款金额")
    private String chargeMoney;

    @ExcelProperty("找零")
    private String changeMoney;

    //开始时间
    private String beginDate;

    //结束时间
    private String endDate;

}
