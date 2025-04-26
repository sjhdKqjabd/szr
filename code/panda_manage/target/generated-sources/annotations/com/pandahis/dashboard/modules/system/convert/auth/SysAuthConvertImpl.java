package com.pandahis.dashboard.modules.system.convert.auth;

import com.pandahis.dashboard.framework.security.core.LoginUser;
import com.pandahis.dashboard.modules.system.controller.auth.vo.auth.SysAuthMenuRespVO;
import com.pandahis.dashboard.modules.system.controller.auth.vo.auth.SysAuthMenuRespVO.SysAuthMenuRespVOBuilder;
import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdatePasswordReqVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdateReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysMenuDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:13+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class SysAuthConvertImpl implements SysAuthConvert {

    @Override
    public LoginUser convert(SysUserDO bean) {
        if ( bean == null ) {
            return null;
        }

        LoginUser loginUser = new LoginUser();

        loginUser.setId( bean.getId() );
        loginUser.setDeptId( bean.getDeptId() );
        loginUser.setUsername( bean.getUsername() );
        loginUser.setPassword( bean.getPassword() );
        loginUser.setStatus( bean.getStatus() );

        return loginUser;
    }

    @Override
    public SysAuthMenuRespVO convertTreeNode(SysMenuDO menu) {
        if ( menu == null ) {
            return null;
        }

        SysAuthMenuRespVOBuilder sysAuthMenuRespVO = SysAuthMenuRespVO.builder();

        sysAuthMenuRespVO.id( menu.getId() );
        sysAuthMenuRespVO.parentId( menu.getParentId() );
        sysAuthMenuRespVO.name( menu.getName() );
        sysAuthMenuRespVO.path( menu.getPath() );
        sysAuthMenuRespVO.component( menu.getComponent() );
        sysAuthMenuRespVO.icon( menu.getIcon() );

        return sysAuthMenuRespVO.build();
    }

    @Override
    public LoginUser convert(SysUserProfileUpdateReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        LoginUser loginUser = new LoginUser();

        return loginUser;
    }

    @Override
    public LoginUser convert(SysUserProfileUpdatePasswordReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        LoginUser loginUser = new LoginUser();

        return loginUser;
    }
}
