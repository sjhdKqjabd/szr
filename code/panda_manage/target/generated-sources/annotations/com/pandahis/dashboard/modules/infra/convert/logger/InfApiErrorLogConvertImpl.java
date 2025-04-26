package com.pandahis.dashboard.modules.infra.convert.logger;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.logger.apilog.core.service.dto.ApiErrorLogCreateDTO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogExcelVO;
import com.pandahis.dashboard.modules.infra.controller.logger.vo.apierrorlog.InfApiErrorLogRespVO;
import com.pandahis.dashboard.modules.infra.dal.dataobject.logger.InfApiErrorLogDO;
import com.pandahis.dashboard.modules.infra.dal.dataobject.logger.InfApiErrorLogDO.InfApiErrorLogDOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:12+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class InfApiErrorLogConvertImpl implements InfApiErrorLogConvert {

    @Override
    public InfApiErrorLogDO convert(ApiErrorLogCreateDTO bean) {
        if ( bean == null ) {
            return null;
        }

        InfApiErrorLogDOBuilder infApiErrorLogDO = InfApiErrorLogDO.builder();

        infApiErrorLogDO.userId( bean.getUserId() );
        infApiErrorLogDO.traceId( bean.getTraceId() );
        infApiErrorLogDO.userType( bean.getUserType() );
        infApiErrorLogDO.applicationName( bean.getApplicationName() );
        infApiErrorLogDO.requestMethod( bean.getRequestMethod() );
        infApiErrorLogDO.requestUrl( bean.getRequestUrl() );
        infApiErrorLogDO.requestParams( bean.getRequestParams() );
        infApiErrorLogDO.userIp( bean.getUserIp() );
        infApiErrorLogDO.userAgent( bean.getUserAgent() );
        infApiErrorLogDO.exceptionTime( bean.getExceptionTime() );
        infApiErrorLogDO.exceptionName( bean.getExceptionName() );
        infApiErrorLogDO.exceptionMessage( bean.getExceptionMessage() );
        infApiErrorLogDO.exceptionRootCauseMessage( bean.getExceptionRootCauseMessage() );
        infApiErrorLogDO.exceptionStackTrace( bean.getExceptionStackTrace() );
        infApiErrorLogDO.exceptionClassName( bean.getExceptionClassName() );
        infApiErrorLogDO.exceptionFileName( bean.getExceptionFileName() );
        infApiErrorLogDO.exceptionMethodName( bean.getExceptionMethodName() );
        infApiErrorLogDO.exceptionLineNumber( bean.getExceptionLineNumber() );

        return infApiErrorLogDO.build();
    }

    @Override
    public InfApiErrorLogRespVO convert(InfApiErrorLogDO bean) {
        if ( bean == null ) {
            return null;
        }

        InfApiErrorLogRespVO infApiErrorLogRespVO = new InfApiErrorLogRespVO();

        infApiErrorLogRespVO.setTraceId( bean.getTraceId() );
        if ( bean.getUserId() != null ) {
            infApiErrorLogRespVO.setUserId( bean.getUserId().intValue() );
        }
        infApiErrorLogRespVO.setUserType( bean.getUserType() );
        infApiErrorLogRespVO.setApplicationName( bean.getApplicationName() );
        infApiErrorLogRespVO.setRequestMethod( bean.getRequestMethod() );
        infApiErrorLogRespVO.setRequestUrl( bean.getRequestUrl() );
        infApiErrorLogRespVO.setRequestParams( bean.getRequestParams() );
        infApiErrorLogRespVO.setUserIp( bean.getUserIp() );
        infApiErrorLogRespVO.setUserAgent( bean.getUserAgent() );
        infApiErrorLogRespVO.setExceptionTime( bean.getExceptionTime() );
        infApiErrorLogRespVO.setExceptionName( bean.getExceptionName() );
        infApiErrorLogRespVO.setExceptionMessage( bean.getExceptionMessage() );
        infApiErrorLogRespVO.setExceptionRootCauseMessage( bean.getExceptionRootCauseMessage() );
        infApiErrorLogRespVO.setExceptionStackTrace( bean.getExceptionStackTrace() );
        infApiErrorLogRespVO.setExceptionClassName( bean.getExceptionClassName() );
        infApiErrorLogRespVO.setExceptionFileName( bean.getExceptionFileName() );
        infApiErrorLogRespVO.setExceptionMethodName( bean.getExceptionMethodName() );
        infApiErrorLogRespVO.setExceptionLineNumber( bean.getExceptionLineNumber() );
        infApiErrorLogRespVO.setProcessStatus( bean.getProcessStatus() );
        if ( bean.getId() != null ) {
            infApiErrorLogRespVO.setId( bean.getId().intValue() );
        }
        infApiErrorLogRespVO.setCreateTime( bean.getCreateTime() );
        infApiErrorLogRespVO.setProcessTime( bean.getProcessTime() );
        if ( bean.getProcessUserId() != null ) {
            infApiErrorLogRespVO.setProcessUserId( bean.getProcessUserId().intValue() );
        }

        return infApiErrorLogRespVO;
    }

    @Override
    public PageResult<InfApiErrorLogRespVO> convertPage(PageResult<InfApiErrorLogDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<InfApiErrorLogRespVO> pageResult = new PageResult<InfApiErrorLogRespVO>();

        pageResult.setList( infApiErrorLogDOListToInfApiErrorLogRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<InfApiErrorLogExcelVO> convertList02(List<InfApiErrorLogDO> list) {
        if ( list == null ) {
            return null;
        }

        List<InfApiErrorLogExcelVO> list1 = new ArrayList<InfApiErrorLogExcelVO>( list.size() );
        for ( InfApiErrorLogDO infApiErrorLogDO : list ) {
            list1.add( infApiErrorLogDOToInfApiErrorLogExcelVO( infApiErrorLogDO ) );
        }

        return list1;
    }

    protected List<InfApiErrorLogRespVO> infApiErrorLogDOListToInfApiErrorLogRespVOList(List<InfApiErrorLogDO> list) {
        if ( list == null ) {
            return null;
        }

        List<InfApiErrorLogRespVO> list1 = new ArrayList<InfApiErrorLogRespVO>( list.size() );
        for ( InfApiErrorLogDO infApiErrorLogDO : list ) {
            list1.add( convert( infApiErrorLogDO ) );
        }

        return list1;
    }

    protected InfApiErrorLogExcelVO infApiErrorLogDOToInfApiErrorLogExcelVO(InfApiErrorLogDO infApiErrorLogDO) {
        if ( infApiErrorLogDO == null ) {
            return null;
        }

        InfApiErrorLogExcelVO infApiErrorLogExcelVO = new InfApiErrorLogExcelVO();

        if ( infApiErrorLogDO.getId() != null ) {
            infApiErrorLogExcelVO.setId( infApiErrorLogDO.getId().intValue() );
        }
        infApiErrorLogExcelVO.setTraceId( infApiErrorLogDO.getTraceId() );
        if ( infApiErrorLogDO.getUserId() != null ) {
            infApiErrorLogExcelVO.setUserId( infApiErrorLogDO.getUserId().intValue() );
        }
        infApiErrorLogExcelVO.setUserType( infApiErrorLogDO.getUserType() );
        infApiErrorLogExcelVO.setApplicationName( infApiErrorLogDO.getApplicationName() );
        infApiErrorLogExcelVO.setRequestMethod( infApiErrorLogDO.getRequestMethod() );
        infApiErrorLogExcelVO.setRequestUrl( infApiErrorLogDO.getRequestUrl() );
        infApiErrorLogExcelVO.setRequestParams( infApiErrorLogDO.getRequestParams() );
        infApiErrorLogExcelVO.setUserIp( infApiErrorLogDO.getUserIp() );
        infApiErrorLogExcelVO.setUserAgent( infApiErrorLogDO.getUserAgent() );
        infApiErrorLogExcelVO.setExceptionTime( infApiErrorLogDO.getExceptionTime() );
        infApiErrorLogExcelVO.setExceptionName( infApiErrorLogDO.getExceptionName() );
        infApiErrorLogExcelVO.setExceptionMessage( infApiErrorLogDO.getExceptionMessage() );
        infApiErrorLogExcelVO.setExceptionRootCauseMessage( infApiErrorLogDO.getExceptionRootCauseMessage() );
        infApiErrorLogExcelVO.setExceptionStackTrace( infApiErrorLogDO.getExceptionStackTrace() );
        infApiErrorLogExcelVO.setExceptionClassName( infApiErrorLogDO.getExceptionClassName() );
        infApiErrorLogExcelVO.setExceptionFileName( infApiErrorLogDO.getExceptionFileName() );
        infApiErrorLogExcelVO.setExceptionMethodName( infApiErrorLogDO.getExceptionMethodName() );
        infApiErrorLogExcelVO.setExceptionLineNumber( infApiErrorLogDO.getExceptionLineNumber() );
        infApiErrorLogExcelVO.setCreateTime( infApiErrorLogDO.getCreateTime() );
        infApiErrorLogExcelVO.setProcessStatus( infApiErrorLogDO.getProcessStatus() );
        infApiErrorLogExcelVO.setProcessTime( infApiErrorLogDO.getProcessTime() );
        if ( infApiErrorLogDO.getProcessUserId() != null ) {
            infApiErrorLogExcelVO.setProcessUserId( infApiErrorLogDO.getProcessUserId().intValue() );
        }

        return infApiErrorLogExcelVO;
    }
}
