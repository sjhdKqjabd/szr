package com.pandahis.dashboard.modules.system.dal.dataobject.dev;


import com.pandahis.dashboard.common.pojo.PageParam;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@TableName("teacourse")
@NoArgsConstructor
@AllArgsConstructor
public class Teacourse extends PageParam {

    //教师姓名
    private String tname;
    //课时数
    private String times;
    //课程名称
    private String coursename;
    @TableId(type = IdType.INPUT)
    private String id;
    //班级名称
    private String classname;
    //任课地点
    private String address;
    //任课人数
    private String number;
    //教师id
    private String tid;
    //是否删除
    private String isDeleted;
}
