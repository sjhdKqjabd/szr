package com.pandahis.dashboard.modules.system.service.user;

import com.pandahis.dashboard.common.pojo.PageResult;
import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdatePasswordReqVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdateReqVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.user.*;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import com.pandahis.dashboard.util.collection.CollectionUtils;
import cn.hutool.core.collection.CollUtil;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 Service 接口
 *
 * @author 源码乐园
 */
public interface SysUserService {

    /**
     * 创建用户
     *
     * @param reqVO 用户信息
     * @return 用户编号
     */
    Long createUser(SysUserCreateReqVO reqVO);

    /**
     * 修改用户
     *
     * @param reqVO 用户信息
     */
    void updateUser(SysUserUpdateReqVO reqVO);

    /**
     * 修改用户个人信息
     *
     * @param id 用户编号
     * @param reqVO 用户个人信息
     */
    void updateUserProfile(Long id, SysUserProfileUpdateReqVO reqVO);

    /**
     * 修改用户个人密码
     *
     * @param id 用户编号
     * @param reqVO 更新用户个人密码
     */
    void updateUserPassword(Long id, SysUserProfileUpdatePasswordReqVO reqVO);

    /**
     * 更新用户头像
     *
     * @param id         用户 id
     * @param avatarFile 头像文件
     */
    void updateUserAvatar(Long id, InputStream avatarFile);

    /**
     * 修改密码
     *
     * @param id       用户编号
     * @param password 密码
     */
    void updateUserPassword(Long id, String password);

    /**
     * 修改密码
     *
     * @param id     用户编号
     * @param status 状态
     */
    void updateUserStatus(Long id, Integer status);

    /**
     * 删除用户
     *
     * @param id 用户编号
     */
    void deleteUser(Long id);

    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象信息
     */
    SysUserDO getUserByUsername(String username);

    /**
     * 通过用户 ID 查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    SysUserDO getUser(Long id);

    /**
     * 获得用户分页列表
     *
     * @param reqVO 分页条件
     * @return 分页列表
     */
    PageResult<SysUserDO> getUserPage(SysUserPageReqVO reqVO);

    /**
     * 获得用户列表
     *
     * @param reqVO 列表请求
     * @return 用户列表
     */
    List<SysUserDO> getUsers(SysUserExportReqVO reqVO);

    /**
     * 获得用户列表
     *
     * @param ids 用户编号数组
     * @return 用户列表
     */
    List<SysUserDO> getUsers(Collection<Long> ids);

    /**
     * 获得用户 Map
     *
     * @param ids 用户编号数组
     * @return 用户 Map
     */
    default Map<Long, SysUserDO> getUserMap(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return new HashMap<>();
        }
        return CollectionUtils.convertMap(getUsers(ids), SysUserDO::getId);
    }

    /**
     * 获得用户列表，基于昵称模糊匹配
     *
     * @param nickname 昵称
     * @return 用户列表
     */
    List<SysUserDO> getUsersByNickname(String nickname);

    /**
     * 获得用户列表，基于用户账号模糊匹配
     *
     * @param username 用户账号
     * @return 用户列表
     */
    List<SysUserDO> getUsersByUsername(String username);

    /**
     * 批量导入用户
     *
     * @param importUsers     导入用户列表
     * @param isUpdateSupport 是否支持更新
     * @return 导入结果
     */
    SysUserImportRespVO importUsers(List<SysUserImportExcelVO> importUsers, boolean isUpdateSupport);

    Long registerUser(SysUserCreateReqVO reqVO);


    List<SysUserPageReqVO> getUserPagesByUserName(SysUserPageReqVO vo);

    Object queryPetUser(SysUserPageReqVO vo);

    List<SysUserDO> getCurrentOnlineUser(SysUserDO reqVO);

    Object initAllUser(SysUserRespVO vo);

    Object getUsersByDeptName(SysUserRespVO vo);

    Object getUserByIdCard(SysUserRespVO sysUserRespVO);

}
