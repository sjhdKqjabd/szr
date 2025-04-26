package com.pandahis.dashboard.modules.dev.stastic.dal.dataobject;


import lombok.*;

import java.util.Date;

@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptCountDto {


    private Integer number;
    private String name;
    private Date createTime;
    private String startTime;
    private String endTime;


}
