package com.pandahis.dashboard.modules.dev.sendMsg.dal.mysql.sendMsg;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.mybatis.core.mapper.BaseMapperX;
import com.pandahis.dashboard.framework.mybatis.core.query.QueryWrapperX;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgExportReqVO;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.PandaSendMsgPageReqVO;
import com.pandahis.dashboard.modules.dev.sendMsg.dal.dataobject.sendMsg.PandaSendMsgDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 发送消息表 Mapper
 *
 * @author 源码乐园
 */
@Mapper
public interface PandaSendMsgMapper extends BaseMapperX<PandaSendMsgDO> {

    default PageResult<PandaSendMsgDO> selectPage(PandaSendMsgPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<PandaSendMsgDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .eqIfPresent("type", reqVO.getType())
                .eqIfPresent("content", reqVO.getContent())
                .eqIfPresent("status", reqVO.getStatus())
                .betweenIfPresent("send_time", reqVO.getBeginSendTime(), reqVO.getEndSendTime())
                .eqIfPresent("to_contact_id", reqVO.getToContactId())
                .eqIfPresent("from_user_id", reqVO.getFromUserId())
                .orderByDesc("id")        );
    }

    default List<PandaSendMsgDO> selectList(PandaSendMsgExportReqVO reqVO) {
        return selectList(new QueryWrapperX<PandaSendMsgDO>()
                .betweenIfPresent("create_time", reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .eqIfPresent("type", reqVO.getType())
                .eqIfPresent("content", reqVO.getContent())
                .eqIfPresent("status", reqVO.getStatus())
                .betweenIfPresent("send_time", reqVO.getBeginSendTime(), reqVO.getEndSendTime())
                .eqIfPresent("to_contact_id", reqVO.getToContactId())
                .eqIfPresent("from_user_id", reqVO.getFromUserId())
                .orderByDesc("id")        );
    }

    default  List<PandaSendMsgDO> selectMsgByToContactGroupByToContactId(SysUserDO sysUserDO){
        return  selectList(new QueryWrapperX<PandaSendMsgDO>()
                .eq("deleted",false)
                .eq("to_contact_id",sysUserDO.getId())
                  .or()
                  .eq("from_user_id",sysUserDO.getId())
                .groupBy("to_contact_id")
                .orderByDesc("send_time")
        );
    };

    default  List<PandaSendMsgDO> getUnredMsgsByToContactId(SysUserDO sysUserDO){
        return selectList(new QueryWrapperX<PandaSendMsgDO>()
              .eq("deleted",false)
              .eq("to_contact_id",sysUserDO.getId())
              .orderByDesc("send_time")
        );
    };

    List<PandaSendMsgDO> getSendMsgWithEachOther(@Param("pandaSendMsgDO") PandaSendMsgDO pandaSendMsgDO);
    List<PandaSendMsgDO> getSendMsgWithEachOtherUnreaded(@Param("pandaSendMsgDO") PandaSendMsgDO pandaSendMsgDO);

    Integer updateAllReaded(@Param("msgDos") List<PandaSendMsgDO> msgDOS);

    Integer updateReadedToUserIdAndFromUserId(@Param("toContactId") String toContactId,
                                              @Param("fromUserId") String fromUserId);

    List<PandaSendMsgDO> selectUnReadFromUserIdAndToUserId(@Param("fromUserId")String fromUserId, @Param("toContactId")String toContactId);

    Integer updateSelectUnReadFromUserIdAndToUserId(@Param("fromUserId") String fromUserId,@Param("toContactId") String toContactId);

}
