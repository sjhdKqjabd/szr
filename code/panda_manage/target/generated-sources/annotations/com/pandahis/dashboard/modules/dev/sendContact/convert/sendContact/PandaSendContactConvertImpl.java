package com.pandahis.dashboard.modules.dev.sendContact.convert.sendContact;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactCreateReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactExcelVO;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactRespVO;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactUpdateReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.dal.dataobject.sendContact.PandaSendContactDO;
import com.pandahis.dashboard.modules.dev.sendContact.dal.dataobject.sendContact.PandaSendContactDO.PandaSendContactDOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:12+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class PandaSendContactConvertImpl implements PandaSendContactConvert {

    @Override
    public PandaSendContactDO convert(PandaSendContactCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaSendContactDOBuilder pandaSendContactDO = PandaSendContactDO.builder();

        pandaSendContactDO.displayName( bean.getDisplayName() );
        pandaSendContactDO.avatar( bean.getAvatar() );
        pandaSendContactDO.index( bean.getIndex() );
        pandaSendContactDO.unread( bean.getUnread() );
        pandaSendContactDO.lastSendTime( bean.getLastSendTime() );
        pandaSendContactDO.lastContent( bean.getLastContent() );

        return pandaSendContactDO.build();
    }

    @Override
    public PandaSendContactDO convert(PandaSendContactUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaSendContactDOBuilder pandaSendContactDO = PandaSendContactDO.builder();

        pandaSendContactDO.id( bean.getId() );
        pandaSendContactDO.displayName( bean.getDisplayName() );
        pandaSendContactDO.avatar( bean.getAvatar() );
        pandaSendContactDO.index( bean.getIndex() );
        pandaSendContactDO.unread( bean.getUnread() );
        pandaSendContactDO.lastSendTime( bean.getLastSendTime() );
        pandaSendContactDO.lastContent( bean.getLastContent() );

        return pandaSendContactDO.build();
    }

    @Override
    public PandaSendContactRespVO convert(PandaSendContactDO bean) {
        if ( bean == null ) {
            return null;
        }

        PandaSendContactRespVO pandaSendContactRespVO = new PandaSendContactRespVO();

        pandaSendContactRespVO.setDisplayName( bean.getDisplayName() );
        pandaSendContactRespVO.setAvatar( bean.getAvatar() );
        pandaSendContactRespVO.setIndex( bean.getIndex() );
        pandaSendContactRespVO.setUnread( bean.getUnread() );
        pandaSendContactRespVO.setLastSendTime( bean.getLastSendTime() );
        pandaSendContactRespVO.setLastContent( bean.getLastContent() );
        pandaSendContactRespVO.setId( bean.getId() );
        pandaSendContactRespVO.setCreateTime( bean.getCreateTime() );

        return pandaSendContactRespVO;
    }

    @Override
    public List<PandaSendContactRespVO> convertList(List<PandaSendContactDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PandaSendContactRespVO> list1 = new ArrayList<PandaSendContactRespVO>( list.size() );
        for ( PandaSendContactDO pandaSendContactDO : list ) {
            list1.add( convert( pandaSendContactDO ) );
        }

        return list1;
    }

    @Override
    public PageResult<PandaSendContactRespVO> convertPage(PageResult<PandaSendContactDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<PandaSendContactRespVO> pageResult = new PageResult<PandaSendContactRespVO>();

        pageResult.setList( convertList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public List<PandaSendContactExcelVO> convertList02(List<PandaSendContactDO> list) {
        if ( list == null ) {
            return null;
        }

        List<PandaSendContactExcelVO> list1 = new ArrayList<PandaSendContactExcelVO>( list.size() );
        for ( PandaSendContactDO pandaSendContactDO : list ) {
            list1.add( pandaSendContactDOToPandaSendContactExcelVO( pandaSendContactDO ) );
        }

        return list1;
    }

    protected PandaSendContactExcelVO pandaSendContactDOToPandaSendContactExcelVO(PandaSendContactDO pandaSendContactDO) {
        if ( pandaSendContactDO == null ) {
            return null;
        }

        PandaSendContactExcelVO pandaSendContactExcelVO = new PandaSendContactExcelVO();

        pandaSendContactExcelVO.setId( pandaSendContactDO.getId() );
        pandaSendContactExcelVO.setCreateTime( pandaSendContactDO.getCreateTime() );
        pandaSendContactExcelVO.setDisplayName( pandaSendContactDO.getDisplayName() );
        pandaSendContactExcelVO.setAvatar( pandaSendContactDO.getAvatar() );
        pandaSendContactExcelVO.setIndex( pandaSendContactDO.getIndex() );
        pandaSendContactExcelVO.setUnread( pandaSendContactDO.getUnread() );
        pandaSendContactExcelVO.setLastSendTime( pandaSendContactDO.getLastSendTime() );
        pandaSendContactExcelVO.setLastContent( pandaSendContactDO.getLastContent() );

        return pandaSendContactExcelVO;
    }
}
