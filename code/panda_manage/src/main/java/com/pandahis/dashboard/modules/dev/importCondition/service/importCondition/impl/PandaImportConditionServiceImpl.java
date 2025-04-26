package com.pandahis.dashboard.modules.dev.importCondition.service.importCondition.impl;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionCreateReqVO;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionExportReqVO;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionPageReqVO;
import com.pandahis.dashboard.modules.dev.importCondition.controller.importCondition.vo.PandaImportConditionUpdateReqVO;
import com.pandahis.dashboard.modules.dev.importCondition.convert.importCondition.PandaImportConditionConvert;
import com.pandahis.dashboard.modules.dev.importCondition.dal.dataobject.importCondition.PandaImportConditionDO;
import com.pandahis.dashboard.modules.dev.importCondition.dal.mysql.importCondition.PandaImportConditionMapper;
import com.pandahis.dashboard.modules.dev.importCondition.service.importCondition.PandaImportConditionService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;



/**
 * 导医信息 Service 实现类
 *
 * @author 源码乐园
 */
@Service
@Validated
public class PandaImportConditionServiceImpl implements PandaImportConditionService {

    @Resource
    private PandaImportConditionMapper importConditionMapper;

    @Override
    public Long createImportCondition(PandaImportConditionCreateReqVO createReqVO) {
        // 插入
        PandaImportConditionDO importCondition = PandaImportConditionConvert.INSTANCE.convert(createReqVO);
        importConditionMapper.insert(importCondition);
        // 返回
        return importCondition.getId();
    }

    @Override
    public void updateImportCondition(PandaImportConditionUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateImportConditionExists(updateReqVO.getId());
        // 更新
        PandaImportConditionDO updateObj = PandaImportConditionConvert.INSTANCE.convert(updateReqVO);
        importConditionMapper.updateById(updateObj);
    }

    @Override
    public void deleteImportCondition(Long id) {
        // 校验存在
        this.validateImportConditionExists(id);
        // 更新
        importConditionMapper.deleteById(id);
    }

    private void validateImportConditionExists(Long id) {

    }

    @Override
    public PandaImportConditionDO getImportCondition(Long id) {
        return importConditionMapper.selectById(id);
    }

    @Override
    public List<PandaImportConditionDO> getImportConditionList(Collection<Long> ids) {
        return importConditionMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PandaImportConditionDO> getImportConditionPage(PandaImportConditionPageReqVO pageReqVO) {
        return importConditionMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PandaImportConditionDO> getImportConditionList(PandaImportConditionExportReqVO exportReqVO) {
        return importConditionMapper.selectList(exportReqVO);
    }

}
