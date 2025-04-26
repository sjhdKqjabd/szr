package com.pandahis.dashboard.modules.dev.stastic.dal.mysql;


import com.pandahis.dashboard.modules.dev.stastic.dal.dataobject.DeptCountDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticMapper {


    List<DeptCountDto> getDeptCounts(DeptCountDto deptCountDto);

}
