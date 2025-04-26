package com.pandahis.dashboard.modules.system.convert.permission;

import com.pandahis.dashboard.modules.system.controller.permission.vo.role.*;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysRoleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysRoleConvert {

    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    SysRoleDO convert(SysRoleUpdateReqVO bean);

    SysRoleRespVO convert(SysRoleDO bean);

    SysRoleDO convert(SysRoleCreateReqVO bean);

    List<SysRoleSimpleRespVO> convertList02(List<SysRoleDO> list);

    List<SysRoleExcelVO> convertList03(List<SysRoleDO> list);

}
