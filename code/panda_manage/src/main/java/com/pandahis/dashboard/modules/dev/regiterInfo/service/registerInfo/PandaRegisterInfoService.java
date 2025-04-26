package com.pandahis.dashboard.modules.dev.regiterInfo.service.registerInfo;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoCreateReqVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoExportReqVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoPageReqVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoUpdateReqVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.dal.dataobject.registerInfo.PandaRegisterInfoDO;

import java.util.*;
import javax.validation.*;

/**
 * 挂号表 Service 接口
 *
 * @author 源码乐园
 */
public interface PandaRegisterInfoService {

    /**
     * 创建挂号表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRegisterInfo(@Valid PandaRegisterInfoCreateReqVO createReqVO);

    /**
     * 更新挂号表
     *
     * @param updateReqVO 更新信息
     */
    void updateRegisterInfo(@Valid PandaRegisterInfoUpdateReqVO updateReqVO);

    /**
     * 删除挂号表
     *
     * @param id 编号
     */
    void deleteRegisterInfo(Long id);

    /**
     * 获得挂号表
     *
     * @param id 编号
     * @return 挂号表
     */
    PandaRegisterInfoDO getRegisterInfo(Long id);

    /**
     * 获得挂号表列表
     *
     * @param ids 编号
     * @return 挂号表列表
     */
    List<PandaRegisterInfoDO> getRegisterInfoList(Collection<Long> ids);

    /**
     * 获得挂号表分页
     *
     * @param pageReqVO 分页查询
     * @return 挂号表分页
     */
    PageResult<PandaRegisterInfoDO> getRegisterInfoPage(PandaRegisterInfoPageReqVO pageReqVO);

    /**
     * 获得挂号表列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 挂号表分页
     */
    List<PandaRegisterInfoDO> getRegisterInfoList(PandaRegisterInfoExportReqVO exportReqVO);

}
