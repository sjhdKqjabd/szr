package com.pandahis.dashboard.modules.system.convert.auth;

import com.pandahis.dashboard.modules.system.controller.auth.vo.session.SysUserSessionPageItemRespVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.auth.SysUserSessionDO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:12+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class SysUserSessionConvertImpl implements SysUserSessionConvert {

    @Override
    public SysUserSessionPageItemRespVO convert(SysUserSessionDO session) {
        if ( session == null ) {
            return null;
        }

        SysUserSessionPageItemRespVO sysUserSessionPageItemRespVO = new SysUserSessionPageItemRespVO();

        sysUserSessionPageItemRespVO.setId( session.getId() );
        sysUserSessionPageItemRespVO.setUserIp( session.getUserIp() );
        sysUserSessionPageItemRespVO.setUserAgent( session.getUserAgent() );
        sysUserSessionPageItemRespVO.setCreateTime( session.getCreateTime() );
        sysUserSessionPageItemRespVO.setUsername( session.getUsername() );

        return sysUserSessionPageItemRespVO;
    }
}
