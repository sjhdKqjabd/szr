package com.pandahis.dashboard.modules.system.dal.dataobject.dev;


import com.pandahis.dashboard.common.pojo.PageParam;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@TableName("gradesign")
@NoArgsConstructor
@AllArgsConstructor
public class Gradesign extends PageParam {

    //教师id
    @TableField(value = "tId")
    private Long tId;
    @TableId(type = IdType.INPUT)
    private String id;
    //名称
    private String name;
    //用户id
    private String uid;
    //科室名称
    private String cname;

    private String isDeleted;
}
