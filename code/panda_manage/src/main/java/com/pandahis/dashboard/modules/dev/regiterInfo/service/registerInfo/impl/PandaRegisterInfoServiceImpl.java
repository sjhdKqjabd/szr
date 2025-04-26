package com.pandahis.dashboard.modules.dev.regiterInfo.service.registerInfo.impl;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoCreateReqVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoExportReqVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoPageReqVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.controller.registerInfo.vo.PandaRegisterInfoUpdateReqVO;
import com.pandahis.dashboard.modules.dev.regiterInfo.convert.registerInfo.PandaRegisterInfoConvert;
import com.pandahis.dashboard.modules.dev.regiterInfo.dal.dataobject.registerInfo.PandaRegisterInfoDO;
import com.pandahis.dashboard.modules.dev.regiterInfo.dal.mysql.registerInfo.PandaRegisterInfoMapper;
import com.pandahis.dashboard.modules.dev.regiterInfo.service.registerInfo.PandaRegisterInfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;



/**
 * 挂号表 Service 实现类
 *
 * @author 源码乐园
 */
@Service
@Validated
public class PandaRegisterInfoServiceImpl implements PandaRegisterInfoService {

    @Resource
    private PandaRegisterInfoMapper registerInfoMapper;

    @Override
    public Long createRegisterInfo(PandaRegisterInfoCreateReqVO createReqVO) {
        // 插入
        PandaRegisterInfoDO registerInfo = PandaRegisterInfoConvert.INSTANCE.convert(createReqVO);
        registerInfoMapper.insert(registerInfo);
        // 返回
        return registerInfo.getId();
    }

    @Override
    public void updateRegisterInfo(PandaRegisterInfoUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateRegisterInfoExists(updateReqVO.getId());
        // 更新
        PandaRegisterInfoDO updateObj = PandaRegisterInfoConvert.INSTANCE.convert(updateReqVO);
        registerInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteRegisterInfo(Long id) {
        // 校验存在
        this.validateRegisterInfoExists(id);
        // 更新
        registerInfoMapper.deleteById(id);
    }

    private void validateRegisterInfoExists(Long id) {

    }

    @Override
    public PandaRegisterInfoDO getRegisterInfo(Long id) {
        return registerInfoMapper.selectById(id);
    }

    @Override
    public List<PandaRegisterInfoDO> getRegisterInfoList(Collection<Long> ids) {
        return registerInfoMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PandaRegisterInfoDO> getRegisterInfoPage(PandaRegisterInfoPageReqVO pageReqVO) {
        return registerInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PandaRegisterInfoDO> getRegisterInfoList(PandaRegisterInfoExportReqVO exportReqVO) {
        return registerInfoMapper.selectList(exportReqVO);
    }

}
