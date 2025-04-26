package com.pandahis.dashboard.modules.dev.sendMsg.service.sendMsg;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgExportReqVO;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgPageReqVO;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgUpdateReqVO;
import com.pandahis.dashboard.modules.dev.sendMsg.dal.dataobject.sendMsg.PandaSendMsgDO;

import java.util.*;
import javax.validation.*;


/**
 * 发送消息表 Service 接口
 *
 * @author 源码乐园
 */
public interface PandaSendMsgService {

    /**
     * 创建发送消息表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSendMsg(@Valid PandaSendMsgDO createReqVO);

    /**
     * 更新发送消息表
     *
     * @param updateReqVO 更新信息
     */
    void updateSendMsg(@Valid PandaSendMsgUpdateReqVO updateReqVO);

    /**
     * 删除发送消息表
     *
     * @param id 编号
     */
    void deleteSendMsg(Long id);

    /**
     * 获得发送消息表
     *
     * @param id 编号
     * @return 发送消息表
     */
    PandaSendMsgDO getSendMsg(Long id);

    /**
     * 获得发送消息表列表
     *
     * @param ids 编号
     * @return 发送消息表列表
     */
    List<PandaSendMsgDO> getSendMsgList(Collection<Long> ids);

    /**
     * 获得发送消息表分页
     *
     * @param pageReqVO 分页查询
     * @return 发送消息表分页
     */
    PageResult<PandaSendMsgDO> getSendMsgPage(PandaSendMsgPageReqVO pageReqVO);

    /**
     * 获得发送消息表列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 发送消息表分页
     */
    List<PandaSendMsgDO> getSendMsgList(PandaSendMsgExportReqVO exportReqVO);

}
