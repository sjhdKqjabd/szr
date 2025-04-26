package com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 就诊 Excel VO
 *
 * @author 源码乐园
 */
@Data
public class PandaPatRecordExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("诊断结果")
    private String result;

    @ExcelProperty("病情描述")
    private String descInfo;

    @ExcelProperty("接诊医生名")
    private String docName;

    @ExcelProperty("挂号科室")
    private String deptName;

    @ExcelProperty("病人名称")
    private String petName;

}
