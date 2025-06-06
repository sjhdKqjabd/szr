package com.pandahis.dashboard.modules.system.service.permission.impl;

import com.pandahis.dashboard.common.enums.CommonStatusEnum;
import com.pandahis.dashboard.common.exception.util.ServiceExceptionUtil;
import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.framework.mybatis.core.dataobject.BaseDO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.role.SysRoleCreateReqVO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.role.SysRoleExportReqVO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.role.SysRolePageReqVO;
import com.pandahis.dashboard.modules.system.controller.permission.vo.role.SysRoleUpdateReqVO;
import com.pandahis.dashboard.modules.system.convert.permission.SysRoleConvert;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysRoleDO;
import com.pandahis.dashboard.modules.system.dal.mysql.permission.SysRoleMapper;
import com.pandahis.dashboard.modules.system.enums.SysErrorCodeConstants;
import com.pandahis.dashboard.modules.system.enums.permission.RoleCodeEnum;
import com.pandahis.dashboard.modules.system.enums.permission.SysRoleTypeEnum;
import com.pandahis.dashboard.modules.system.mq.producer.permission.SysRoleProducer;
import com.pandahis.dashboard.modules.system.service.permission.SysPermissionService;
import com.pandahis.dashboard.modules.system.service.permission.SysRoleService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色 Service 实现类
 *
 * @author 源码乐园
 */
@Service
@Slf4j
public class SysRoleServiceImpl implements SysRoleService {

    /**
     * 定时执行 {@link #schedulePeriodicRefresh()} 的周期
     * 因为已经通过 Redis Pub/Sub 机制，所以频率不需要高
     */
    private static final long SCHEDULER_PERIOD = 5 * 60 * 1000L;

    /**
     * 角色缓存
     * key：角色编号 {@link SysRoleDO#getId()}
     *
     * 这里声明 volatile 修饰的原因是，每次刷新时，直接修改指向
     */
    private volatile Map<Long, SysRoleDO> roleCache;
    /**
     * 缓存菜单的最大更新时间，用于后续的增量轮询，判断是否有更新
     */
    private volatile Date maxUpdateTime;

    @Resource
    private SysPermissionService permissionService;

    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private SysRoleProducer roleProducer;

    /**
     * 初始化 {@link #roleCache} 缓存
     */
    @Override
    @PostConstruct
    public void initLocalCache() {
        // 获取菜单列表，如果有更新
        List<SysRoleDO> roleList = this.loadRoleIfUpdate(maxUpdateTime);
        if (CollUtil.isEmpty(roleList)) {
            return;
        }

        // 写入缓存
        ImmutableMap.Builder<Long, SysRoleDO> builder = ImmutableMap.builder();
        roleList.forEach(sysRoleDO -> builder.put(sysRoleDO.getId(), sysRoleDO));
        roleCache = builder.build();
        assert roleList.size() > 0; // 断言，避免告警
        maxUpdateTime = roleList.stream().max(Comparator.comparing(BaseDO::getUpdateTime)).get().getUpdateTime();
        log.info("[initLocalCache][初始化 Role 数量为 {}]", roleList.size());
    }

    @Scheduled(fixedDelay = SCHEDULER_PERIOD, initialDelay = SCHEDULER_PERIOD)
    public void schedulePeriodicRefresh() {
        initLocalCache();
    }

    /**
     * 如果菜单发生变化，从数据库中获取最新的全量菜单。
     * 如果未发生变化，则返回空
     *
     * @param maxUpdateTime 当前菜单的最大更新时间
     * @return 菜单列表
     */
    private List<SysRoleDO> loadRoleIfUpdate(Date maxUpdateTime) {
        // 第一步，判断是否要更新。
        if (maxUpdateTime == null) { // 如果更新时间为空，说明 DB 一定有新数据
            log.info("[loadRoleIfUpdate][首次加载全量菜单]");
        } else { // 判断数据库中是否有更新的菜单
            if (!roleMapper.selectExistsByUpdateTimeAfter(maxUpdateTime)) {
                return null;
            }
            log.info("[loadRoleIfUpdate][增量加载全量菜单]");
        }
        // 第二步，如果有更新，则从数据库加载所有菜单
        return roleMapper.selectList();
    }

    @Override
    public Long createRole(SysRoleCreateReqVO reqVO) {
        // 校验角色
        checkDuplicateRole(reqVO.getName(), reqVO.getCode(), null);
        // 插入到数据库
        SysRoleDO role = SysRoleConvert.INSTANCE.convert(reqVO);
        role.setType(SysRoleTypeEnum.CUSTOM.getType());
        role.setStatus(CommonStatusEnum.ENABLE.getStatus());
        roleMapper.insert(role);
        // 发送刷新消息
        roleProducer.sendRoleRefreshMessage();
        // 返回
        return role.getId();
    }

    @Override
    public void updateRole(SysRoleUpdateReqVO reqVO) {
        // 校验是否可以更新
        this.checkUpdateRole(reqVO.getId());
        // 校验角色的唯一字段是否重复
        checkDuplicateRole(reqVO.getName(), reqVO.getCode(), reqVO.getId());
        // 更新到数据库
        SysRoleDO updateObject = SysRoleConvert.INSTANCE.convert(reqVO);
        roleMapper.updateById(updateObject);
        // 发送刷新消息
        roleProducer.sendRoleRefreshMessage();
    }

    @Override
    public void updateRoleStatus(Long id, Integer status) {
        // 校验是否可以更新
        this.checkUpdateRole(id);
        // 更新状态
        SysRoleDO updateObject = new SysRoleDO();
        updateObject.setId(id);
        updateObject.setStatus(status);
        roleMapper.updateById(updateObject);
        // 发送刷新消息
        roleProducer.sendRoleRefreshMessage();
    }

    @Override
    public void updateRoleDataScope(Long id, Integer dataScope, Set<Long> dataScopeDeptIds) {
        // 校验是否可以更新
        checkUpdateRole(id);
        // 更新数据范围
        SysRoleDO updateObject = new SysRoleDO();
        updateObject.setId(id);
        updateObject.setDataScope(dataScope);
        updateObject.setDataScopeDeptIds(dataScopeDeptIds);
        roleMapper.updateById(updateObject);
        // 发送刷新消息
        roleProducer.sendRoleRefreshMessage();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long id) {
        // 校验是否可以更新
        this.checkUpdateRole(id);
        // 标记删除
        roleMapper.deleteById(id);
        // 删除相关数据
        permissionService.processRoleDeleted(id);
        // 发送刷新消息. 注意，需要事务提交后，在进行发送刷新消息。不然 db 还未提交，结果缓存先刷新了
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {

            @Override
            public void afterCommit() {
                roleProducer.sendRoleRefreshMessage();
            }

        });
    }

    @Override
    public SysRoleDO getRoleFromCache(Long id) {
        return roleCache.get(id);
    }

    @Override
    public List<SysRoleDO> getRoles(@Nullable Collection<Integer> statuses) {
        return roleMapper.selectListByStatus(statuses);
    }

    @Override
    public List<SysRoleDO> getRolesFromCache(Collection<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return roleCache.values().stream().filter(roleDO -> ids.contains(roleDO.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean hasAnyAdmin(Collection<SysRoleDO> roleList) {
        if (CollectionUtil.isEmpty(roleList)) {
            return false;
        }
        return roleList.stream().anyMatch(roleDO -> RoleCodeEnum.ADMIN.getKey().equals(roleDO.getCode()));
    }

    @Override
    public SysRoleDO getRole(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public PageResult<SysRoleDO> getRolePage(SysRolePageReqVO reqVO) {
        return roleMapper.selectPage(reqVO);
    }

    @Override
    public List<SysRoleDO> getRoles(SysRoleExportReqVO reqVO) {
        return roleMapper.listRoles(reqVO);
    }

    /**
     * 校验角色的唯一字段是否重复
     *
     * 1. 是否存在相同名字的角色
     * 2. 是否存在相同编码的角色
     *
     * @param name 角色名字
     * @param code 角色额编码
     * @param id 角色编号
     */
    private void checkDuplicateRole(String name, String code, Long id) {
        // 1. 该 name 名字被其它角色所使用
        SysRoleDO role = roleMapper.selectByName(name);
        if (role != null && !role.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.ROLE_NAME_DUPLICATE, name);
        }
        // 2. 是否存在相同编码的角色
        if (!StringUtils.hasText(code)) {
            return;
        }
        // 该 code 编码被其它角色所使用
        role = roleMapper.selectByCode(code);
        if (role != null && !role.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.ROLE_CODE_DUPLICATE, name);
        }
    }

    /**
     * 校验角色是否可以被更新
     *
     * @param id 角色编号
     */
    private void checkUpdateRole(Long id) {
        SysRoleDO roleDO = roleMapper.selectById(id);
        if (roleDO == null) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.ROLE_NOT_EXISTS);
        }
        // 内置角色，不允许删除
        if (SysRoleTypeEnum.SYSTEM.getType().equals(roleDO.getType())) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE);
        }
    }

//    @Override
//    @DataScope(deptAlias = "d")
//    public List<SysRole> selectRoleList(SysRole role) {
//        return roleMapper.selectRoleList(role);
//    }

}
