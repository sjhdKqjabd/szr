package com.pandahis.dashboard.modules.system.service.dept.impl;

import com.pandahis.dashboard.common.enums.CommonStatusEnum;
import com.pandahis.dashboard.common.exception.util.ServiceExceptionUtil;
import com.pandahis.dashboard.framework.mybatis.core.dataobject.BaseDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysDeptDO;
import com.pandahis.dashboard.modules.system.dal.mysql.dept.SysDeptMapper;
import com.pandahis.dashboard.modules.system.enums.SysErrorCodeConstants;
import com.pandahis.dashboard.modules.system.enums.dept.DeptIdEnum;
import com.pandahis.dashboard.modules.system.mq.producer.dept.SysDeptProducer;
import cn.hutool.core.collection.CollUtil;
import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptListReqVO;
import com.pandahis.dashboard.modules.system.controller.dept.vo.dept.SysDeptUpdateReqVO;
import com.pandahis.dashboard.modules.system.convert.dept.SysDeptConvert;
import com.pandahis.dashboard.modules.system.service.dept.SysDeptService;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

/**
 * 科室 Service 实现类
 *
 * @author 源码乐园
 */
@Service
@Validated
@Slf4j
public class SysDeptServiceImpl implements SysDeptService {

    /**
     * 定时执行 {@link #schedulePeriodicRefresh()} 的周期
     * 因为已经通过 Redis Pub/Sub 机制，所以频率不需要高
     */
    private static final long SCHEDULER_PERIOD = 5 * 60 * 1000L;

    /**
     * 科室缓存
     * key：科室编号 {@link SysDeptDO#getId()}
     *
     * 这里声明 volatile 修饰的原因是，每次刷新时，直接修改指向
     */
    @SuppressWarnings("FieldCanBeLocal")
    private volatile Map<Long, SysDeptDO> deptCache;
    /**
     * 父科室缓存
     * key：科室编号 {@link SysDeptDO#getParentId()}
     * value: 直接子科室列表
     *
     * 这里声明 volatile 修饰的原因是，每次刷新时，直接修改指向
     */
    private volatile Multimap<Long, SysDeptDO> parentDeptCache;
    /**
     * 缓存科室的最大更新时间，用于后续的增量轮询，判断是否有更新
     */
    private volatile Date maxUpdateTime;

    @Resource
    private SysDeptMapper deptMapper;

    @Resource
    private SysDeptProducer deptProducer;

    @Override
    @PostConstruct
    public synchronized void initLocalCache() {
        // 获取科室列表，如果有更新
        List<SysDeptDO> deptList = this.loadDeptIfUpdate(maxUpdateTime);
        if (CollUtil.isEmpty(deptList)) {
            return;
        }

        // 构建缓存
        ImmutableMap.Builder<Long, SysDeptDO> builder = ImmutableMap.builder();
        ImmutableMultimap.Builder<Long, SysDeptDO> parentBuilder = ImmutableMultimap.builder();
        deptList.forEach(sysRoleDO -> {
            builder.put(sysRoleDO.getId(), sysRoleDO);
            parentBuilder.put(sysRoleDO.getParentId(), sysRoleDO);
        });
        // 设置缓存
        deptCache = builder.build();
        parentDeptCache = parentBuilder.build();
        assert deptList.size() > 0; // 断言，避免告警
        maxUpdateTime = deptList.stream().max(Comparator.comparing(BaseDO::getUpdateTime)).get().getUpdateTime();
        log.info("[initLocalCache][初始化 Dept 数量为 {}]", deptList.size());
    }

    @Scheduled(fixedDelay = SCHEDULER_PERIOD, initialDelay = SCHEDULER_PERIOD)
    public void schedulePeriodicRefresh() {
        initLocalCache();
    }

    /**
     * 如果科室发生变化，从数据库中获取最新的全量科室。
     * 如果未发生变化，则返回空
     *
     * @param maxUpdateTime 当前科室的最大更新时间
     * @return 科室列表
     */
    private List<SysDeptDO> loadDeptIfUpdate(Date maxUpdateTime) {
        // 第一步，判断是否要更新。
        if (maxUpdateTime == null) { // 如果更新时间为空，说明 DB 一定有新数据
            log.info("[loadMenuIfUpdate][首次加载全量科室]");
        } else { // 判断数据库中是否有更新的科室
            if (!deptMapper.selectExistsByUpdateTimeAfter(maxUpdateTime)) {
                return null;
            }
            log.info("[loadMenuIfUpdate][增量加载全量科室]");
        }
        // 第二步，如果有更新，则从数据库加载所有科室
        return deptMapper.selectList();
    }

    @Override
    public Long createDept(SysDeptCreateReqVO reqVO) {
        // 校验正确性
        checkCreateOrUpdate(null, reqVO.getParentId(), reqVO.getName());
        // 插入科室
        SysDeptDO dept = SysDeptConvert.INSTANCE.convert(reqVO);
        deptMapper.insert(dept);
        // 发送刷新消息
        deptProducer.sendDeptRefreshMessage();
        return dept.getId();
    }

    @Override
    public void updateDept(SysDeptUpdateReqVO reqVO) {
        // 校验正确性
        checkCreateOrUpdate(reqVO.getId(), reqVO.getParentId(), reqVO.getName());
        // 更新科室
        SysDeptDO updateObj = SysDeptConvert.INSTANCE.convert(reqVO);
        deptMapper.updateById(updateObj);
        // 发送刷新消息
        deptProducer.sendDeptRefreshMessage();
    }

    @Override
    public void deleteDept(Long id) {
        // 校验是否存在
        checkDeptExists(id);
        // 校验是否有子科室
        if (deptMapper.selectCountByParentId(id) > 0) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DEPT_EXITS_CHILDREN);
        }
        // 删除科室
        deptMapper.deleteById(id);
        // 发送刷新消息
        deptProducer.sendDeptRefreshMessage();
    }

    @Override
    public List<SysDeptDO> getSimpleDepts(Collection<Long> ids) {
        return deptMapper.selectBatchIds(ids);
    }

    @Override
    public List<SysDeptDO> getSimpleDepts(SysDeptListReqVO reqVO) {
        return deptMapper.selectList(reqVO);
    }

    @Override
    public List<SysDeptDO> getDeptsByParentIdFromCache(Long parentId, boolean recursive) {
        List<SysDeptDO> result = new ArrayList<>();
        // 递归，简单粗暴
        this.listDeptsByParentIdFromCache(result, parentId,
                recursive ? Integer.MAX_VALUE : 1, // 如果递归获取，则无限；否则，只递归 1 次
                parentDeptCache);
        return result;
    }

    /**
     * 递归获取所有的子科室，添加到 result 结果
     *
     * @param result 结果
     * @param parentId 父编号
     * @param recursiveCount 递归次数
     * @param parentDeptMap 父科室 Map，使用缓存，避免变化
     */
    private void listDeptsByParentIdFromCache(List<SysDeptDO> result, Long parentId, int recursiveCount,
                                              Multimap<Long, SysDeptDO> parentDeptMap) {
        // 递归次数为 0，结束！
        if (recursiveCount == 0) {
            return;
        }
        // 获得子科室
        Collection<SysDeptDO> depts = parentDeptMap.get(parentId);
        if (CollUtil.isEmpty(depts)) {
            return;
        }
        result.addAll(depts);
        // 继续递归
        depts.forEach(dept -> listDeptsByParentIdFromCache(result, dept.getId(),
                recursiveCount - 1, parentDeptMap));
    }

    @Override
    public SysDeptDO getDept(Long id) {
        return deptMapper.selectById(id);
    }

    private void checkCreateOrUpdate(Long id, Long parentId, String name) {
        // 校验自己存在
        checkDeptExists(id);
        // 校验父科室的有效性
        checkParentDeptEnable(id, parentId);
        // 校验科室名的唯一性
        checkDeptNameUnique(id, parentId, name);
    }

    private void checkParentDeptEnable(Long id, Long parentId) {
        if (parentId == null || DeptIdEnum.ROOT.getId().equals(parentId)) {
            return;
        }
        // 不能设置自己为父科室
        if (parentId.equals(id)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DEPT_PARENT_ERROR);
        }
        // 父岗位不存在
        SysDeptDO dept = deptMapper.selectById(parentId);
        if (dept == null) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DEPT_PARENT_NOT_EXITS);
        }
        // 父科室被禁用
        if (!CommonStatusEnum.ENABLE.getStatus().equals(dept.getStatus())) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DEPT_NOT_ENABLE);
        }
        // 父科室不能是原来的子科室
        List<SysDeptDO> children = this.getDeptsByParentIdFromCache(id, true);
        if (children.stream().anyMatch(dept1 -> dept1.getId().equals(parentId))) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DEPT_PARENT_IS_CHILD);
        }
    }

    private void checkDeptExists(Long id) {
        if (id == null) {
            return;
        }
        SysDeptDO dept = deptMapper.selectById(id);
        if (dept == null) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DEPT_NOT_FOUND);
        }
    }

    private void checkDeptNameUnique(Long id, Long parentId, String name) {
        SysDeptDO menu = deptMapper.selectByParentIdAndName(parentId, name);
        if (menu == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的岗位
        if (id == null) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DEPT_NAME_DUPLICATE);
        }
        if (!menu.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.DEPT_NAME_DUPLICATE);
        }
    }

    @Override
    public List<SysDeptDO> getSubDepts(SysDeptListReqVO reqVO) {
        return    deptMapper.selectList(reqVO);
    }


    //    @Override
//    @DataScope(deptAlias = "d")
//    public List<SysDept> selectDeptList(SysDept dept)
//    {
//        return deptMapper.selectDeptList(dept);
//    }

}
