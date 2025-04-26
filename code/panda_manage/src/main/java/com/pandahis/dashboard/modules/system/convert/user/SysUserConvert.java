package com.pandahis.dashboard.modules.system.convert.user;

import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileRespVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdatePasswordReqVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdateReqVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.user.*;
import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysDeptDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysPostDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysRoleDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SysUserConvert {

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUserPageItemRespVO convert(SysUserDO bean);

    SysUserPageItemRespVO.Dept convert(SysDeptDO bean);

    SysUserDO convert(SysUserCreateReqVO bean);

    SysUserDO convert(SysUserUpdateReqVO bean);

    SysUserExcelVO convert02(SysUserDO bean);

    SysUserDO convert(SysUserImportExcelVO bean);

    SysUserProfileRespVO convert03(SysUserDO bean);

    List<SysUserProfileRespVO.Role> convertList(List<SysRoleDO> list);

    SysUserProfileRespVO.Dept convert02(SysDeptDO bean);

    SysUserDO convert(SysUserProfileUpdateReqVO bean);

    SysUserDO convert(SysUserProfileUpdatePasswordReqVO bean);

    List<SysUserProfileRespVO.Post> convertList02(List<SysPostDO> list);

}
