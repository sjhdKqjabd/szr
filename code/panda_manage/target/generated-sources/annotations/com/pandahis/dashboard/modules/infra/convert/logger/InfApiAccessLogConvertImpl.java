package com.pandahis.dashboard.modules.infra.convert.logger;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.logger.apilog.core.service.dto.ApiAccessLogCreateDTO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogExcelVO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apiaccesslog.InfApiAccessLogRespVO;
import com.pandahis.dashboard.modules.infra.dal.dataobject.logger.InfApiAccessLogDO;
import com.pandahis.dashboard.modules.infra.dal.dataobject.logger.InfApiAccessLogDO.InfApiAccessLogDOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:13+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class InfApiAccessLogConvertImpl implements InfApiAccessLogConvert {

    @Override
    public InfApiAccessLogDO convert(ApiAccessLogCreateDTO bean) {
        if ( bean == null ) {
            return null;
        }

        InfApiAccessLogDOBuilder infApiAccessLogDO = InfApiAccessLogDO.builder();

        infApiAccessLogDO.traceId( bean.getTraceId() );
        infApiAccessLogDO.userId( bean.getUserId() );
        infApiAccessLogDO.userType( bean.getUserType() );
        infApiAccessLogDO.applicationName( bean.getApplicationName() );
        infApiAccessLogDO.requestMethod( bean.getRequestMethod() );
        infApiAccessLogDO.requestUrl( bean.getRequestUrl() );
        infApiAccessLogDO.requestParams( bean.getRequestParams() );
        infApiAccessLogDO.userIp( bean.getUserIp() );
        infApiAccessLogDO.userAgent( bean.getUserAgent() );
        infApiAccessLogDO.beginTime( bean.getBeginTime() );
        infApiAccessLogDO.endTime( bean.getEndTime() );
        infApiAccessLogDO.duration( bean.getDuration() );
        infApiAccessLogDO.resultCode( bean.getResultCode() );
        infApiAccessLogDO.resultMsg( bean.getResultMsg() );

        return infApiAccessLogDO.build();
    }

    @Override
    public InfApiAccessLogRespVO convert(InfApiAccessLogDO bean) {
        if ( bean == null ) {
            return null;
        }

        InfApiAccessLogRespVO infApiAccessLogRespVO = new InfApiAccessLogRespVO();

        infApiAccessLogRespVO.setTraceId( bean.getTraceId() );
        infApiAccessLogRespVO.setUserId( bean.getUserId() );
        infApiAccessLogRespVO.setUserType( bean.getUserType() );
        infApiAccessLogRespVO.setApplicationName( bean.getApplicationName() );
        infApiAccessLogRespVO.setRequestMethod( bean.getRequestMethod() );
        infApiAccessLogRespVO.setRequestUrl( bean.getRequestUrl() );
        infApiAccessLogRespVO.setRequestParams( bean.getRequestParams() );
        infApiAccessLogRespVO.setUserIp( bean.getUserIp() );
        infApiAccessLogRespVO.setUserAgent( bean.getUserAgent() );
        infApiAccessLogRespVO.setBeginTime( bean.getBeginTime() );
        infApiAccessLogRespVO.setEndTime( bean.getEndTime() );
        infApiAccessLogRespVO.setDuration( bean.getDuration() );
        infApiAccessLogRespVO.setResultCode( bean.getResultCode() );
        infApiAccessLogRespVO.setResultMsg( bean.getResultMsg() );
        if ( bean.getId() != null ) {
            infApiAccessLogRespVO.setId( bean.getId().longValue() );
        }
        infApiAccessLogRespVO.setCreateTime( bean.getCreateTime() );

        return infApiAccessLogRespVO;
    }

    @Override
    public List<InfApiAccessLogRespVO> convertList(List<InfApiAccessLogDO> list) {
        if ( list == null ) {
            return null;
        }

        List<InfApiAccessLogRespVO> list1 = new ArrayList<InfApiAccessLogRespVO>( list.size() );
        for ( InfApiAccessLogDO infApiAccessLogDO : list ) {
            list1.add( convert( infApiAccessLogDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<InfApiAccessLogRespVO> convertPage(PageResult<InfApiAccessLogDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<InfApiAccessLogRespVO> pageResult = new PageResult<InfApiAccessLogRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<InfApiAccessLogExcelVO> convertList02(List<InfApiAccessLogDO> list) {
        if ( list == null ) {
            return null;
        }

        List<InfApiAccessLogExcelVO> list1 = new ArrayList<InfApiAccessLogExcelVO>( list.size() );
        for ( InfApiAccessLogDO infApiAccessLogDO : list ) {
            list1.add( infApiAccessLogDOToInfApiAccessLogExcelVO( infApiAccessLogDO ) );
        }

        return list1;
    }

    protected InfApiAccessLogExcelVO infApiAccessLogDOToInfApiAccessLogExcelVO(InfApiAccessLogDO infApiAccessLogDO) {
        if ( infApiAccessLogDO == null ) {
            return null;
        }

        InfApiAccessLogExcelVO infApiAccessLogExcelVO = new InfApiAccessLogExcelVO();

        if ( infApiAccessLogDO.getId() != null ) {
            infApiAccessLogExcelVO.setId( infApiAccessLogDO.getId().longValue() );
        }
        infApiAccessLogExcelVO.setTraceId( infApiAccessLogDO.getTraceId() );
        infApiAccessLogExcelVO.setUserId( infApiAccessLogDO.getUserId() );
        infApiAccessLogExcelVO.setUserType( infApiAccessLogDO.getUserType() );
        infApiAccessLogExcelVO.setApplicationName( infApiAccessLogDO.getApplicationName() );
        infApiAccessLogExcelVO.setRequestMethod( infApiAccessLogDO.getRequestMethod() );
        infApiAccessLogExcelVO.setRequestUrl( infApiAccessLogDO.getRequestUrl() );
        infApiAccessLogExcelVO.setRequestParams( infApiAccessLogDO.getRequestParams() );
        infApiAccessLogExcelVO.setUserIp( infApiAccessLogDO.getUserIp() );
        infApiAccessLogExcelVO.setUserAgent( infApiAccessLogDO.getUserAgent() );
        infApiAccessLogExcelVO.setBeginTime( infApiAccessLogDO.getBeginTime() );
        infApiAccessLogExcelVO.setEndTime( infApiAccessLogDO.getEndTime() );
        infApiAccessLogExcelVO.setDuration( infApiAccessLogDO.getDuration() );
        infApiAccessLogExcelVO.setResultCode( infApiAccessLogDO.getResultCode() );
        infApiAccessLogExcelVO.setResultMsg( infApiAccessLogDO.getResultMsg() );

        return infApiAccessLogExcelVO;
    }
}
