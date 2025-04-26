package com.pandahis.dashboard.modules.system.service.notice.impl;

import com.pandahis.dashboard.common.exception.util.ServiceExceptionUtil;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.security.core.util.SecurityFrameworkUtils;
import com.pandahis.dashboard.modules.system.controller.notice.vo.SysNoticeCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.notice.vo.SysNoticePageReqVO;
import com.pandahis.dashboard.modules.system.controller.notice.vo.SysNoticeUpdateReqVO;
import com.pandahis.dashboard.modules.system.convert.notice.SysNoticeConvert;
import com.pandahis.dashboard.modules.system.dal.dataobject.notice.SysNoticeDO;
import com.pandahis.dashboard.modules.system.dal.mysql.notice.SysNoticeMapper;
import com.pandahis.dashboard.modules.system.enums.SysErrorCodeConstants;
import com.pandahis.dashboard.modules.system.service.notice.SysNoticeService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 通知公告 Service 实现类
 *
 * @author 源码乐园
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {

    @Resource
    private SysNoticeMapper noticeMapper;

    @Override
    public Long createNotice(SysNoticeCreateReqVO reqVO) {
        SysNoticeDO notice = SysNoticeConvert.INSTANCE.convert(reqVO);
        notice.setReaded("");
        noticeMapper.insert(notice);
        return notice.getId();
    }

    @Override
    public void updateNotice(SysNoticeUpdateReqVO reqVO) {
        // 校验是否存在
        this.checkNoticeExists(reqVO.getId());
        // 更新通知公告
        SysNoticeDO updateObj = SysNoticeConvert.INSTANCE.convert(reqVO);
        noticeMapper.updateById(updateObj);
    }

    @Override
    public void deleteNotice(Long id) {
        // 校验是否存在
        this.checkNoticeExists(id);
        // 删除通知公告
        noticeMapper.deleteById(id);
    }

    @Override
    public PageResult<SysNoticeDO> pageNotices(SysNoticePageReqVO reqVO) {
        //获取尚未已读的通知信息


        if(StringUtils.isNotEmpty(reqVO.getIsfront())){
            Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
            reqVO.setReaded(String.valueOf(loginUserId));
        }
        IPage page=new Page();
        page.setCurrent(reqVO.getPageNo());
        page.setSize(reqVO.getPageSize());

        IPage<SysNoticeDO> result11 = noticeMapper.pageNotices(page, reqVO);
        PageResult result=new PageResult();
        result.setTotal(result11.getTotal());

        result.setList(result11.getRecords());
        return result;
    }

    @Override
    public SysNoticeDO getNotice(Long id) {
        return noticeMapper.selectById(id);
    }

    @VisibleForTesting
    public void checkNoticeExists(Long id) {
        if (id == null) {
            return;
        }
        SysNoticeDO notice = noticeMapper.selectById(id);
        if (notice == null) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.NOTICE_NOT_FOUND);
        }
    }

    @Override
    public BaseMapper<SysNoticeDO> getRepository() {
        return   noticeMapper;
    }
}
