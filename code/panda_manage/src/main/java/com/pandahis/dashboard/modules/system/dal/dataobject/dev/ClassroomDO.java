package com.pandahis.dashboard.modules.system.dal.dataobject.dev;

import com.pandahis.dashboard.common.pojo.PageParam;
import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;


@TableName("classroom")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomDO extends PageParam {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 楼宇名称

     */
    private String bname;
    /**
     * 教室编号
     */
    private String rname;


    @TableField(exist = false)
    private List children;


    @TableField(exist = false)
    private String value=this.bname;

    @TableField(exist = false)
    private String label=this.bname;




}
