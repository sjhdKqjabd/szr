package com.pandahis.dashboard.modules.dev.sendContact.service.sendContact;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactCreateReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactExportReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactPageReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.controller.sendContact.vo.PandaSendContactUpdateReqVO;
import com.pandahis.dashboard.modules.dev.sendContact.dal.dataobject.sendContact.PandaSendContactDO;
import com.pandahis.dashboard.modules.dev.sendMsg.dal.dataobject.sendMsg.PandaSendMsgDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;

import java.util.*;
import javax.validation.*;


/**
 * 联系人表 Service 接口
 *
 * @author 源码乐园
 */
public interface PandaSendContactService {

    /**
     * 创建联系人表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSendContact(@Valid PandaSendContactCreateReqVO createReqVO);

    /**
     * 更新联系人表
     *
     * @param updateReqVO 更新信息
     */
    void updateSendContact(@Valid PandaSendContactUpdateReqVO updateReqVO);

    /**
     * 删除联系人表
     *
     * @param id 编号
     */
    void deleteSendContact(Long id);

    /**
     * 获得联系人表
     *
     * @param id 编号
     * @return 联系人表
     */
    PandaSendContactDO getSendContact(Long id);

    /**
     * 获得联系人表列表
     *
     * @param sysUserDO 编号
     * @return 联系人表列表
     */
    List<PandaSendContactDO> getSendContactList(SysUserDO sysUserDO);

    /**
     * 获得联系人表分页
     *
     * @param pageReqVO 分页查询
     * @return 联系人表分页
     */
    PageResult<PandaSendContactDO> getSendContactPage(PandaSendContactPageReqVO pageReqVO);

    /**
     * 获得联系人表列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 联系人表分页
     */
    List<PandaSendContactDO> getSendContactList(PandaSendContactExportReqVO exportReqVO);

    List<PandaSendMsgDO> getSendMsgWithEachOther(PandaSendMsgDO pandaSendMsgDO);

    List<PandaSendMsgDO> getSendMsgWithEachOtherUnreaded(PandaSendMsgDO pandaSendMsgDO);



}
