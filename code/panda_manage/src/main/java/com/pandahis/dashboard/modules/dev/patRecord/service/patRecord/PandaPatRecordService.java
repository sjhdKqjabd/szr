package com.pandahis.dashboard.modules.dev.patRecord.service.patRecord;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordCreateReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordExportReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordPageReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordUpdateReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.dal.dataobject.patRecord.PandaPatRecordDO;

import java.util.*;
import javax.validation.*;


/**
 * 就诊 Service 接口
 *
 * @author 源码乐园
 */
public interface PandaPatRecordService {

    /**
     * 创建就诊
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPatRecord(@Valid PandaPatRecordCreateReqVO createReqVO);

    /**
     * 更新就诊
     *
     * @param updateReqVO 更新信息
     */
    void updatePatRecord(@Valid PandaPatRecordUpdateReqVO updateReqVO);

    /**
     * 删除就诊
     *
     * @param id 编号
     */
    void deletePatRecord(Long id);

    /**
     * 获得就诊
     *
     * @param id 编号
     * @return 就诊
     */
    PandaPatRecordDO getPatRecord(Long id);

    /**
     * 获得就诊列表
     *
     * @param ids 编号
     * @return 就诊列表
     */
    List<PandaPatRecordDO> getPatRecordList(Collection<Long> ids);

    /**
     * 获得就诊分页
     *
     * @param pageReqVO 分页查询
     * @return 就诊分页
     */
    PageResult<PandaPatRecordDO> getPatRecordPage(PandaPatRecordPageReqVO pageReqVO);

    /**
     * 获得就诊列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 就诊分页
     */
    List<PandaPatRecordDO> getPatRecordList(PandaPatRecordExportReqVO exportReqVO);

}
