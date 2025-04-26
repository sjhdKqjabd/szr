package com.pandahis.dashboard.modules.system.convert.dept;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.controller.dept.vo.post.SysPostCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.post.SysPostExcelVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.post.SysPostRespVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.post.SysPostSimpleRespVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.post.SysPostUpdateReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysPostDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:12+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class SysPostConvertImpl implements SysPostConvert {

    @Override
    public List<SysPostSimpleRespVO> convertList02(List<SysPostDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysPostSimpleRespVO> list1 = new ArrayList<SysPostSimpleRespVO>( list.size() );
        for ( SysPostDO sysPostDO : list ) {
            list1.add( sysPostDOToSysPostSimpleRespVO( sysPostDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<SysPostRespVO> convertPage(PageResult<SysPostDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<SysPostRespVO> pageResult = new PageResult<SysPostRespVO>();

        pageResult.setList( sysPostDOListToSysPostRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public SysPostRespVO convert(SysPostDO id) {
        if ( id == null ) {
            return null;
        }

        SysPostRespVO sysPostRespVO = new SysPostRespVO();

        sysPostRespVO.setName( id.getName() );
        sysPostRespVO.setCode( id.getCode() );
        sysPostRespVO.setSort( id.getSort() );
        sysPostRespVO.setStatus( id.getStatus() );
        sysPostRespVO.setRemark( id.getRemark() );
        sysPostRespVO.setId( id.getId() );
        sysPostRespVO.setCreateTime( id.getCreateTime() );

        return sysPostRespVO;
    }

    @Override
    public SysPostDO convert(SysPostCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysPostDO sysPostDO = new SysPostDO();

        sysPostDO.setName( bean.getName() );
        sysPostDO.setCode( bean.getCode() );
        sysPostDO.setSort( bean.getSort() );
        sysPostDO.setStatus( bean.getStatus() );
        sysPostDO.setRemark( bean.getRemark() );

        return sysPostDO;
    }

    @Override
    public SysPostDO convert(SysPostUpdateReqVO reqVO) {
        if ( reqVO == null ) {
            return null;
        }

        SysPostDO sysPostDO = new SysPostDO();

        sysPostDO.setId( reqVO.getId() );
        sysPostDO.setName( reqVO.getName() );
        sysPostDO.setCode( reqVO.getCode() );
        sysPostDO.setSort( reqVO.getSort() );
        sysPostDO.setStatus( reqVO.getStatus() );
        sysPostDO.setRemark( reqVO.getRemark() );

        return sysPostDO;
    }

    @Override
    public List<SysPostExcelVO> convertList03(List<SysPostDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysPostExcelVO> list1 = new ArrayList<SysPostExcelVO>( list.size() );
        for ( SysPostDO sysPostDO : list ) {
            list1.add( sysPostDOToSysPostExcelVO( sysPostDO ) );
        }

        return list1;
    }

    protected SysPostSimpleRespVO sysPostDOToSysPostSimpleRespVO(SysPostDO sysPostDO) {
        if ( sysPostDO == null ) {
            return null;
        }

        SysPostSimpleRespVO sysPostSimpleRespVO = new SysPostSimpleRespVO();

        sysPostSimpleRespVO.setId( sysPostDO.getId() );
        sysPostSimpleRespVO.setName( sysPostDO.getName() );

        return sysPostSimpleRespVO;
    }

    protected List<SysPostRespVO> sysPostDOListToSysPostRespVOList(List<SysPostDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysPostRespVO> list1 = new ArrayList<SysPostRespVO>( list.size() );
        for ( SysPostDO sysPostDO : list ) {
            list1.add( convert( sysPostDO ) );
        }

        return list1;
    }

    protected SysPostExcelVO sysPostDOToSysPostExcelVO(SysPostDO sysPostDO) {
        if ( sysPostDO == null ) {
            return null;
        }

        SysPostExcelVO sysPostExcelVO = new SysPostExcelVO();

        sysPostExcelVO.setId( sysPostDO.getId() );
        sysPostExcelVO.setCode( sysPostDO.getCode() );
        sysPostExcelVO.setName( sysPostDO.getName() );
        sysPostExcelVO.setSort( sysPostDO.getSort() );
        if ( sysPostDO.getStatus() != null ) {
            sysPostExcelVO.setStatus( String.valueOf( sysPostDO.getStatus() ) );
        }

        return sysPostExcelVO;
    }
}
