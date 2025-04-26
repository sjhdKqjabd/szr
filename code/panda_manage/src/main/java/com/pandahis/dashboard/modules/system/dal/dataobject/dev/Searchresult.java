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
@TableName("searchresult")
@NoArgsConstructor
@AllArgsConstructor
public class Searchresult extends PageParam {

    //文件连接
    private String fileurl;
    //类型
    private String type;
    //积分
    private String score;
    //备用字段
    private String ba;
    //科研类别
    @TableField(value = "searchType")
    private String searchType;
    @TableId(type = IdType.INPUT)
    private String id;
    //校验
    private String checked;
    //作者编号  tid
    private String bb;
    private String name;




}
