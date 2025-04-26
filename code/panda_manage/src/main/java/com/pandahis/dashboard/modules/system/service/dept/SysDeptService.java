package com.pandahis.dashboard.modules.system.service.dept;

import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysDeptDO;
import com.pandahis.dashboard.util.collection.CollectionUtils;
import cn.hutool.core.collection.CollUtil;
import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptListReqVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptUpdateReqVO;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 科室 Service 接口
 *
 * @author 源码乐园
 */
public interface SysDeptService {

    /**
     * 初始化科室的本地缓存
     */
    void initLocalCache();

    /**
     * 创建科室
     *
     * @param reqVO 科室信息
     * @return 科室编号
     */
    Long createDept(SysDeptCreateReqVO reqVO);

    /**
     * 更新科室
     *
     * @param reqVO 科室信息
     */
    void updateDept(SysDeptUpdateReqVO reqVO);

    /**
     * 删除科室
     *
     * @param id 科室编号
     */
    void deleteDept(Long id);

    /**
     * 获得指定编号的科室列表
     *
     * @param ids 科室编号数组
     * @return 科室列表
     */
    List<SysDeptDO> getSimpleDepts(Collection<Long> ids);

    /**
     * 获得指定编号的科室 Map
     *
     * @param ids 科室编号数组
     * @return 科室 Map
     */
    default Map<Long, SysDeptDO> getDeptMap(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return Collections.emptyMap();
        }
        List<SysDeptDO> list = getSimpleDepts(ids);
        return CollectionUtils.convertMap(list, SysDeptDO::getId);
    }

    /**
     * 筛选科室列表
     *
     * @param reqVO 筛选条件请求 VO
     * @return 科室列表
     */
    List<SysDeptDO> getSimpleDepts(SysDeptListReqVO reqVO);

    /**
     * 获得科室信息
     *
     * @param id 科室编号
     * @return 科室信息
     */
    SysDeptDO getDept(Long id);

    /**
     * 获得所有子科室，从缓存中
     *
     * @param parentId 科室编号
     * @param recursive 是否递归获取所有
     * @return 子科室列表
     */
    List<SysDeptDO> getDeptsByParentIdFromCache(Long parentId, boolean recursive);

    List<SysDeptDO> getSubDepts(SysDeptListReqVO reqVO);

}
