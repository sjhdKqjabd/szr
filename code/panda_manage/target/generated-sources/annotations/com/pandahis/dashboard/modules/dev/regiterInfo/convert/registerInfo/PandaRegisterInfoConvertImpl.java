package com.pandahis.dashboard.modules.dev.regiterInfo.convert.registerInfo;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoCreateReqVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoExcelVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoRespVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoUpdateReqVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.dal.dataobject.registerInfo.PandaRegisterInfoDO;
import com.pandahis.dashboard.modules.dev.regiterInfo.dal.dataobject.registerInfo.PandaRegisterInfoDO.PandaRegisterInfoDOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:13+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class PandaRegisterInfoConvertImpl implements PandaRegisterInfoConvert {

    @Override
    public PandaRegisterInfoDO convert(PandaRegisterInfoCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaRegisterInfoDOBuilder pandaRegisterInfoDO = PandaRegisterInfoDO.builder();

        pandaRegisterInfoDO.petName( bean.getPetName() );
        pandaRegisterInfoDO.petId( bean.getPetId() );
        pandaRegisterInfoDO.deptName( bean.getDeptName() );
        pandaRegisterInfoDO.docName( bean.getDocName() );
        pandaRegisterInfoDO.needMoney( bean.getNeedMoney() );
        pandaRegisterInfoDO.chargeMoney( bean.getChargeMoney() );
        pandaRegisterInfoDO.changeMoney( bean.getChangeMoney() );

        return pandaRegisterInfoDO.build();
    }

    @Override
    public PandaRegisterInfoDO convert(PandaRegisterInfoUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaRegisterInfoDOBuilder pandaRegisterInfoDO = PandaRegisterInfoDO.builder();

        pandaRegisterInfoDO.id( bean.getId() );
        pandaRegisterInfoDO.petName( bean.getPetName() );
        pandaRegisterInfoDO.petId( bean.getPetId() );
        pandaRegisterInfoDO.deptName( bean.getDeptName() );
        pandaRegisterInfoDO.docName( bean.getDocName() );
        pandaRegisterInfoDO.needMoney( bean.getNeedMoney() );
        pandaRegisterInfoDO.chargeMoney( bean.getChargeMoney() );
        pandaRegisterInfoDO.changeMoney( bean.getChangeMoney() );

        return pandaRegisterInfoDO.build();
    }

    @Override
    public PandaRegisterInfoRespVO convert(PandaRegisterInfoDO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaRegisterInfoRespVO pandaRegisterInfoRespVO = new PandaRegisterInfoRespVO();

        pandaRegisterInfoRespVO.setPetName( bean.getPetName() );
        pandaRegisterInfoRespVO.setPetId( bean.getPetId() );
        pandaRegisterInfoRespVO.setDeptName( bean.getDeptName() );
        pandaRegisterInfoRespVO.setDocName( bean.getDocName() );
        pandaRegisterInfoRespVO.setNeedMoney( bean.getNeedMoney() );
        pandaRegisterInfoRespVO.setChargeMoney( bean.getChargeMoney() );
        pandaRegisterInfoRespVO.setChangeMoney( bean.getChangeMoney() );
        pandaRegisterInfoRespVO.setId( bean.getId() );
        pandaRegisterInfoRespVO.setCreateTime( bean.getCreateTime() );

        return pandaRegisterInfoRespVO;
    }

    @Override
    public List<PandaRegisterInfoRespVO> convertList(List<PandaRegisterInfoDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PandaRegisterInfoRespVO> list1 = new ArrayList<PandaRegisterInfoRespVO>( list.size() );
        for ( PandaRegisterInfoDO pandaRegisterInfoDO : list ) {
            list1.add( convert( pandaRegisterInfoDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<PandaRegisterInfoRespVO> convertPage(PageResult<PandaRegisterInfoDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<PandaRegisterInfoRespVO> pageResult = new PageResult<PandaRegisterInfoRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<PandaRegisterInfoExcelVO> convertList02(List<PandaRegisterInfoDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PandaRegisterInfoExcelVO> list1 = new ArrayList<PandaRegisterInfoExcelVO>( list.size() );
        for ( PandaRegisterInfoDO pandaRegisterInfoDO : list ) {
            list1.add( pandaRegisterInfoDOToPandaRegisterInfoExcelVO( pandaRegisterInfoDO ) );
        }

        return list1;
    }

    protected PandaRegisterInfoExcelVO pandaRegisterInfoDOToPandaRegisterInfoExcelVO(PandaRegisterInfoDO pandaRegisterInfoDO) {
        if ( pandaRegisterInfoDO == null ) {
            return null;
        }

        PandaRegisterInfoExcelVO pandaRegisterInfoExcelVO = new PandaRegisterInfoExcelVO();

        pandaRegisterInfoExcelVO.setId( pandaRegisterInfoDO.getId() );
        pandaRegisterInfoExcelVO.setCreateTime( pandaRegisterInfoDO.getCreateTime() );
        pandaRegisterInfoExcelVO.setPetName( pandaRegisterInfoDO.getPetName() );
        pandaRegisterInfoExcelVO.setPetId( pandaRegisterInfoDO.getPetId() );
        pandaRegisterInfoExcelVO.setDeptName( pandaRegisterInfoDO.getDeptName() );
        pandaRegisterInfoExcelVO.setDocName( pandaRegisterInfoDO.getDocName() );
        pandaRegisterInfoExcelVO.setNeedMoney( pandaRegisterInfoDO.getNeedMoney() );
        pandaRegisterInfoExcelVO.setChargeMoney( pandaRegisterInfoDO.getChargeMoney() );
        pandaRegisterInfoExcelVO.setChangeMoney( pandaRegisterInfoDO.getChangeMoney() );

        return pandaRegisterInfoExcelVO;
    }
}
