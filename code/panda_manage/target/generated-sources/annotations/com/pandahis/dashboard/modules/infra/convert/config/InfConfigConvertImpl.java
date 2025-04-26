package com.pandahis.dashboard.modules.infra.convert.config;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.infra.controller.config.vo.InfConfigCreateReqVO;
import com.pandahis.dashboard.modules.infra.controller.config.vo.InfConfigExcelVO;
import com.pandahis.dashboard.modules.infra.controller.config.vo.InfConfigRespVO;
import com.pandahis.dashboard.modules.infra.controller.config.vo.InfConfigUpdateReqVO;
import com.pandahis.dashboard.modules.infra.dal.dataobject.config.InfConfigDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:13+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class InfConfigConvertImpl implements InfConfigConvert {

    @Override
    public PageResult<InfConfigRespVO> convertPage(PageResult<InfConfigDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<InfConfigRespVO> pageResult = new PageResult<InfConfigRespVO>();

        pageResult.setList( infConfigDOListToInfConfigRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public InfConfigRespVO convert(InfConfigDO bean) {
        if ( bean == null ) {
            return null;
        }

        InfConfigRespVO infConfigRespVO = new InfConfigRespVO();

        infConfigRespVO.setGroup( bean.getGroup() );
        infConfigRespVO.setName( bean.getName() );
        infConfigRespVO.setValue( bean.getValue() );
        infConfigRespVO.setSensitive( bean.getSensitive() );
        infConfigRespVO.setRemark( bean.getRemark() );
        infConfigRespVO.setId( bean.getId() );
        infConfigRespVO.setKey( bean.getKey() );
        infConfigRespVO.setType( bean.getType() );
        infConfigRespVO.setCreateTime( bean.getCreateTime() );

        return infConfigRespVO;
    }

    @Override
    public InfConfigDO convert(InfConfigCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        InfConfigDO infConfigDO = new InfConfigDO();

        infConfigDO.setGroup( bean.getGroup() );
        infConfigDO.setName( bean.getName() );
        infConfigDO.setKey( bean.getKey() );
        infConfigDO.setValue( bean.getValue() );
        infConfigDO.setSensitive( bean.getSensitive() );
        infConfigDO.setRemark( bean.getRemark() );

        return infConfigDO;
    }

    @Override
    public InfConfigDO convert(InfConfigUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        InfConfigDO infConfigDO = new InfConfigDO();

        infConfigDO.setId( bean.getId() );
        infConfigDO.setGroup( bean.getGroup() );
        infConfigDO.setName( bean.getName() );
        infConfigDO.setValue( bean.getValue() );
        infConfigDO.setSensitive( bean.getSensitive() );
        infConfigDO.setRemark( bean.getRemark() );

        return infConfigDO;
    }

    @Override
    public List<InfConfigExcelVO> convertList(List<InfConfigDO> list) {
        if ( list == null ) {
            return null;
        }

        List<InfConfigExcelVO> list1 = new ArrayList<InfConfigExcelVO>( list.size() );
        for ( InfConfigDO infConfigDO : list ) {
            list1.add( infConfigDOToInfConfigExcelVO( infConfigDO ) );
        }

        return list1;
    }

    protected List<InfConfigRespVO> infConfigDOListToInfConfigRespVOList(List<InfConfigDO> list) {
        if ( list == null ) {
            return null;
        }

        List<InfConfigRespVO> list1 = new ArrayList<InfConfigRespVO>( list.size() );
        for ( InfConfigDO infConfigDO : list ) {
            list1.add( convert( infConfigDO ) );
        }

        return list1;
    }

    protected InfConfigExcelVO infConfigDOToInfConfigExcelVO(InfConfigDO infConfigDO) {
        if ( infConfigDO == null ) {
            return null;
        }

        InfConfigExcelVO infConfigExcelVO = new InfConfigExcelVO();

        infConfigExcelVO.setId( infConfigDO.getId() );
        infConfigExcelVO.setKey( infConfigDO.getKey() );
        infConfigExcelVO.setGroup( infConfigDO.getGroup() );
        infConfigExcelVO.setName( infConfigDO.getName() );
        infConfigExcelVO.setValue( infConfigDO.getValue() );
        infConfigExcelVO.setType( infConfigDO.getType() );
        infConfigExcelVO.setSensitive( infConfigDO.getSensitive() );
        infConfigExcelVO.setRemark( infConfigDO.getRemark() );
        infConfigExcelVO.setCreateTime( infConfigDO.getCreateTime() );

        return infConfigExcelVO;
    }
}
