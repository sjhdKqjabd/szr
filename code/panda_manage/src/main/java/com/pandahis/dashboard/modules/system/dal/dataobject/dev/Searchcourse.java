package com.pandahis.dashboard.modules.system.dal.dataobject.dev;


import com.pandahis.dashboard.common.pojo.PageParam;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@TableName("searchcourse")
@NoArgsConstructor
@AllArgsConstructor
public class Searchcourse extends PageParam {

    @TableId(type = IdType.INPUT)
    private String id;
    //作者编号
    private String authorId;
    //备用字段
    private String bb;
    //结束时间
    private String enddate;
    //编号
    private String no;
    //经费
    private String fee;
    //项目来源
    private String resource;
    //开始时间
    private String startdate;
    //课题名称
    private String name;
    //立项单位
    private String unit;

    private Boolean checked;


}
