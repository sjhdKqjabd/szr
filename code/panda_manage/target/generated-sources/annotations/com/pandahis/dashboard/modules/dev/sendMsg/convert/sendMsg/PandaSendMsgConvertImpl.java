package com.pandahis.dashboard.modules.dev.sendMsg.convert.sendMsg;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgCreateReqVO;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgExcelVO;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgRespVO;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgUpdateReqVO;
import com.pandahis.dashboard.modules.dev.sendMsg.dal.dataobject.sendMsg.PandaSendMsgDO;
import com.pandahis.dashboard.modules.dev.sendMsg.dal.dataobject.sendMsg.PandaSendMsgDO.PandaSendMsgDOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:11+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class PandaSendMsgConvertImpl implements PandaSendMsgConvert {

    @Override
    public PandaSendMsgDO convert(PandaSendMsgCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaSendMsgDOBuilder pandaSendMsgDO = PandaSendMsgDO.builder();

        pandaSendMsgDO.type( bean.getType() );
        pandaSendMsgDO.content( bean.getContent() );
        pandaSendMsgDO.status( bean.getStatus() );
        pandaSendMsgDO.sendTime( bean.getSendTime() );
        pandaSendMsgDO.toContactId( bean.getToContactId() );
        pandaSendMsgDO.fromUserId( bean.getFromUserId() );

        return pandaSendMsgDO.build();
    }

    @Override
    public PandaSendMsgDO convert(PandaSendMsgUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaSendMsgDOBuilder pandaSendMsgDO = PandaSendMsgDO.builder();

        pandaSendMsgDO.id( bean.getId() );
        pandaSendMsgDO.type( bean.getType() );
        pandaSendMsgDO.content( bean.getContent() );
        pandaSendMsgDO.status( bean.getStatus() );
        pandaSendMsgDO.sendTime( bean.getSendTime() );
        pandaSendMsgDO.toContactId( bean.getToContactId() );
        pandaSendMsgDO.fromUserId( bean.getFromUserId() );

        return pandaSendMsgDO.build();
    }

    @Override
    public PandaSendMsgRespVO convert(PandaSendMsgDO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaSendMsgRespVO pandaSendMsgRespVO = new PandaSendMsgRespVO();

        pandaSendMsgRespVO.setType( bean.getType() );
        pandaSendMsgRespVO.setContent( bean.getContent() );
        pandaSendMsgRespVO.setStatus( bean.getStatus() );
        pandaSendMsgRespVO.setSendTime( bean.getSendTime() );
        pandaSendMsgRespVO.setToContactId( bean.getToContactId() );
        pandaSendMsgRespVO.setFromUserId( bean.getFromUserId() );
        pandaSendMsgRespVO.setId( bean.getId() );
        pandaSendMsgRespVO.setCreateTime( bean.getCreateTime() );

        return pandaSendMsgRespVO;
    }

    @Override
    public List<PandaSendMsgRespVO> convertList(List<PandaSendMsgDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PandaSendMsgRespVO> list1 = new ArrayList<PandaSendMsgRespVO>( list.size() );
        for ( PandaSendMsgDO pandaSendMsgDO : list ) {
            list1.add( convert( pandaSendMsgDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<PandaSendMsgRespVO> convertPage(PageResult<PandaSendMsgDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<PandaSendMsgRespVO> pageResult = new PageResult<PandaSendMsgRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<PandaSendMsgExcelVO> convertList02(List<PandaSendMsgDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PandaSendMsgExcelVO> list1 = new ArrayList<PandaSendMsgExcelVO>( list.size() );
        for ( PandaSendMsgDO pandaSendMsgDO : list ) {
            list1.add( pandaSendMsgDOToPandaSendMsgExcelVO( pandaSendMsgDO ) );
        }

        return list1;
    }

    protected PandaSendMsgExcelVO pandaSendMsgDOToPandaSendMsgExcelVO(PandaSendMsgDO pandaSendMsgDO) {
        if ( pandaSendMsgDO == null ) {
            return null;
        }

        PandaSendMsgExcelVO pandaSendMsgExcelVO = new PandaSendMsgExcelVO();

        pandaSendMsgExcelVO.setId( pandaSendMsgDO.getId() );
        pandaSendMsgExcelVO.setCreateTime( pandaSendMsgDO.getCreateTime() );
        pandaSendMsgExcelVO.setType( pandaSendMsgDO.getType() );
        pandaSendMsgExcelVO.setContent( pandaSendMsgDO.getContent() );
        pandaSendMsgExcelVO.setStatus( pandaSendMsgDO.getStatus() );
        pandaSendMsgExcelVO.setSendTime( pandaSendMsgDO.getSendTime() );
        pandaSendMsgExcelVO.setToContactId( pandaSendMsgDO.getToContactId() );
        pandaSendMsgExcelVO.setFromUserId( pandaSendMsgDO.getFromUserId() );

        return pandaSendMsgExcelVO;
    }
}
