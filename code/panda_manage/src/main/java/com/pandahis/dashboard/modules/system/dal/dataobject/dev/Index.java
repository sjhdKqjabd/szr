package com.pandahis.dashboard.modules.system.dal.dataobject.dev;


import com.pandahis.dashboard.common.pojo.PageParam;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@TableName("indexinfo")
@NoArgsConstructor
@AllArgsConstructor
public class Index  extends PageParam {
    @TableId(type = IdType.INPUT)
    private String id;
    private String name;
    private  Double weight;
    private String score;


}
