package com.pandahis.dashboard.modules.system.convert.dict;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.controller.dict.vo.type.SysDictTypeCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.dict.vo.type.SysDictTypeExcelVO;
import com.pandahis.dashboard.modules.system.controller.dict.vo.type.SysDictTypeRespVO;
import com.pandahis.dashboard.modules.system.controller.dict.vo.type.SysDictTypeSimpleRespVO;
import com.pandahis.dashboard.modules.system.controller.dict.vo.type.SysDictTypeUpdateReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.dict.SysDictTypeDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.dict.SysDictTypeDO.SysDictTypeDOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:12+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class SysDictTypeConvertImpl implements SysDictTypeConvert {

    @Override
    public PageResult<SysDictTypeRespVO> convertPage(PageResult<SysDictTypeDO> bean) {
        if ( bean == null ) {
            return null;
        }

        PageResult<SysDictTypeRespVO> pageResult = new PageResult<SysDictTypeRespVO>();

        pageResult.setList( sysDictTypeDOListToSysDictTypeRespVOList( bean.getList() ) );
        pageResult.setTotal( bean.getTotal() );

        return pageResult;
    }

    @Override
    public SysDictTypeRespVO convert(SysDictTypeDO bean) {
        if ( bean == null ) {
            return null;
        }

        SysDictTypeRespVO sysDictTypeRespVO = new SysDictTypeRespVO();

        sysDictTypeRespVO.setName( bean.getName() );
        sysDictTypeRespVO.setStatus( bean.getStatus() );
        sysDictTypeRespVO.setRemark( bean.getRemark() );
        sysDictTypeRespVO.setId( bean.getId() );
        sysDictTypeRespVO.setType( bean.getType() );
        sysDictTypeRespVO.setCreateTime( bean.getCreateTime() );

        return sysDictTypeRespVO;
    }

    @Override
    public SysDictTypeDO convert(SysDictTypeCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysDictTypeDOBuilder sysDictTypeDO = SysDictTypeDO.builder();

        sysDictTypeDO.name( bean.getName() );
        sysDictTypeDO.type( bean.getType() );
        sysDictTypeDO.status( bean.getStatus() );
        sysDictTypeDO.remark( bean.getRemark() );

        return sysDictTypeDO.build();
    }

    @Override
    public SysDictTypeDO convert(SysDictTypeUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysDictTypeDOBuilder sysDictTypeDO = SysDictTypeDO.builder();

        sysDictTypeDO.id( bean.getId() );
        sysDictTypeDO.name( bean.getName() );
        sysDictTypeDO.status( bean.getStatus() );
        sysDictTypeDO.remark( bean.getRemark() );

        return sysDictTypeDO.build();
    }

    @Override
    public List<SysDictTypeSimpleRespVO> convertList(List<SysDictTypeDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysDictTypeSimpleRespVO> list1 = new ArrayList<SysDictTypeSimpleRespVO>( list.size() );
        for ( SysDictTypeDO sysDictTypeDO : list ) {
            list1.add( sysDictTypeDOToSysDictTypeSimpleRespVO( sysDictTypeDO ) );
        }

        return list1;
    }

    @Override
    public List<SysDictTypeExcelVO> convertList02(List<SysDictTypeDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysDictTypeExcelVO> list1 = new ArrayList<SysDictTypeExcelVO>( list.size() );
        for ( SysDictTypeDO sysDictTypeDO : list ) {
            list1.add( sysDictTypeDOToSysDictTypeExcelVO( sysDictTypeDO ) );
        }

        return list1;
    }

    protected List<SysDictTypeRespVO> sysDictTypeDOListToSysDictTypeRespVOList(List<SysDictTypeDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysDictTypeRespVO> list1 = new ArrayList<SysDictTypeRespVO>( list.size() );
        for ( SysDictTypeDO sysDictTypeDO : list ) {
            list1.add( convert( sysDictTypeDO ) );
        }

        return list1;
    }

    protected SysDictTypeSimpleRespVO sysDictTypeDOToSysDictTypeSimpleRespVO(SysDictTypeDO sysDictTypeDO) {
        if ( sysDictTypeDO == null ) {
            return null;
        }

        SysDictTypeSimpleRespVO sysDictTypeSimpleRespVO = new SysDictTypeSimpleRespVO();

        sysDictTypeSimpleRespVO.setId( sysDictTypeDO.getId() );
        sysDictTypeSimpleRespVO.setName( sysDictTypeDO.getName() );
        sysDictTypeSimpleRespVO.setType( sysDictTypeDO.getType() );

        return sysDictTypeSimpleRespVO;
    }

    protected SysDictTypeExcelVO sysDictTypeDOToSysDictTypeExcelVO(SysDictTypeDO sysDictTypeDO) {
        if ( sysDictTypeDO == null ) {
            return null;
        }

        SysDictTypeExcelVO sysDictTypeExcelVO = new SysDictTypeExcelVO();

        sysDictTypeExcelVO.setId( sysDictTypeDO.getId() );
        sysDictTypeExcelVO.setName( sysDictTypeDO.getName() );
        sysDictTypeExcelVO.setType( sysDictTypeDO.getType() );
        sysDictTypeExcelVO.setStatus( sysDictTypeDO.getStatus() );

        return sysDictTypeExcelVO;
    }
}
