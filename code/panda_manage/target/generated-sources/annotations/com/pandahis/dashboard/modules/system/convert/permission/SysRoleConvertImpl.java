package com.pandahis.dashboard.modules.system.convert.permission;

import com.pandahis.dashboard.modules.system.controller.permission.vo.role.SysRoleCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.role.SysRoleExcelVO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.role.SysRoleRespVO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.role.SysRoleSimpleRespVO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.role.SysRoleUpdateReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysRoleDO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:12+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class SysRoleConvertImpl implements SysRoleConvert {

    @Override
    public SysRoleDO convert(SysRoleUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysRoleDO sysRoleDO = new SysRoleDO();

        sysRoleDO.setId( bean.getId() );
        sysRoleDO.setName( bean.getName() );
        sysRoleDO.setCode( bean.getCode() );
        sysRoleDO.setSort( bean.getSort() );
        sysRoleDO.setType( bean.getType() );
        sysRoleDO.setRemark( bean.getRemark() );

        return sysRoleDO;
    }

    @Override
    public SysRoleRespVO convert(SysRoleDO bean) {
        if ( bean == null ) {
            return null;
        }

        SysRoleRespVO sysRoleRespVO = new SysRoleRespVO();

        sysRoleRespVO.setName( bean.getName() );
        sysRoleRespVO.setCode( bean.getCode() );
        sysRoleRespVO.setSort( bean.getSort() );
        sysRoleRespVO.setRemark( bean.getRemark() );
        sysRoleRespVO.setId( bean.getId() );
        sysRoleRespVO.setDataScope( bean.getDataScope() );
        Set<Long> set = bean.getDataScopeDeptIds();
        if ( set != null ) {
            sysRoleRespVO.setDataScopeDeptIds( new HashSet<Long>( set ) );
        }
        sysRoleRespVO.setStatus( bean.getStatus() );
        sysRoleRespVO.setType( bean.getType() );
        sysRoleRespVO.setCreateTime( bean.getCreateTime() );

        return sysRoleRespVO;
    }

    @Override
    public SysRoleDO convert(SysRoleCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysRoleDO sysRoleDO = new SysRoleDO();

        sysRoleDO.setName( bean.getName() );
        sysRoleDO.setCode( bean.getCode() );
        sysRoleDO.setSort( bean.getSort() );
        sysRoleDO.setType( bean.getType() );
        sysRoleDO.setRemark( bean.getRemark() );

        return sysRoleDO;
    }

    @Override
    public List<SysRoleSimpleRespVO> convertList02(List<SysRoleDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysRoleSimpleRespVO> list1 = new ArrayList<SysRoleSimpleRespVO>( list.size() );
        for ( SysRoleDO sysRoleDO : list ) {
            list1.add( sysRoleDOToSysRoleSimpleRespVO( sysRoleDO ) );
        }

        return list1;
    }

    @Override
    public List<SysRoleExcelVO> convertList03(List<SysRoleDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysRoleExcelVO> list1 = new ArrayList<SysRoleExcelVO>( list.size() );
        for ( SysRoleDO sysRoleDO : list ) {
            list1.add( sysRoleDOToSysRoleExcelVO( sysRoleDO ) );
        }

        return list1;
    }

    protected SysRoleSimpleRespVO sysRoleDOToSysRoleSimpleRespVO(SysRoleDO sysRoleDO) {
        if ( sysRoleDO == null ) {
            return null;
        }

        SysRoleSimpleRespVO sysRoleSimpleRespVO = new SysRoleSimpleRespVO();

        sysRoleSimpleRespVO.setId( sysRoleDO.getId() );
        sysRoleSimpleRespVO.setName( sysRoleDO.getName() );

        return sysRoleSimpleRespVO;
    }

    protected SysRoleExcelVO sysRoleDOToSysRoleExcelVO(SysRoleDO sysRoleDO) {
        if ( sysRoleDO == null ) {
            return null;
        }

        SysRoleExcelVO sysRoleExcelVO = new SysRoleExcelVO();

        sysRoleExcelVO.setId( sysRoleDO.getId() );
        sysRoleExcelVO.setName( sysRoleDO.getName() );
        sysRoleExcelVO.setCode( sysRoleDO.getCode() );
        sysRoleExcelVO.setSort( sysRoleDO.getSort() );
        sysRoleExcelVO.setDataScope( sysRoleDO.getDataScope() );
        if ( sysRoleDO.getStatus() != null ) {
            sysRoleExcelVO.setStatus( String.valueOf( sysRoleDO.getStatus() ) );
        }

        return sysRoleExcelVO;
    }
}
