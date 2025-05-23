package com.pandahis.dashboard.modules.infra.service.config.impl;

import com.pandahis.dashboard.common.exception.util.ServiceExceptionUtil;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.infra.controller.config.vo.InfConfigCreateReqVO;
import com.pandahis.dashboard.modules.infra.controller.config.vo.InfConfigExportReqVO;
import com.pandahis.dashboard.modules.infra.controller.config.vo.InfConfigPageReqVO;
import com.pandahis.dashboard.modules.infra.controller.config.vo.InfConfigUpdateReqVO;
import com.pandahis.dashboard.modules.infra.convert.config.InfConfigConvert;
import com.pandahis.dashboard.modules.infra.dal.dataobject.config.InfConfigDO;
import com.pandahis.dashboard.modules.infra.dal.mysql.config.InfConfigMapper;
import com.pandahis.dashboard.modules.infra.enums.InfErrorCodeConstants;
import com.pandahis.dashboard.modules.infra.enums.config.InfConfigTypeEnum;

import com.pandahis.dashboard.modules.infra.service.config.InfConfigService;
import com.google.common.annotations.VisibleForTesting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import java.util.List;

/**
 * 参数配置 Service 实现类
 */
@Service
@Slf4j
@Validated
public class InfConfigServiceImpl implements InfConfigService {

    @Resource
    private InfConfigMapper configMapper;



    @Override
    public Long createConfig(InfConfigCreateReqVO reqVO) {
        // 校验正确性
        checkCreateOrUpdate(null, reqVO.getKey());
        // 插入参数配置
        InfConfigDO config = InfConfigConvert.INSTANCE.convert(reqVO);
        config.setType(InfConfigTypeEnum.CUSTOM.getType());
        configMapper.insert(config);

        return config.getId();
    }

    @Override
    public void updateConfig(InfConfigUpdateReqVO reqVO) {
        // 校验正确性
        checkCreateOrUpdate(reqVO.getId(), null); // 不允许更新 key
        // 更新参数配置
        InfConfigDO updateObj = InfConfigConvert.INSTANCE.convert(reqVO);
        configMapper.updateById(updateObj);


    }

    @Override
    public void deleteConfig(Long id) {
        // 校验配置存在
        InfConfigDO config = checkConfigExists(id);
        // 内置配置，不允许删除
        if (InfConfigTypeEnum.SYSTEM.getType().equals(config.getType())) {
            throw ServiceExceptionUtil.exception(InfErrorCodeConstants.CONFIG_CAN_NOT_DELETE_SYSTEM_TYPE);
        }
        // 删除
        configMapper.deleteById(id);

    }

    @Override
    public InfConfigDO getConfig(Long id) {
        return configMapper.selectById(id);
    }

    @Override
    public InfConfigDO getConfigByKey(String key) {
        return configMapper.selectByKey(key);
    }

    @Override
    public PageResult<InfConfigDO> getConfigPage(InfConfigPageReqVO reqVO) {
        return configMapper.selectPage(reqVO);
    }

    @Override
    public List<InfConfigDO> getConfigList(InfConfigExportReqVO reqVO) {
        return configMapper.selectList(reqVO);
    }

    private void checkCreateOrUpdate(Long id, String key) {
        // 校验自己存在
        checkConfigExists(id);
        // 校验参数配置 key 的唯一性
        checkConfigKeyUnique(id, key);
    }

    @VisibleForTesting
    public InfConfigDO checkConfigExists(Long id) {
        if (id == null) {
            return null;
        }
        InfConfigDO config = configMapper.selectById(id);
        if (config == null) {
            throw ServiceExceptionUtil.exception(InfErrorCodeConstants.CONFIG_NOT_EXISTS);
        }
        return config;
    }

    @VisibleForTesting
    public void checkConfigKeyUnique(Long id, String key) {
        InfConfigDO config = configMapper.selectByKey(key);
        if (config == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的参数配置
        if (id == null) {
            throw ServiceExceptionUtil.exception(InfErrorCodeConstants.CONFIG_KEY_DUPLICATE);
        }
        if (!config.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(InfErrorCodeConstants.CONFIG_KEY_DUPLICATE);
        }
    }

}
