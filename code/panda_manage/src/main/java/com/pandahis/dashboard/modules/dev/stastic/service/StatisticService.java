package com.pandahis.dashboard.modules.dev.stastic.service;

import com.pandahis.dashboard.modules.dev.stastic.dal.dataobject.DeptCountDto;

import java.util.List;

public interface StatisticService {


    List<DeptCountDto> getDeptCounts(DeptCountDto deptCountDto);

}
