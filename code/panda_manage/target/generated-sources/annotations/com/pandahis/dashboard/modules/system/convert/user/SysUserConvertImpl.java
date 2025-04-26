package com.pandahis.dashboard.modules.system.convert.user;

import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileRespVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileRespVO.Post;
import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileRespVO.Role;
import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdatePasswordReqVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdateReqVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.user.SysUserCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.user.SysUserExcelVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.user.SysUserImportExcelVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.user.SysUserPageItemRespVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.user.SysUserPageItemRespVO.Dept;
import com.pandahis.dashboard.modules.system.controller.user.vo.user.SysUserUpdateReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysDeptDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysPostDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysRoleDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO.SysUserDOBuilder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:13+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class SysUserConvertImpl implements SysUserConvert {

    @Override
    public SysUserPageItemRespVO convert(SysUserDO bean) {
        if ( bean == null ) {
            return null;
        }

        SysUserPageItemRespVO sysUserPageItemRespVO = new SysUserPageItemRespVO();

        sysUserPageItemRespVO.setUsername( bean.getUsername() );
        sysUserPageItemRespVO.setNickname( bean.getNickname() );
        sysUserPageItemRespVO.setRemark( bean.getRemark() );
        sysUserPageItemRespVO.setDeptId( bean.getDeptId() );
        Set<Long> set = bean.getPostIds();
        if ( set != null ) {
            sysUserPageItemRespVO.setPostIds( new HashSet<Long>( set ) );
        }
        sysUserPageItemRespVO.setEmail( bean.getEmail() );
        sysUserPageItemRespVO.setMobile( bean.getMobile() );
        sysUserPageItemRespVO.setSex( bean.getSex() );
        sysUserPageItemRespVO.setAvatar( bean.getAvatar() );
        sysUserPageItemRespVO.setId( bean.getId() );
        sysUserPageItemRespVO.setStatus( bean.getStatus() );
        sysUserPageItemRespVO.setLoginIp( bean.getLoginIp() );
        sysUserPageItemRespVO.setLoginDate( bean.getLoginDate() );
        sysUserPageItemRespVO.setCreateTime( bean.getCreateTime() );

        return sysUserPageItemRespVO;
    }

    @Override
    public Dept convert(SysDeptDO bean) {
        if ( bean == null ) {
            return null;
        }

        Dept dept = new Dept();

        dept.setId( bean.getId() );
        dept.setName( bean.getName() );

        return dept;
    }

    @Override
    public SysUserDO convert(SysUserCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysUserDOBuilder sysUserDO = SysUserDO.builder();

        sysUserDO.username( bean.getUsername() );
        sysUserDO.password( bean.getPassword() );
        sysUserDO.nickname( bean.getNickname() );
        sysUserDO.remark( bean.getRemark() );
        sysUserDO.deptId( bean.getDeptId() );
        Set<Long> set = bean.getPostIds();
        if ( set != null ) {
            sysUserDO.postIds( new HashSet<Long>( set ) );
        }
        sysUserDO.email( bean.getEmail() );
        sysUserDO.mobile( bean.getMobile() );
        sysUserDO.sex( bean.getSex() );
        sysUserDO.avatar( bean.getAvatar() );

        return sysUserDO.build();
    }

    @Override
    public SysUserDO convert(SysUserUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysUserDOBuilder sysUserDO = SysUserDO.builder();

        sysUserDO.id( bean.getId() );
        sysUserDO.username( bean.getUsername() );
        sysUserDO.nickname( bean.getNickname() );
        sysUserDO.remark( bean.getRemark() );
        sysUserDO.deptId( bean.getDeptId() );
        Set<Long> set = bean.getPostIds();
        if ( set != null ) {
            sysUserDO.postIds( new HashSet<Long>( set ) );
        }
        sysUserDO.email( bean.getEmail() );
        sysUserDO.mobile( bean.getMobile() );
        sysUserDO.sex( bean.getSex() );
        sysUserDO.avatar( bean.getAvatar() );

        return sysUserDO.build();
    }

    @Override
    public SysUserExcelVO convert02(SysUserDO bean) {
        if ( bean == null ) {
            return null;
        }

        SysUserExcelVO sysUserExcelVO = new SysUserExcelVO();

        sysUserExcelVO.setId( bean.getId() );
        sysUserExcelVO.setUsername( bean.getUsername() );
        sysUserExcelVO.setNickname( bean.getNickname() );
        sysUserExcelVO.setEmail( bean.getEmail() );
        sysUserExcelVO.setMobile( bean.getMobile() );
        sysUserExcelVO.setSex( bean.getSex() );
        sysUserExcelVO.setStatus( bean.getStatus() );
        sysUserExcelVO.setLoginIp( bean.getLoginIp() );
        sysUserExcelVO.setLoginDate( bean.getLoginDate() );

        return sysUserExcelVO;
    }

    @Override
    public SysUserDO convert(SysUserImportExcelVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysUserDOBuilder sysUserDO = SysUserDO.builder();

        sysUserDO.username( bean.getUsername() );
        sysUserDO.nickname( bean.getNickname() );
        sysUserDO.deptId( bean.getDeptId() );
        sysUserDO.email( bean.getEmail() );
        sysUserDO.mobile( bean.getMobile() );
        sysUserDO.sex( bean.getSex() );
        sysUserDO.status( bean.getStatus() );

        return sysUserDO.build();
    }

    @Override
    public SysUserProfileRespVO convert03(SysUserDO bean) {
        if ( bean == null ) {
            return null;
        }

        SysUserProfileRespVO sysUserProfileRespVO = new SysUserProfileRespVO();

        sysUserProfileRespVO.setUsername( bean.getUsername() );
        sysUserProfileRespVO.setNickname( bean.getNickname() );
        sysUserProfileRespVO.setRemark( bean.getRemark() );
        sysUserProfileRespVO.setDeptId( bean.getDeptId() );
        Set<Long> set = bean.getPostIds();
        if ( set != null ) {
            sysUserProfileRespVO.setPostIds( new HashSet<Long>( set ) );
        }
        sysUserProfileRespVO.setEmail( bean.getEmail() );
        sysUserProfileRespVO.setMobile( bean.getMobile() );
        sysUserProfileRespVO.setSex( bean.getSex() );
        sysUserProfileRespVO.setAvatar( bean.getAvatar() );
        sysUserProfileRespVO.setId( bean.getId() );
        sysUserProfileRespVO.setStatus( bean.getStatus() );
        sysUserProfileRespVO.setLoginIp( bean.getLoginIp() );
        sysUserProfileRespVO.setLoginDate( bean.getLoginDate() );
        sysUserProfileRespVO.setCreateTime( bean.getCreateTime() );

        return sysUserProfileRespVO;
    }

    @Override
    public List<Role> convertList(List<SysRoleDO> list) {
        if ( list == null ) {
            return null;
        }

        List<Role> list1 = new ArrayList<Role>( list.size() );
        for ( SysRoleDO sysRoleDO : list ) {
            list1.add( sysRoleDOToRole( sysRoleDO ) );
        }

        return list1;
    }

    @Override
    public com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileRespVO.Dept convert02(SysDeptDO bean) {
        if ( bean == null ) {
            return null;
        }

        com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileRespVO.Dept dept = new com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileRespVO.Dept();

        dept.setId( bean.getId() );
        dept.setName( bean.getName() );

        return dept;
    }

    @Override
    public SysUserDO convert(SysUserProfileUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysUserDOBuilder sysUserDO = SysUserDO.builder();

        sysUserDO.nickname( bean.getNickname() );
        sysUserDO.email( bean.getEmail() );
        sysUserDO.mobile( bean.getMobile() );
        sysUserDO.sex( bean.getSex() );

        return sysUserDO.build();
    }

    @Override
    public SysUserDO convert(SysUserProfileUpdatePasswordReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysUserDOBuilder sysUserDO = SysUserDO.builder();

        return sysUserDO.build();
    }

    @Override
    public List<Post> convertList02(List<SysPostDO> list) {
        if ( list == null ) {
            return null;
        }

        List<Post> list1 = new ArrayList<Post>( list.size() );
        for ( SysPostDO sysPostDO : list ) {
            list1.add( sysPostDOToPost( sysPostDO ) );
        }

        return list1;
    }

    protected Role sysRoleDOToRole(SysRoleDO sysRoleDO) {
        if ( sysRoleDO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( sysRoleDO.getId() );
        role.setName( sysRoleDO.getName() );

        return role;
    }

    protected Post sysPostDOToPost(SysPostDO sysPostDO) {
        if ( sysPostDO == null ) {
            return null;
        }

        Post post = new Post();

        post.setId( sysPostDO.getId() );
        post.setName( sysPostDO.getName() );

        return post;
    }
}
