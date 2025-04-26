package com.pandahis.dashboard.modules.system.dal.dataobject.dev;

import com.pandahis.dashboard.common.pojo.PageParam;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;
import com.baomidou.mybatisplus.annotation.*;

/**
 *  DO
 *
 * @author
 */
@TableName("teachwork")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeachworkDO extends PageParam {

    /**
     *
     */
    @TableId
    @ExcelProperty("id")
    private Integer id;
    /**
     * 教师id
     */
    private Integer tid;
    /**
     * 课时数
     */
    private Integer ctimes;
    /**
     * 班级名
     */
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
     * 审核
     */
    private String checked;
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
    private Double number;
    /**
     * 工作量
     */
    @ExcelProperty("工作量")
    private Double total;

    private String isDeleted;


}
