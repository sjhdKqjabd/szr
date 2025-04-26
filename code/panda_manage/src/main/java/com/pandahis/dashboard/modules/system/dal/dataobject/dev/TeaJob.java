package com.pandahis.dashboard.modules.system.dal.dataobject.dev;


import com.pandahis.dashboard.common.pojo.PageParam;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("teajob")
@NoArgsConstructor
@AllArgsConstructor
public class TeaJob  extends PageParam {


    private Integer id;
    private String title;
    private Integer total;
    @TableField(exist = false)
    private Double percentage; //当前已工作量
    @TableField(value = "tId")
    private Long tId; //教师id
    //是否删除
    private String isDeleted;
}
