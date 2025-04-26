package com.pandahis.dashboard.modules.system.dal.dataobject.dev;

import com.pandahis.dashboard.common.pojo.PageParam;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@TableName("teawork")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeaworkDO extends PageParam {

    @TableId
    private Integer id;
    /**
     * 项目名称
     */
    private String title;
    /**
     * 项目类型
     */
    private String ptype;
    /**
     * 教研类型
     */
    private String type;

    /**
     * 教研计分
     */
    private String score;

    private String checked;
    private String tid;
    private String users;
    @TableField(exist = false)
    private List<String> userArray;
    //是否删除
    private String isDeleted;

}
