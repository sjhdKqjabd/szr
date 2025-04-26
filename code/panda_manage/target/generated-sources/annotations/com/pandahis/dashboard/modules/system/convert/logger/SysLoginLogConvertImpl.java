package com.pandahis.dashboard.modules.system.convert.logger;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog.SysLoginLogCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog.SysLoginLogExcelVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.loginlog.SysLoginLogRespVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.logger.SysLoginLogDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:12+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class SysLoginLogConvertImpl implements SysLoginLogConvert {

    @Override
    public SysLoginLogDO convert(SysLoginLogCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysLoginLogDO sysLoginLogDO = new SysLoginLogDO();

        sysLoginLogDO.setLogType( bean.getLogType() );
        sysLoginLogDO.setTraceId( bean.getTraceId() );
        sysLoginLogDO.setUsername( bean.getUsername() );
        sysLoginLogDO.setResult( bean.getResult() );
        sysLoginLogDO.setUserIp( bean.getUserIp() );
        sysLoginLogDO.setUserAgent( bean.getUserAgent() );

        return sysLoginLogDO;
    }

    @Override
    public PageResult<SysLoginLogRespVO> convertPage(PageResult<SysLoginLogDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<SysLoginLogRespVO> pageResult = new PageResult<SysLoginLogRespVO>();

        pageResult.setList( sysLoginLogDOListToSysLoginLogRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<SysLoginLogExcelVO> convertList(List<SysLoginLogDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysLoginLogExcelVO> list1 = new ArrayList<SysLoginLogExcelVO>( list.size() );
        for ( SysLoginLogDO sysLoginLogDO : list ) {
            list1.add( sysLoginLogDOToSysLoginLogExcelVO( sysLoginLogDO ) );
        }

        return list1;
    }

    protected SysLoginLogRespVO sysLoginLogDOToSysLoginLogRespVO(SysLoginLogDO sysLoginLogDO) {
        if ( sysLoginLogDO == null ) {
            return null;
        }

        SysLoginLogRespVO sysLoginLogRespVO = new SysLoginLogRespVO();

        sysLoginLogRespVO.setLogType( sysLoginLogDO.getLogType() );
        sysLoginLogRespVO.setTraceId( sysLoginLogDO.getTraceId() );
        sysLoginLogRespVO.setUsername( sysLoginLogDO.getUsername() );
        sysLoginLogRespVO.setResult( sysLoginLogDO.getResult() );
        sysLoginLogRespVO.setUserIp( sysLoginLogDO.getUserIp() );
        sysLoginLogRespVO.setUserAgent( sysLoginLogDO.getUserAgent() );
        sysLoginLogRespVO.setId( sysLoginLogDO.getId() );
        sysLoginLogRespVO.setCreateTime( sysLoginLogDO.getCreateTime() );

        return sysLoginLogRespVO;
    }

    protected List<SysLoginLogRespVO> sysLoginLogDOListToSysLoginLogRespVOList(List<SysLoginLogDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysLoginLogRespVO> list1 = new ArrayList<SysLoginLogRespVO>( list.size() );
        for ( SysLoginLogDO sysLoginLogDO : list ) {
            list1.add( sysLoginLogDOToSysLoginLogRespVO( sysLoginLogDO ) );
        }

        return list1;
    }

    protected SysLoginLogExcelVO sysLoginLogDOToSysLoginLogExcelVO(SysLoginLogDO sysLoginLogDO) {
        if ( sysLoginLogDO == null ) {
            return null;
        }

        SysLoginLogExcelVO sysLoginLogExcelVO = new SysLoginLogExcelVO();

        sysLoginLogExcelVO.setId( sysLoginLogDO.getId() );
        sysLoginLogExcelVO.setUsername( sysLoginLogDO.getUsername() );
        sysLoginLogExcelVO.setResult( sysLoginLogDO.getResult() );
        sysLoginLogExcelVO.setUserIp( sysLoginLogDO.getUserIp() );
        sysLoginLogExcelVO.setUserAgent( sysLoginLogDO.getUserAgent() );
        sysLoginLogExcelVO.setCreateTime( sysLoginLogDO.getCreateTime() );

        return sysLoginLogExcelVO;
    }
}
