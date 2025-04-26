package com.pandahis.dashboard.modules.system.convert.dict;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.controller.dict.vo.data.SysDictDataCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.dict.vo.data.SysDictDataExcelVO;
import com.pandahis.dashboard.modules.system.controller.dict.vo.data.SysDictDataRespVO;
import com.pandahis.dashboard.modules.system.controller.dict.vo.data.SysDictDataSimpleVO;
import com.pandahis.dashboard.modules.system.controller.dict.vo.data.SysDictDataUpdateReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.dict.SysDictDataDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:12+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class SysDictDataConvertImpl implements SysDictDataConvert {

    @Override
    public List<SysDictDataSimpleVO> convertList(List<SysDictDataDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysDictDataSimpleVO> list1 = new ArrayList<SysDictDataSimpleVO>( list.size() );
        for ( SysDictDataDO sysDictDataDO : list ) {
            list1.add( sysDictDataDOToSysDictDataSimpleVO( sysDictDataDO ) );
        }

        return list1;
    }

    @Override
    public SysDictDataRespVO convert(SysDictDataDO bean) {
        if ( bean == null ) {
            return null;
        }

        SysDictDataRespVO sysDictDataRespVO = new SysDictDataRespVO();

        sysDictDataRespVO.setSort( bean.getSort() );
        sysDictDataRespVO.setLabel( bean.getLabel() );
        sysDictDataRespVO.setValue( bean.getValue() );
        sysDictDataRespVO.setDictType( bean.getDictType() );
        sysDictDataRespVO.setStatus( bean.getStatus() );
        sysDictDataRespVO.setRemark( bean.getRemark() );
        sysDictDataRespVO.setId( bean.getId() );
        sysDictDataRespVO.setCreateTime( bean.getCreateTime() );

        return sysDictDataRespVO;
    }

    @Override
    public PageResult<SysDictDataRespVO> convertPage(PageResult<SysDictDataDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<SysDictDataRespVO> pageResult = new PageResult<SysDictDataRespVO>();

        pageResult.setList( sysDictDataDOListToSysDictDataRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public SysDictDataDO convert(SysDictDataUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysDictDataDO sysDictDataDO = new SysDictDataDO();

        sysDictDataDO.setId( bean.getId() );
        sysDictDataDO.setSort( bean.getSort() );
        sysDictDataDO.setLabel( bean.getLabel() );
        sysDictDataDO.setValue( bean.getValue() );
        sysDictDataDO.setDictType( bean.getDictType() );
        sysDictDataDO.setStatus( bean.getStatus() );
        sysDictDataDO.setRemark( bean.getRemark() );

        return sysDictDataDO;
    }

    @Override
    public SysDictDataDO convert(SysDictDataCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysDictDataDO sysDictDataDO = new SysDictDataDO();

        sysDictDataDO.setSort( bean.getSort() );
        sysDictDataDO.setLabel( bean.getLabel() );
        sysDictDataDO.setValue( bean.getValue() );
        sysDictDataDO.setDictType( bean.getDictType() );
        sysDictDataDO.setStatus( bean.getStatus() );
        sysDictDataDO.setRemark( bean.getRemark() );

        return sysDictDataDO;
    }

    @Override
    public List<SysDictDataExcelVO> convertList02(List<SysDictDataDO> bean) {
        if ( bean == null ) {
            return null;
        }

        List<SysDictDataExcelVO> list = new ArrayList<SysDictDataExcelVO>( bean.size() );
        for ( SysDictDataDO sysDictDataDO : bean ) {
            list.add( sysDictDataDOToSysDictDataExcelVO( sysDictDataDO ) );
        }

        return list;
    }

    protected SysDictDataSimpleVO sysDictDataDOToSysDictDataSimpleVO(SysDictDataDO sysDictDataDO) {
        if ( sysDictDataDO == null ) {
            return null;
        }

        SysDictDataSimpleVO sysDictDataSimpleVO = new SysDictDataSimpleVO();

        sysDictDataSimpleVO.setDictType( sysDictDataDO.getDictType() );
        sysDictDataSimpleVO.setValue( sysDictDataDO.getValue() );
        sysDictDataSimpleVO.setLabel( sysDictDataDO.getLabel() );

        return sysDictDataSimpleVO;
    }

    protected List<SysDictDataRespVO> sysDictDataDOListToSysDictDataRespVOList(List<SysDictDataDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysDictDataRespVO> list1 = new ArrayList<SysDictDataRespVO>( list.size() );
        for ( SysDictDataDO sysDictDataDO : list ) {
            list1.add( convert( sysDictDataDO ) );
        }

        return list1;
    }

    protected SysDictDataExcelVO sysDictDataDOToSysDictDataExcelVO(SysDictDataDO sysDictDataDO) {
        if ( sysDictDataDO == null ) {
            return null;
        }

        SysDictDataExcelVO sysDictDataExcelVO = new SysDictDataExcelVO();

        sysDictDataExcelVO.setId( sysDictDataDO.getId() );
        sysDictDataExcelVO.setSort( sysDictDataDO.getSort() );
        sysDictDataExcelVO.setLabel( sysDictDataDO.getLabel() );
        sysDictDataExcelVO.setValue( sysDictDataDO.getValue() );
        sysDictDataExcelVO.setDictType( sysDictDataDO.getDictType() );
        sysDictDataExcelVO.setStatus( sysDictDataDO.getStatus() );

        return sysDictDataExcelVO;
    }
}
