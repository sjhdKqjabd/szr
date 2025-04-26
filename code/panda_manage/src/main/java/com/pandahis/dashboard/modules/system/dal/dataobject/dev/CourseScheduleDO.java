package com.pandahis.dashboard.modules.system.dal.dataobject.dev;

import com.pandahis.dashboard.common.pojo.PageParam;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;


@TableName("course_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseScheduleDO extends PageParam {

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 教师编号
     */
    @ExcelProperty("教师编号")
    private Integer tId;
    /**
     * 科室编号
     */
    @ExcelProperty("科室编号")
    private Long collegeId;
    /**
     * 时刻时间段
     */
    @ExcelProperty("时刻时间段")
    private String period;
    /**
     * 课程信息
     */
    @ExcelProperty("课程信息")
    private String courseId;
    /**
     * 学期
     */
    @ExcelProperty("学期")
    private String term;
    /**
     * 周次
     */
    @ExcelProperty("周次")
    private String week;
    /**
     * 星期信息
     */
    @ExcelProperty("星期信息")
    private String weekDay;

    @TableField(exist = false)
    private String courseName;

    @TableField(exist = false)
    private String teacherName;

    @TableField(exist = false)
    private String  collegeName;

    @TableField(exist = false)
    private ClassroomDO room;

    @TableField(exist = false)
    private List<CourseScheduleDO>  children;



}
