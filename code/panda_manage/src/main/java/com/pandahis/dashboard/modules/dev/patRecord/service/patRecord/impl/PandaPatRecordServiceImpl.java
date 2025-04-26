package com.pandahis.dashboard.modules.dev.patRecord.service.patRecord.impl;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordCreateReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordExportReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordPageReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.controller.patRecord.vo.PandaPatRecordUpdateReqVO;
import com.pandahis.dashboard.modules.dev.patRecord.convert.patRecord.PandaPatRecordConvert;
import com.pandahis.dashboard.modules.dev.patRecord.dal.dataobject.patRecord.PandaPatRecordDO;
import com.pandahis.dashboard.modules.dev.patRecord.dal.mysql.patRecord.PandaPatRecordMapper;
import com.pandahis.dashboard.modules.dev.patRecord.service.patRecord.PandaPatRecordService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;


/**
 * 就诊 Service 实现类
 *
 * @author 源码乐园
 */
@Service
@Validated
public class PandaPatRecordServiceImpl implements PandaPatRecordService {

    @Resource
    private PandaPatRecordMapper patRecordMapper;

    @Override
    public Long createPatRecord(PandaPatRecordCreateReqVO createReqVO) {
        // 插入
        PandaPatRecordDO patRecord = PandaPatRecordConvert.INSTANCE.convert(createReqVO);
        patRecordMapper.insert(patRecord);
        // 返回
        return patRecord.getId();
    }

    @Override
    public void updatePatRecord(PandaPatRecordUpdateReqVO updateReqVO) {
        // 校验存在
        this.validatePatRecordExists(updateReqVO.getId());
        // 更新
        PandaPatRecordDO updateObj = PandaPatRecordConvert.INSTANCE.convert(updateReqVO);
        patRecordMapper.updateById(updateObj);
    }

    @Override
    public void deletePatRecord(Long id) {
        // 校验存在
        this.validatePatRecordExists(id);
        // 更新
        patRecordMapper.deleteById(id);
    }

    private void validatePatRecordExists(Long id) {

    }

    @Override
    public PandaPatRecordDO getPatRecord(Long id) {
        return patRecordMapper.selectById(id);
    }

    @Override
    public List<PandaPatRecordDO> getPatRecordList(Collection<Long> ids) {
        return patRecordMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PandaPatRecordDO> getPatRecordPage(PandaPatRecordPageReqVO pageReqVO) {
        return patRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PandaPatRecordDO> getPatRecordList(PandaPatRecordExportReqVO exportReqVO) {
        return patRecordMapper.selectList(exportReqVO);
    }

}
