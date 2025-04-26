package com.pandahis.dashboard.modules.system.convert.auth;

import com.pandahis.dashboard.modules.system.dal.dataobject.auth.SysUserSessionDO;
import com.pandahis.dashboard.modules.system.controller.auth.vo.session.SysUserSessionPageItemRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserSessionConvert {

    SysUserSessionConvert INSTANCE = Mappers.getMapper(SysUserSessionConvert.class);

    SysUserSessionPageItemRespVO convert(SysUserSessionDO session);

}
