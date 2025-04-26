package com.pandahis.dashboard.modules.system.dal.dataobject.dev;


import com.pandahis.dashboard.common.pojo.PageParam;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import lombok.AllArgsConstructor;


@Data
@TableName("commentinfo")
@NoArgsConstructor
@AllArgsConstructor
public class Commentinfo extends PageParam {

    //评论人名字
    private String cname;
    //内容
    private String content;
    //评论人id
    private String cnameid;
    @TableId(type = IdType.INPUT)
    private String id;
    //中间id
    private String tid;
    //时间
    private String ctime;

    private String pid;

    @TableField(exist = false)
    List<Commentinfo> children;


}
