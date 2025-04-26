package com.pandahis.dashboard.modules.dev.patRecord.convert.patRecord;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordCreateReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordExcelVO;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordRespVO;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordUpdateReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.dal.dataobject.patRecord.PandaPatRecordDO;
import com.pandahis.dashboard.modules.dev.patRecord.dal.dataobject.patRecord.PandaPatRecordDO.PandaPatRecordDOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:12+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class PandaPatRecordConvertImpl implements PandaPatRecordConvert {

    @Override
    public PandaPatRecordDO convert(PandaPatRecordCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaPatRecordDOBuilder pandaPatRecordDO = PandaPatRecordDO.builder();

        pandaPatRecordDO.result( bean.getResult() );
        pandaPatRecordDO.descInfo( bean.getDescInfo() );
        pandaPatRecordDO.docName( bean.getDocName() );
        pandaPatRecordDO.deptName( bean.getDeptName() );
        pandaPatRecordDO.petName( bean.getPetName() );
        pandaPatRecordDO.petId( bean.getPetId() );

        return pandaPatRecordDO.build();
    }

    @Override
    public PandaPatRecordDO convert(PandaPatRecordUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaPatRecordDOBuilder pandaPatRecordDO = PandaPatRecordDO.builder();

        pandaPatRecordDO.id( bean.getId() );
        pandaPatRecordDO.result( bean.getResult() );
        pandaPatRecordDO.descInfo( bean.getDescInfo() );
        pandaPatRecordDO.docName( bean.getDocName() );
        pandaPatRecordDO.deptName( bean.getDeptName() );
        pandaPatRecordDO.petName( bean.getPetName() );
        pandaPatRecordDO.petId( bean.getPetId() );

        return pandaPatRecordDO.build();
    }

    @Override
    public PandaPatRecordRespVO convert(PandaPatRecordDO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaPatRecordRespVO pandaPatRecordRespVO = new PandaPatRecordRespVO();

        pandaPatRecordRespVO.setResult( bean.getResult() );
        pandaPatRecordRespVO.setDescInfo( bean.getDescInfo() );
        pandaPatRecordRespVO.setDocName( bean.getDocName() );
        pandaPatRecordRespVO.setDeptName( bean.getDeptName() );
        pandaPatRecordRespVO.setPetName( bean.getPetName() );
        pandaPatRecordRespVO.setId( bean.getId() );
        pandaPatRecordRespVO.setCreateTime( bean.getCreateTime() );

        return pandaPatRecordRespVO;
    }

    @Override
    public List<PandaPatRecordRespVO> convertList(List<PandaPatRecordDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PandaPatRecordRespVO> list1 = new ArrayList<PandaPatRecordRespVO>( list.size() );
        for ( PandaPatRecordDO pandaPatRecordDO : list ) {
            list1.add( convert( pandaPatRecordDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<PandaPatRecordRespVO> convertPage(PageResult<PandaPatRecordDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<PandaPatRecordRespVO> pageResult = new PageResult<PandaPatRecordRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<PandaPatRecordExcelVO> convertList02(List<PandaPatRecordDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PandaPatRecordExcelVO> list1 = new ArrayList<PandaPatRecordExcelVO>( list.size() );
        for ( PandaPatRecordDO pandaPatRecordDO : list ) {
            list1.add( pandaPatRecordDOToPandaPatRecordExcelVO( pandaPatRecordDO ) );
        }

        return list1;
    }

    protected PandaPatRecordExcelVO pandaPatRecordDOToPandaPatRecordExcelVO(PandaPatRecordDO pandaPatRecordDO) {
        if ( pandaPatRecordDO == null ) {
            return null;
        }

        PandaPatRecordExcelVO pandaPatRecordExcelVO = new PandaPatRecordExcelVO();

        pandaPatRecordExcelVO.setId( pandaPatRecordDO.getId() );
        pandaPatRecordExcelVO.setCreateTime( pandaPatRecordDO.getCreateTime() );
        pandaPatRecordExcelVO.setResult( pandaPatRecordDO.getResult() );
        pandaPatRecordExcelVO.setDescInfo( pandaPatRecordDO.getDescInfo() );
        pandaPatRecordExcelVO.setDocName( pandaPatRecordDO.getDocName() );
        pandaPatRecordExcelVO.setDeptName( pandaPatRecordDO.getDeptName() );
        pandaPatRecordExcelVO.setPetName( pandaPatRecordDO.getPetName() );

        return pandaPatRecordExcelVO;
    }
}
