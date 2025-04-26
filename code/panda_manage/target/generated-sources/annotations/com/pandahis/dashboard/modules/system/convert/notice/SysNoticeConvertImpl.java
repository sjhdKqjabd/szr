package com.pandahis.dashboard.modules.system.convert.notice;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.controller.notice.vo.SysNoticeCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.notice.vo.SysNoticeRespVO;
import com.pandahis.dashboard.modules.system.controller.notice.vo.SysNoticeUpdateReqVO;
import com.pandahis.dashboard.modules.system.dal.dataobject.notice.SysNoticeDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-09T17:15:13+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_361 (Oracle Corporation)"
)
public class SysNoticeConvertImpl implements SysNoticeConvert {

    @Override
    public PageResult<SysNoticeRespVO> convertPage(PageResult<SysNoticeDO> page) {
        if ( page == null ) {
            return null;
        }

        PageResult<SysNoticeRespVO> pageResult = new PageResult<SysNoticeRespVO>();

        pageResult.setList( sysNoticeDOListToSysNoticeRespVOList( page.getList() ) );
        pageResult.setTotal( page.getTotal() );

        return pageResult;
    }

    @Override
    public SysNoticeRespVO convert(SysNoticeDO bean) {
        if ( bean == null ) {
            return null;
        }

        SysNoticeRespVO sysNoticeRespVO = new SysNoticeRespVO();

        sysNoticeRespVO.setTitle( bean.getTitle() );
        sysNoticeRespVO.setType( bean.getType() );
        sysNoticeRespVO.setContent( bean.getContent() );
        sysNoticeRespVO.setStatus( bean.getStatus() );
        sysNoticeRespVO.setReaded( bean.getReaded() );
        sysNoticeRespVO.setId( bean.getId() );
        sysNoticeRespVO.setCreateTime( bean.getCreateTime() );

        return sysNoticeRespVO;
    }

    @Override
    public SysNoticeDO convert(SysNoticeUpdateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysNoticeDO sysNoticeDO = new SysNoticeDO();

        sysNoticeDO.setId( bean.getId() );
        sysNoticeDO.setTitle( bean.getTitle() );
        sysNoticeDO.setType( bean.getType() );
        sysNoticeDO.setContent( bean.getContent() );
        sysNoticeDO.setStatus( bean.getStatus() );
        sysNoticeDO.setReaded( bean.getReaded() );

        return sysNoticeDO;
    }

    @Override
    public SysNoticeDO convert(SysNoticeCreateReqVO bean) {
        if ( bean == null ) {
            return null;
        }

        SysNoticeDO sysNoticeDO = new SysNoticeDO();

        sysNoticeDO.setTitle( bean.getTitle() );
        sysNoticeDO.setType( bean.getType() );
        sysNoticeDO.setContent( bean.getContent() );
        sysNoticeDO.setStatus( bean.getStatus() );
        sysNoticeDO.setReaded( bean.getReaded() );

        return sysNoticeDO;
    }

    protected List<SysNoticeRespVO> sysNoticeDOListToSysNoticeRespVOList(List<SysNoticeDO> list) {
        if ( list == null ) {
            return null;
        }

        List<SysNoticeRespVO> list1 = new ArrayList<SysNoticeRespVO>( list.size() );
        for ( SysNoticeDO sysNoticeDO : list ) {
            list1.add( convert( sysNoticeDO ) );
        }

        return list1;
    }
}
