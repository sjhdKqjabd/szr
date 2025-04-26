package com.pandahis.dashboard.modules.infra.convert.file;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.infra.controller.file.vo.InfFileRespVO;
import com.pandahis.dashboard.modules.infra.dal.dataobject.file.InfFileDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:13+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class InfFileConvertImpl implements InfFileConvert {

    @Override
    public InfFileRespVO convert(InfFileDO bean) {
        if ( bean == null ) {
            return null;
        }

        InfFileRespVO infFileRespVO = new InfFileRespVO();

        infFileRespVO.setId( bean.getId() );
        infFileRespVO.setType( bean.getType() );
        infFileRespVO.setCreateTime( bean.getCreateTime() );

        return infFileRespVO;
    }

    @Override
    public PageResult<InfFileRespVO> convertPage(PageResult<InfFileDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<InfFileRespVO> pageResult = new PageResult<InfFileRespVO>();

        pageResult.setList( infFileDOListToInfFileRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    protected List<InfFileRespVO> infFileDOListToInfFileRespVOList(List<InfFileDO> list) {
        if ( list == null ) {
            return null;
        }

        List<InfFileRespVO> list1 = new ArrayList<InfFileRespVO>( list.size() );
        for ( InfFileDO infFileDO : list ) {
            list1.add( convert( infFileDO ) );
        }

        return list1;
    }
}
