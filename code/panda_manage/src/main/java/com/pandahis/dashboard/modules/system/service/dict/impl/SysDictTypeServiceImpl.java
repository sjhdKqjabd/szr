package com.pandahis.dashboard.modules.system.service.dict.impl;

import com.pandahis.dashboard.common.exception.util.ServiceExceptionUtil;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.dal.dataobject.dict.SysDictTypeDO;
import com.pandahis.dashboard.modules.system.dal.mysql.dict.SysDictTypeMapper;
import com.pandahis.dashboard.modules.system.enums.SysErrorCodeConstants;
import com.pandahis.dashboard.modules.system.service.dict.SysDictDataService;
import com.pandahis.dashboard.modules.system.service.dict.SysDictTypeService;
import com.pandahis.dashboard.modules.system.controller.dict.vo.type.SysDictTypeCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.dict.vo.type.SysDictTypeExportReqVO;
import com.pandahis.dashboard.modules.system.controller.dict.vo.type.SysDictTypePageReqVO;
import com.pandahis.dashboard.modules.system.controller.dict.vo.type.SysDictTypeUpdateReqVO;
import com.pandahis.dashboard.modules.system.convert.dict.SysDictTypeConvert;
import com.google.common.annotations.VisibleForTesting;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.pandahis.dashboard.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 字典类型 Service 实现类
 *
 * @author 源码乐园
 */
@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {

    @Resource
    private SysDictDataService dictDataService;

    @Resource
    private SysDictTypeMapper dictTypeMapper;

    @Override
    public PageResult<SysDictTypeDO> getDictTypePage(SysDictTypePageReqVO reqVO) {
        return dictTypeMapper.selectPage(reqVO);
    }

    @Override
    public List<SysDictTypeDO> getDictTypeList(SysDictTypeExportReqVO reqVO) {
        return dictTypeMapper.selectList(reqVO);
    }

    @Override
    public SysDictTypeDO getDictType(Long id) {
        return dictTypeMapper.selectById(id);
    }

    @Override
    public SysDictTypeDO getDictType(String type) {
        return dictTypeMapper.selectByType(type);
    }

    @Override
    public Long createDictType(SysDictTypeCreateReqVO reqVO) {
        // 校验正确性
        this.checkCreateOrUpdate(null, reqVO.getName(), reqVO.getType());
        // 插入字典类型
        SysDictTypeDO dictType = SysDictTypeConvert.INSTANCE.convert(reqVO);
        dictTypeMapper.insert(dictType);
        return dictType.getId();
    }

    @Override
    public void updateDictType(SysDictTypeUpdateReqVO reqVO) {
        // 校验正确性
        this.checkCreateOrUpdate(reqVO.getId(), reqVO.getName(), null);
        // 更新字典类型
        SysDictTypeDO updateObj = SysDictTypeConvert.INSTANCE.convert(reqVO);
        dictTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteDictType(Long id) {
        // 校验是否存在
        SysDictTypeDO dictType = this.checkDictTypeExists(id);
        // 校验是否有字典数据
        if (dictDataService.countByDictType(dictType.getType()) > 0) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DICT_TYPE_HAS_CHILDREN);
        }
        // 删除字典类型
        dictTypeMapper.deleteById(id);
    }

    @Override
    public List<SysDictTypeDO> getDictTypeList() {
        return dictTypeMapper.selectList();
    }

    private void checkCreateOrUpdate(Long id, String name, String type) {
        // 校验自己存在
        checkDictTypeExists(id);
        // 校验字典类型的名字的唯一性
        checkDictTypeNameUnique(id, name);
        // 校验字典类型的类型的唯一性
        checkDictTypeUnique(id, type);
    }

    @VisibleForTesting
    public void checkDictTypeNameUnique(Long id, String name) {
        SysDictTypeDO dictType = dictTypeMapper.selectByName(name);
        if (dictType == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典类型
        if (id == null) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DICT_TYPE_NAME_DUPLICATE);
        }
        if (!dictType.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DICT_TYPE_NAME_DUPLICATE);
        }
    }

    @VisibleForTesting
    public void checkDictTypeUnique(Long id, String type) {
        SysDictTypeDO dictType = dictTypeMapper.selectByType(type);
        if (dictType == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的字典类型
        if (id == null) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DICT_TYPE_TYPE_DUPLICATE);
        }
        if (!dictType.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DICT_TYPE_TYPE_DUPLICATE);
        }
    }

    @VisibleForTesting
    public SysDictTypeDO checkDictTypeExists(Long id) {
        if (id == null) {
            return null;
        }
        SysDictTypeDO dictType = dictTypeMapper.selectById(id);
        if (dictType == null) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DICT_TYPE_NOT_EXISTS);
        }
        return dictType;
    }

}
