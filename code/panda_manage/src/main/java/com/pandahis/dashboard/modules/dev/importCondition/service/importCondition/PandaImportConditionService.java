package com.pandahis.dashboard.modules.dev.importCondition.service.importCondition;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionCreateReqVO;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionExportReqVO;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionPageReqVO;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionUpdateReqVO;
import com.pandahis.dashboard.modules.dev.importCondition.dal.dataobject.importCondition.PandaImportConditionDO;

import java.util.*;
import javax.validation.*;


/**
 * 导医信息 Service 接口
 *
 * @author 源码乐园
 */
public interface PandaImportConditionService {

    /**
     * 创建导医信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createImportCondition(@Valid PandaImportConditionCreateReqVO createReqVO);

    /**
     * 更新导医信息
     *
     * @param updateReqVO 更新信息
     */
    void updateImportCondition(@Valid PandaImportConditionUpdateReqVO updateReqVO);

    /**
     * 删除导医信息
     *
     * @param id 编号
     */
    void deleteImportCondition(Long id);

    /**
     * 获得导医信息
     *
     * @param id 编号
     * @return 导医信息
     */
    PandaImportConditionDO getImportCondition(Long id);

    /**
     * 获得导医信息列表
     *
     * @param ids 编号
     * @return 导医信息列表
     */
    List<PandaImportConditionDO> getImportConditionList(Collection<Long> ids);

    /**
     * 获得导医信息分页
     *
     * @param pageReqVO 分页查询
     * @return 导医信息分页
     */
    PageResult<PandaImportConditionDO> getImportConditionPage(PandaImportConditionPageReqVO pageReqVO);

    /**
     * 获得导医信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 导医信息分页
     */
    List<PandaImportConditionDO> getImportConditionList(PandaImportConditionExportReqVO exportReqVO);

}
