package com.pandahis.dashboard.modules.system.dal.dataobject.dev;


import com.pandahis.dashboard.common.pojo.PageParam;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("teajobdetail")
@NoArgsConstructor
@AllArgsConstructor
public class TeaJobDetail extends PageParam {


    private Integer id;// id
    private Integer pid;// pid
    private String title;//标题
    private String status;//状态

    private Double quantity;//子工作量

    private String isDeleted;//是否删除


}
