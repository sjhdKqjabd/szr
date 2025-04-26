package com.pandahis.dashboard.modules.system.convert.dept;

import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptRespVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptSimpleRespVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptUpdateReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysDeptDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:13+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class SysDeptConvertImpl implements SysDeptConvert {

    @Override
    public List<SysDeptRespVO> convertList(List<SysDeptDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysDeptRespVO> list1 = new ArrayList<SysDeptRespVO>( list.size() );
        for ( SysDeptDO sysDeptDO : list ) {
            list1.add( convert( sysDeptDO ) );
        }

        return list1;
    }

    @Override
    public List<SysDeptSimpleRespVO> convertList02(List<SysDeptDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysDeptSimpleRespVO> list1 = new ArrayList<SysDeptSimpleRespVO>( list.size() );
        for ( SysDeptDO sysDeptDO : list ) {
            list1.add( sysDeptDOToSysDeptSimpleRespVO( sysDeptDO ) );
        }

        return list1;
    }

    @Override
    public SysDeptRespVO convert(SysDeptDO bean) {
        if ( bean == null ) {
            return null;
        }

        SysDeptRespVO sysDeptRespVO = new SysDeptRespVO();

        sysDeptRespVO.setName( bean.getName() );
        sysDeptRespVO.setParentId( bean.getParentId() );
        sysDeptRespVO.setSort( bean.getSort() );
        sysDeptRespVO.setLeader( bean.getLeader() );
        sysDeptRespVO.setPhone( bean.getPhone() );
        sysDeptRespVO.setEmail( bean.getEmail() );
        sysDeptRespVO.setId( bean.getId() );
        sysDeptRespVO.setStatus( bean.getStatus() );
        sysDeptRespVO.setCreateTime( bean.getCreateTime() );

        return sysDeptRespVO;
    }

    @Override
    public SysDeptDO convert(SysDeptCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysDeptDO sysDeptDO = new SysDeptDO();

        sysDeptDO.setName( bean.getName() );
        sysDeptDO.setParentId( bean.getParentId() );
        sysDeptDO.setSort( bean.getSort() );
        sysDeptDO.setLeader( bean.getLeader() );
        sysDeptDO.setPhone( bean.getPhone() );
        sysDeptDO.setEmail( bean.getEmail() );
        sysDeptDO.setStatus( bean.getStatus() );

        return sysDeptDO;
    }

    @Override
    public SysDeptDO convert(SysDeptUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysDeptDO sysDeptDO = new SysDeptDO();

        sysDeptDO.setId( bean.getId() );
        sysDeptDO.setName( bean.getName() );
        sysDeptDO.setParentId( bean.getParentId() );
        sysDeptDO.setSort( bean.getSort() );
        sysDeptDO.setLeader( bean.getLeader() );
        sysDeptDO.setPhone( bean.getPhone() );
        sysDeptDO.setEmail( bean.getEmail() );
        sysDeptDO.setStatus( bean.getStatus() );

        return sysDeptDO;
    }

    protected SysDeptSimpleRespVO sysDeptDOToSysDeptSimpleRespVO(SysDeptDO sysDeptDO) {
        if ( sysDeptDO == null ) {
            return null;
        }

        SysDeptSimpleRespVO sysDeptSimpleRespVO = new SysDeptSimpleRespVO();

        sysDeptSimpleRespVO.setId( sysDeptDO.getId() );
        sysDeptSimpleRespVO.setName( sysDeptDO.getName() );
        sysDeptSimpleRespVO.setParentId( sysDeptDO.getParentId() );

        return sysDeptSimpleRespVO;
    }
}
