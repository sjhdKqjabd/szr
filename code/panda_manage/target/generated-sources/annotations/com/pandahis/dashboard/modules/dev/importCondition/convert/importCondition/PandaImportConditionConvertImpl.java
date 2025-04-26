package com.pandahis.dashboard.modules.dev.importCondition.convert.importCondition;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionCreateReqVO;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionExcelVO;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionRespVO;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionUpdateReqVO;
import com.pandahis.dashboard.modules.dev.importCondition.dal.dataobject.importCondition.PandaImportConditionDO;
import com.pandahis.dashboard.modules.dev.importCondition.dal.dataobject.importCondition.PandaImportConditionDO.PandaImportConditionDOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:13+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class PandaImportConditionConvertImpl implements PandaImportConditionConvert {

    @Override
    public PandaImportConditionDO convert(PandaImportConditionCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaImportConditionDOBuilder pandaImportConditionDO = PandaImportConditionDO.builder();

        pandaImportConditionDO.conditionInfo( bean.getConditionInfo() );
        pandaImportConditionDO.deptName( bean.getDeptName() );
        pandaImportConditionDO.docName( bean.getDocName() );

        return pandaImportConditionDO.build();
    }

    @Override
    public PandaImportConditionDO convert(PandaImportConditionUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaImportConditionDOBuilder pandaImportConditionDO = PandaImportConditionDO.builder();

        pandaImportConditionDO.id( bean.getId() );
        pandaImportConditionDO.conditionInfo( bean.getConditionInfo() );
        pandaImportConditionDO.deptName( bean.getDeptName() );
        pandaImportConditionDO.docName( bean.getDocName() );

        return pandaImportConditionDO.build();
    }

    @Override
    public PandaImportConditionRespVO convert(PandaImportConditionDO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaImportConditionRespVO pandaImportConditionRespVO = new PandaImportConditionRespVO();

        pandaImportConditionRespVO.setConditionInfo( bean.getConditionInfo() );
        pandaImportConditionRespVO.setDeptName( bean.getDeptName() );
        pandaImportConditionRespVO.setDocName( bean.getDocName() );
        pandaImportConditionRespVO.setId( bean.getId() );
        pandaImportConditionRespVO.setCreateTime( bean.getCreateTime() );

        return pandaImportConditionRespVO;
    }

    @Override
    public List<PandaImportConditionRespVO> convertList(List<PandaImportConditionDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PandaImportConditionRespVO> list1 = new ArrayList<PandaImportConditionRespVO>( list.size() );
        for ( PandaImportConditionDO pandaImportConditionDO : list ) {
            list1.add( convert( pandaImportConditionDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<PandaImportConditionRespVO> convertPage(PageResult<PandaImportConditionDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<PandaImportConditionRespVO> pageResult = new PageResult<PandaImportConditionRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<PandaImportConditionExcelVO> convertList02(List<PandaImportConditionDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PandaImportConditionExcelVO> list1 = new ArrayList<PandaImportConditionExcelVO>( list.size() );
        for ( PandaImportConditionDO pandaImportConditionDO : list ) {
            list1.add( pandaImportConditionDOToPandaImportConditionExcelVO( pandaImportConditionDO ) );
        }

        return list1;
    }

    protected PandaImportConditionExcelVO pandaImportConditionDOToPandaImportConditionExcelVO(PandaImportConditionDO pandaImportConditionDO) {
        if ( pandaImportConditionDO == null ) {
            return null;
        }

        PandaImportConditionExcelVO pandaImportConditionExcelVO = new PandaImportConditionExcelVO();

        pandaImportConditionExcelVO.setId( pandaImportConditionDO.getId() );
        pandaImportConditionExcelVO.setCreateTime( pandaImportConditionDO.getCreateTime() );
        pandaImportConditionExcelVO.setConditionInfo( pandaImportConditionDO.getConditionInfo() );
        pandaImportConditionExcelVO.setDeptName( pandaImportConditionDO.getDeptName() );

        return pandaImportConditionExcelVO;
    }
}
