package com.pandahis.dashboard.modules.dev.stastic.service.impl;

import com.pandahis.dashboard.modules.dev.stastic.dal.dataobject.DeptCountDto;
import com.pandahis.dashboard.modules.dev.stastic.dal.mysql.StatisticMapper;
import com.pandahis.dashboard.modules.dev.stastic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    StatisticMapper statisticMapper;


    @Override
    public List<DeptCountDto> getDeptCounts(DeptCountDto deptCountDto) {
        return statisticMapper.getDeptCounts(deptCountDto);
    }
}
