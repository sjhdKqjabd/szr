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
@TableName("compinfo")
@NoArgsConstructor
@AllArgsConstructor
public class Compinfo extends PageParam {
    /**
     * 类型
     */
    private String type;
    //教师编号
    @TableField(value = "tId")
    private Long tId;
    @TableId(type = IdType.INPUT)
    private String id;
    //参赛人数
    @TableField(value = "cNum")
    private Integer cNum;
    //赛事名称
    private String name;
    @TableField(exist = false)
    private String joinName;
    @TableField(value = "jointype")
    private String joinType;
    private String grade;
    private String score;
    private String checked;
    private String isDeleted;
}
