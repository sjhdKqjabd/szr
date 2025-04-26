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
@TableName("classinfo")
@NoArgsConstructor
@AllArgsConstructor
public class Classinfo extends PageParam {

    //id
    @TableId(type = IdType.INPUT)
    private String id;
    //人数
    private Integer numbers;
    //班级名称
    private String name;
    //教师编号
    @TableField(value = "tId")
    private Long tId;
    //是否删除
    private  String isDeleted;



}
