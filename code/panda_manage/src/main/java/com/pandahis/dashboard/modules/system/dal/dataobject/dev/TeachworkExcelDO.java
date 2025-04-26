package com.pandahis.dashboard.modules.system.dal.dataobject.dev;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

/**
 *  DO
 *
 * @author
 */

@Data

public class TeachworkExcelDO {


    @ExcelProperty("主键id")
    private Integer id;

    /**
     * 课时数
     */
    @ExcelProperty("课时数")
    private Integer ctimes;
    /**
     * 班级名
     */
    @ExcelProperty("班级名")
    private String cname;
    /**
     * 学生数量
     */
    @ExcelProperty("学生数量")
    private Integer snum;
    /**
     * 学年
     */
    @ExcelProperty("学年")
    private String syear;

    /**
     * 课程类型
     */
    @ExcelProperty("课程类型")
    private String ctype;

    /**
     * 课程名称
     */
    @ExcelProperty("课程名称")
    private String coursename;
    /**
     * 系数
     */
    @ExcelProperty("系数")
    private Integer number;
    /**
     * 工作量
     */
    @ExcelProperty("工作量")
    private Double total;

}
