package com.pandahis.dashboard.modules.dev.sendMsg.convert.sendMsg;

import java.util.*;


import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgCreateReqVO;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgExcelVO;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgRespVO;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgUpdateReqVO;
import com.pandahis.dashboard.modules.dev.sendMsg.dal.dataobject.sendMsg.PandaSendMsgDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 发送消息表 Convert
 *
 * @author 源码乐园
 */
@Mapper
public interface PandaSendMsgConvert {

    PandaSendMsgConvert INSTANCE = Mappers.getMapper(PandaSendMsgConvert.class);

    PandaSendMsgDO convert(PandaSendMsgCreateReqVO bean);

    PandaSendMsgDO convert(PandaSendMsgUpdateReqVO bean);

    PandaSendMsgRespVO convert(PandaSendMsgDO bean);

    List<PandaSendMsgRespVO> convertList(List<PandaSendMsgDO> list);

    PageResult<PandaSendMsgRespVO> convertPage(PageResult<PandaSendMsgDO> page);

    List<PandaSendMsgExcelVO> convertList02(List<PandaSendMsgDO> list);

}
