package com.pandahis.dashboard.modules.system.convert.logger;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.controller.logger.vo.operatelog.SysOperateLogCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.operatelog.SysOperateLogExcelVO;
import com.pandahis.dashboard.modules.system.controller.logger.vo.operatelog.SysOperateLogRespVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.logger.SysOperateLogDO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:13+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class SysOperateLogConvertImpl implements SysOperateLogConvert {

    @Override
    public SysOperateLogDO convert(SysOperateLogCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysOperateLogDO sysOperateLogDO = new SysOperateLogDO();

        sysOperateLogDO.setTraceId( bean.getTraceId() );
        sysOperateLogDO.setUserId( bean.getUserId() );
        sysOperateLogDO.setModule( bean.getModule() );
        sysOperateLogDO.setName( bean.getName() );
        sysOperateLogDO.setType( bean.getType() );
        sysOperateLogDO.setContent( bean.getContent() );
        Map<String, Object> map = bean.getExts();
        if ( map != null ) {
            sysOperateLogDO.setExts( new HashMap<String, Object>( map ) );
        }
        sysOperateLogDO.setRequestMethod( bean.getRequestMethod() );
        sysOperateLogDO.setRequestUrl( bean.getRequestUrl() );
        sysOperateLogDO.setUserIp( bean.getUserIp() );
        sysOperateLogDO.setUserAgent( bean.getUserAgent() );
        sysOperateLogDO.setJavaMethod( bean.getJavaMethod() );
        sysOperateLogDO.setJavaMethodArgs( bean.getJavaMethodArgs() );
        sysOperateLogDO.setStartTime( bean.getStartTime() );
        sysOperateLogDO.setDuration( bean.getDuration() );
        sysOperateLogDO.setResultCode( bean.getResultCode() );
        sysOperateLogDO.setResultMsg( bean.getResultMsg() );
        sysOperateLogDO.setResultData( bean.getResultData() );

        return sysOperateLogDO;
    }

    @Override
    public PageResult<SysOperateLogRespVO> convertPage(PageResult<SysOperateLogDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<SysOperateLogRespVO> pageResult = new PageResult<SysOperateLogRespVO>();

        pageResult.setList( sysOperateLogDOListToSysOperateLogRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public SysOperateLogRespVO convert(SysOperateLogDO bean) {
        if ( bean == null ) {
            return null;
        }

        SysOperateLogRespVO sysOperateLogRespVO = new SysOperateLogRespVO();

        sysOperateLogRespVO.setTraceId( bean.getTraceId() );
        sysOperateLogRespVO.setUserId( bean.getUserId() );
        sysOperateLogRespVO.setModule( bean.getModule() );
        sysOperateLogRespVO.setName( bean.getName() );
        sysOperateLogRespVO.setType( bean.getType() );
        sysOperateLogRespVO.setContent( bean.getContent() );
        Map<String, Object> map = bean.getExts();
        if ( map != null ) {
            sysOperateLogRespVO.setExts( new HashMap<String, Object>( map ) );
        }
        sysOperateLogRespVO.setRequestMethod( bean.getRequestMethod() );
        sysOperateLogRespVO.setRequestUrl( bean.getRequestUrl() );
        sysOperateLogRespVO.setUserIp( bean.getUserIp() );
        sysOperateLogRespVO.setUserAgent( bean.getUserAgent() );
        sysOperateLogRespVO.setJavaMethod( bean.getJavaMethod() );
        sysOperateLogRespVO.setJavaMethodArgs( bean.getJavaMethodArgs() );
        sysOperateLogRespVO.setStartTime( bean.getStartTime() );
        sysOperateLogRespVO.setDuration( bean.getDuration() );
        sysOperateLogRespVO.setResultCode( bean.getResultCode() );
        sysOperateLogRespVO.setResultMsg( bean.getResultMsg() );
        sysOperateLogRespVO.setResultData( bean.getResultData() );
        sysOperateLogRespVO.setId( bean.getId() );

        return sysOperateLogRespVO;
    }

    @Override
    public SysOperateLogExcelVO convert02(SysOperateLogDO bean) {
        if ( bean == null ) {
            return null;
        }

        SysOperateLogExcelVO sysOperateLogExcelVO = new SysOperateLogExcelVO();

        sysOperateLogExcelVO.setId( bean.getId() );
        sysOperateLogExcelVO.setModule( bean.getModule() );
        sysOperateLogExcelVO.setName( bean.getName() );
        if ( bean.getType() != null ) {
            sysOperateLogExcelVO.setType( String.valueOf( bean.getType() ) );
        }
        sysOperateLogExcelVO.setStartTime( bean.getStartTime() );
        sysOperateLogExcelVO.setDuration( bean.getDuration() );

        return sysOperateLogExcelVO;
    }

    protected List<SysOperateLogRespVO> sysOperateLogDOListToSysOperateLogRespVOList(List<SysOperateLogDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysOperateLogRespVO> list1 = new ArrayList<SysOperateLogRespVO>( list.size() );
        for ( SysOperateLogDO sysOperateLogDO : list ) {
            list1.add( convert( sysOperateLogDO ) );
        }

        return list1;
    }
}
