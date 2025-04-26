package com.pandahis.dashboard.modules.dev.sendMsg.service.sendMsg.impl;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.security.core.util.SecurityFrameworkUtils;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgExportReqVO;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgPageReqVO;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgUpdateReqVO;
import com.pandahis.dashboard.modules.dev.sendMsg.convert.sendMsg.PandaSendMsgConvert;
import com.pandahis.dashboard.modules.dev.sendMsg.dal.dataobject.sendMsg.PandaSendMsgDO;
import com.pandahis.dashboard.modules.dev.sendMsg.dal.mysql.sendMsg.PandaSendMsgMapper;
import com.pandahis.dashboard.modules.dev.sendMsg.service.sendMsg.PandaSendMsgService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 发送消息表 Service 实现类
 *
 * @author 源码乐园
 */
@Service
@Validated
public class PandaSendMsgServiceImpl implements PandaSendMsgService {

    @Resource
    private PandaSendMsgMapper sendMsgMapper;

    @Override
    public Long createSendMsg(@Valid PandaSendMsgDO sendMsg) {
        // 插入

        sendMsg.setCreateTime(new Date());
        sendMsg.setCreator(SecurityFrameworkUtils.getLoginUser().getUsername());
        sendMsg.setSendTime(new Date());
        sendMsg.setDeleted(false);
        sendMsg.setUpdater(SecurityFrameworkUtils.getLoginUser().getUsername());
        sendMsg.setStatus("succeed");
        sendMsg.setFromUserId(sendMsg.getFromUser().getId().toString());
        sendMsg.setCreator(sendMsg.getUpdater());
        sendMsg.setUpdateTime(new Date());
        sendMsg.setReaded("N");
        sendMsgMapper.insert(sendMsg);

        //更新消息已阅读 更具
//        sendMsgMapper.updateReadedToUserIdAndFromUserId(sendMsg.getToContactId(),sendMsg.getFromUserId());
        // 返回
        return sendMsg.getId();
    }

    @Override
    public void updateSendMsg(PandaSendMsgUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateSendMsgExists(updateReqVO.getId());
        // 更新
        PandaSendMsgDO updateObj = PandaSendMsgConvert.INSTANCE.convert(updateReqVO);
        sendMsgMapper.updateById(updateObj);
    }

    @Override
    public void deleteSendMsg(Long id) {
        // 更新
        sendMsgMapper.deleteById(id);
    }

    private void validateSendMsgExists(Long id) {

    }

    @Override
    public PandaSendMsgDO getSendMsg(Long id) {
        return sendMsgMapper.selectById(id);
    }

    @Override
    public List<PandaSendMsgDO> getSendMsgList(Collection<Long> ids) {
        return sendMsgMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PandaSendMsgDO> getSendMsgPage(PandaSendMsgPageReqVO pageReqVO) {
        return sendMsgMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PandaSendMsgDO> getSendMsgList(PandaSendMsgExportReqVO exportReqVO) {
        return sendMsgMapper.selectList(exportReqVO);
    }

}
