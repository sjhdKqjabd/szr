package com.pandahis.dashboard.modules.system.convert.permission;

import com.pandahis.dashboard.modules.system.controller.permission.vo.menu.SysMenuCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.menu.SysMenuRespVO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.menu.SysMenuSimpleRespVO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.menu.SysMenuUpdateReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysMenuDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:12+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class SysMenuConvertImpl implements SysMenuConvert {

    @Override
    public List<SysMenuRespVO> convertList(List<SysMenuDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysMenuRespVO> list1 = new ArrayList<SysMenuRespVO>( list.size() );
        for ( SysMenuDO sysMenuDO : list ) {
            list1.add( convert( sysMenuDO ) );
        }

        return list1;
    }

    @Override
    public SysMenuDO convert(SysMenuCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysMenuDO sysMenuDO = new SysMenuDO();

        sysMenuDO.setName( bean.getName() );
        sysMenuDO.setPermission( bean.getPermission() );
        sysMenuDO.setType( bean.getType() );
        sysMenuDO.setSort( bean.getSort() );
        sysMenuDO.setParentId( bean.getParentId() );
        sysMenuDO.setPath( bean.getPath() );
        sysMenuDO.setIcon( bean.getIcon() );
        sysMenuDO.setComponent( bean.getComponent() );
        sysMenuDO.setStatus( bean.getStatus() );

        return sysMenuDO;
    }

    @Override
    public SysMenuDO convert(SysMenuUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysMenuDO sysMenuDO = new SysMenuDO();

        sysMenuDO.setId( bean.getId() );
        sysMenuDO.setName( bean.getName() );
        sysMenuDO.setPermission( bean.getPermission() );
        sysMenuDO.setType( bean.getType() );
        sysMenuDO.setSort( bean.getSort() );
        sysMenuDO.setParentId( bean.getParentId() );
        sysMenuDO.setPath( bean.getPath() );
        sysMenuDO.setIcon( bean.getIcon() );
        sysMenuDO.setComponent( bean.getComponent() );
        sysMenuDO.setStatus( bean.getStatus() );

        return sysMenuDO;
    }

    @Override
    public SysMenuRespVO convert(SysMenuDO bean) {
        if ( bean == null ) {
            return null;
        }

        SysMenuRespVO sysMenuRespVO = new SysMenuRespVO();

        sysMenuRespVO.setName( bean.getName() );
        sysMenuRespVO.setPermission( bean.getPermission() );
        sysMenuRespVO.setType( bean.getType() );
        sysMenuRespVO.setSort( bean.getSort() );
        sysMenuRespVO.setParentId( bean.getParentId() );
        sysMenuRespVO.setPath( bean.getPath() );
        sysMenuRespVO.setIcon( bean.getIcon() );
        sysMenuRespVO.setComponent( bean.getComponent() );
        sysMenuRespVO.setId( bean.getId() );
        sysMenuRespVO.setStatus( bean.getStatus() );
        sysMenuRespVO.setCreateTime( bean.getCreateTime() );

        return sysMenuRespVO;
    }

    @Override
    public List<SysMenuSimpleRespVO> convertList02(List<SysMenuDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysMenuSimpleRespVO> list1 = new ArrayList<SysMenuSimpleRespVO>( list.size() );
        for ( SysMenuDO sysMenuDO : list ) {
            list1.add( sysMenuDOToSysMenuSimpleRespVO( sysMenuDO ) );
        }

        return list1;
    }

    protected SysMenuSimpleRespVO sysMenuDOToSysMenuSimpleRespVO(SysMenuDO sysMenuDO) {
        if ( sysMenuDO == null ) {
            return null;
        }

        SysMenuSimpleRespVO sysMenuSimpleRespVO = new SysMenuSimpleRespVO();

        sysMenuSimpleRespVO.setId( sysMenuDO.getId() );
        sysMenuSimpleRespVO.setName( sysMenuDO.getName() );
        sysMenuSimpleRespVO.setParentId( sysMenuDO.getParentId() );

        return sysMenuSimpleRespVO;
    }
}
