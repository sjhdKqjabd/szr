package com.pandahis.dashboard.modules.dev.sendContact.service.sendContact.impl;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.security.core.util.SecurityFrameworkUtils;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactCreateReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactExportReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactPageReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactUpdateReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.convert.sendContact.PandaSendContactConvert;
import com.pandahis.dashboard.modules.dev.sendContact.dal.dataobject.sendContact.PandaSendContactDO;
import com.pandahis.dashboard.modules.dev.sendContact.dal.mysql.sendContact.PandaSendContactMapper;
import com.pandahis.dashboard.modules.dev.sendContact.service.sendContact.PandaSendContactService;
import com.pandahis.dashboard.modules.dev.sendMsg.dal.dataobject.sendMsg.PandaSendMsgDO;
import com.pandahis.dashboard.modules.dev.sendMsg.dal.mysql.sendMsg.PandaSendMsgMapper;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import com.pandahis.dashboard.modules.system.dal.mysql.user.SysUserMapper;
import com.pandahis.dashboard.util.collection.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 联系人表 Service 实现类
 *
 * @author 源码乐园
 */
@Service
@Validated
public class PandaSendContactServiceImpl implements PandaSendContactService {

    @Resource
    private PandaSendContactMapper sendContactMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private PandaSendMsgMapper pandaSendMsgMapper;


    @Override
    public Long createSendContact(PandaSendContactCreateReqVO createReqVO) {
        // 插入
        PandaSendContactDO sendContact = PandaSendContactConvert.INSTANCE.convert(createReqVO);
        sendContactMapper.insert(sendContact);
        //更新 to_user

        // 返回
        return sendContact.getId();
    }

    @Override
    public void updateSendContact(PandaSendContactUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateSendContactExists(updateReqVO.getId());
        // 更新
        PandaSendContactDO updateObj = PandaSendContactConvert.INSTANCE.convert(updateReqVO);
        sendContactMapper.updateById(updateObj);
    }

    @Override
    public void deleteSendContact(Long id) {
        // 校验存在
        this.validateSendContactExists(id);
        // 更新
        sendContactMapper.deleteById(id);
    }

    private void validateSendContactExists(Long id) {

    }

    @Override
    public PandaSendContactDO getSendContact(Long id) {
        return sendContactMapper.selectById(id);
    }

    @Override
    public  synchronized List<PandaSendContactDO> getSendContactList(SysUserDO sysUserDO) {

        List<PandaSendContactDO> pandaSendContactDOS=new ArrayList<>();
        //根据接收人Id 获取未读 发送人信息 发送人发送的信息
        //获取发送人信息
        List<PandaSendMsgDO>  msgDOS = pandaSendMsgMapper.selectMsgByToContactGroupByToContactId(sysUserDO);

        for (PandaSendMsgDO msgDO : msgDOS) {
            List<PandaSendMsgDO>  unreadMsgs = pandaSendMsgMapper.getUnredMsgsByToContactId(sysUserDO);
            if(CollectionUtils.isAnyEmpty(unreadMsgs)){
                unreadMsgs=new ArrayList<>();
            }else{
                //构造未读联系人 消息信息
                for (PandaSendMsgDO unreadMsg : unreadMsgs) {
                    String fromUserId = unreadMsg.getFromUserId();
                    SysUserDO sysUserDO1 = sysUserMapper.selectById(fromUserId);
                    unreadMsg.setFromUser(sysUserDO1);
                }
            }

            //set unread siez
            int size = unreadMsgs.stream().map((each) -> {
                return each.getToContactId().equals(sysUserDO.getId())
                      && "N".equals(each.getReaded());
            }).filter((each)->{return each==true;}).collect(Collectors.toList()).size();

            //构造未读联系人相关信息
            PandaSendContactDO contactDO=new PandaSendContactDO();
            //前段传过来查询的用户Id
            String frontUserId = sysUserDO.getId().toString();
            String fromUserId = msgDO.getFromUserId();
            String toContactId = msgDO.getToContactId();
            SysUserDO  sender=null;
            // fromuserid= frontuserid  则获取 toContactId
            // tocatactid = frontuserid 获取获取 fromuserid
            if(fromUserId.equals(frontUserId)){
                  sender = sysUserMapper.selectById(toContactId);
            }else if(toContactId.equals(frontUserId)){
                sender = sysUserMapper.selectById(fromUserId);
                //更新 来自他人发送给自己的消息为已读
                pandaSendMsgMapper.updateSelectUnReadFromUserIdAndToUserId(fromUserId,toContactId);
            }

            contactDO.setDisplayName(sender.getUsername());
            contactDO.setId(sender.getId());
            contactDO.setIndex(sender.getUsername());
            contactDO.setAvatar(sender.getAvatar());
            contactDO.setLastSendTime(msgDO.getSendTime());
            contactDO.setLastContent(msgDO.getContent());
            contactDO.setUnread(String.valueOf(size));
            contactDO.setSendMsgDOS(unreadMsgs);
            int size1 = pandaSendContactDOS.stream().filter((each) ->
            {
                return each.getDisplayName().equals(contactDO.getDisplayName());
            }).collect(Collectors.toList()).size();
            if(size1==0){
                pandaSendContactDOS.add(contactDO);
            }

        }

        return pandaSendContactDOS;
    }

    @Override
    public PageResult<PandaSendContactDO> getSendContactPage(PandaSendContactPageReqVO pageReqVO) {
        return sendContactMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PandaSendContactDO> getSendContactList(PandaSendContactExportReqVO exportReqVO) {
        return sendContactMapper.selectList(exportReqVO);
    }

    @Override
    public List<PandaSendMsgDO> getSendMsgWithEachOther(PandaSendMsgDO pandaSendMsgDO) {

        //查询双发发送的消息
        List<PandaSendMsgDO> msgDOS = pandaSendMsgMapper.getSendMsgWithEachOther(pandaSendMsgDO);
        for (PandaSendMsgDO msgDO : msgDOS) {
            String fromUserId = msgDO.getFromUserId();
            //如果该条消息的 发送人 是当前聊天的发送人 则 该消息是自己的消息
            if(fromUserId.equals(pandaSendMsgDO.getFromUserId())){
                SysUserDO sysUserDO = sysUserMapper.selectById(fromUserId);
                msgDO.setToContactId(msgDO.getToContactId());
                sysUserDO.setDisplayName(sysUserDO.getUsername());
                msgDO.setFromUser(sysUserDO);
            }
            if(fromUserId.equals(pandaSendMsgDO.getToContactId())){
                SysUserDO sysUserDO = sysUserMapper.selectById(fromUserId);
                msgDO.setToContactId(msgDO.getToContactId());
                sysUserDO.setDisplayName(sysUserDO.getUsername());
                msgDO.setFromUser(sysUserDO);
            }
        }
//        //设置查询到的消息都是已读的
//        updateMsgReaded(msgDOS);
        return msgDOS;
    }

    public void updateMsgReaded(List<PandaSendMsgDO> msgDOS){
        //设置查询到的消息都是已读的
        List needReadeds=new ArrayList();
        for (PandaSendMsgDO msgDO : msgDOS) {
            if("N".equals(msgDO.getReaded())){
                needReadeds.add(msgDO);
            }
        }
        if(!CollectionUtils.isAnyEmpty(needReadeds)){
            //更新已读消息是 发送给自己的
            List selfs=new ArrayList();
            Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
            for (PandaSendMsgDO needReaded :( List<PandaSendMsgDO> )needReadeds) {
                if(needReaded.getToContactId().equals(loginUserId.toString())){
                    selfs.add(needReaded);
                }
            }
            if(!CollectionUtils.isAnyEmpty(selfs)){
                pandaSendMsgMapper.updateAllReaded(selfs);
            }
//            pandaSendMsgMapper.updateAllReaded(needReadeds);

        }
    }

    public List<PandaSendMsgDO> getSendMsgWithEachOtherUnreaded(PandaSendMsgDO pandaSendMsgDO) {
        //查询双发发送的消息
//        List<PandaSendMsgDO> msgDOS = pandaSendMsgMapper.getSendMsgWithEachOtherUnreaded(pandaSendMsgDO);
        List<PandaSendMsgDO> msgDOS = pandaSendMsgMapper.selectUnReadFromUserIdAndToUserId(pandaSendMsgDO.getFromUserId(),pandaSendMsgDO.getToContactId());
        for (PandaSendMsgDO msgDO : msgDOS) {
            SysUserDO sysUserDO = sysUserMapper.selectById(pandaSendMsgDO.getToContactId());
            msgDO.setToContactId(msgDO.getToContactId());
            sysUserDO.setDisplayName(sysUserDO.getUsername());
            msgDO.setFromUser(sysUserDO);
        }
        //设置查询到的消息都是已读的
        pandaSendMsgMapper.updateSelectUnReadFromUserIdAndToUserId(pandaSendMsgDO.getFromUserId(),pandaSendMsgDO.getToContactId());
        return msgDOS;
    }

//    public List<PandaSendMsgDO> getSendMsgWithEachOtherUnreaded(PandaSendMsgDO pandaSendMsgDO) {
//        //查询双发发送的消息
//        List<PandaSendMsgDO> msgDOS = pandaSendMsgMapper.getSendMsgWithEachOtherUnreaded(pandaSendMsgDO);
//        for (PandaSendMsgDO msgDO : msgDOS) {
//            String fromUserId = msgDO.getFromUserId();
//            String toUserId = msgDO.getToContactId();
//            //如果该条消息的 发送人 是当前聊天的发送人 则 该消息是自己的消息
//            if(fromUserId.equals(pandaSendMsgDO.getFromUserId())){
//                SysUserDO sysUserDO = sysUserMapper.selectById(fromUserId);
//                msgDO.setToContactId(msgDO.getToContactId());
//                sysUserDO.setDisplayName(sysUserDO.getUsername());
//                msgDO.setFromUser(sysUserDO);
//            }
//            if(fromUserId.equals(pandaSendMsgDO.getToContactId())){
//                SysUserDO sysUserDO = sysUserMapper.selectById(fromUserId);
//                msgDO.setToContactId(msgDO.getToContactId());
//                sysUserDO.setDisplayName(sysUserDO.getUsername());
//                msgDO.setFromUser(sysUserDO);
//            }
//        }
//        //设置查询到的消息都是已读的
//        updateMsgReaded(msgDOS);
//        return msgDOS;
//    }
}
