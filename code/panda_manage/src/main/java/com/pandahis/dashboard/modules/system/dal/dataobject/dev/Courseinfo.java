package com.pandahis.dashboard.modules.system.dal.dataobject.dev;


import com.pandahis.dashboard.common.pojo.PageParam;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@TableName("courseinfo")
@NoArgsConstructor
@AllArgsConstructor
public class Courseinfo extends PageParam {

    @TableId(type = IdType.INPUT)
    private String id;
    //任课地点
    private String address;
    //学时
    private String times;

    //名称
    private String name;
    //人数
    private Integer numbers;
    //教师编号
    private Long tid;
    //班级信息
    private String classId;
    @TableField(exist = false)
    private String className;
    //是否删除
    private String isDeleted;

}
