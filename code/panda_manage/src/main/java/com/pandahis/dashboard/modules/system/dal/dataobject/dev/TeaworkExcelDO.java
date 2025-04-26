package com.pandahis.dashboard.modules.system.dal.dataobject.dev;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;
@Data
public class TeaworkExcelDO {


    /**
     *
     */
    @ExcelProperty("主键id")
    private Integer id;
    /**
     * 项目名称
     */
    @ExcelProperty("项目名称")
    private String title;
    /**
     * 项目类型
     */
    @ExcelProperty("项目类型")
    private String ptype;
    /**
     * 教研类型
     */
    @ExcelProperty("教研类型")
    private String type;

    /**
     * 教研计分
     */
    @ExcelProperty("教研计分")
    private String score;


    @ExcelProperty("用户")
    private String users;
}
