package com.pandahis.dashboard.modules.system.controller.user;

import com.pandahis.dashboard.common.exception.util.ServiceExceptionUtil;
import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.framework.security.core.util.SecurityFrameworkUtils;
import com.pandahis.dashboard.modules.system.convert.user.SysUserConvert;
import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysDeptDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.dept.SysPostDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysRoleDO;
import com.pandahis.dashboard.modules.system.dal.dataobject.user.SysUserDO;
import com.pandahis.dashboard.modules.system.enums.SysErrorCodeConstants;
import com.pandahis.dashboard.modules.system.service.dept.SysDeptService;
import com.pandahis.dashboard.modules.system.service.dept.SysPostService;
import com.pandahis.dashboard.modules.system.service.permission.SysPermissionService;
import com.pandahis.dashboard.modules.system.service.permission.SysRoleService;
import com.pandahis.dashboard.modules.system.service.user.SysUserService;
import cn.hutool.core.collection.CollUtil;
import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileRespVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdatePasswordReqVO;
import com.pandahis.dashboard.modules.system.controller.user.vo.profile.SysUserProfileUpdateReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author niudehua
 */
@Api(tags = "用户个人中心")
@RestController
@RequestMapping("/system/user/profile")
@Validated
@Slf4j
public class SysUserProfileController {

    @Resource
    private SysUserService userService;
    @Resource
    private SysDeptService deptService;
    @Resource
    private SysPostService postService;
    @Resource
    private SysPermissionService permissionService;
    @Resource
    private SysRoleService roleService;

    @GetMapping("/get")
    @ApiOperation("获得登录用户信息")
    public CommonResult<SysUserProfileRespVO> profile() {
        // 获得用户基本信息
        SysUserDO user = userService.getUser(SecurityFrameworkUtils.getLoginUserId());
        SysUserProfileRespVO resp = SysUserConvert.INSTANCE.convert03(user);
        // 获得用户角色
        List<SysRoleDO> userRoles = roleService.getRolesFromCache(permissionService.listUserRoleIs(user.getId()));
        resp.setRoles(SysUserConvert.INSTANCE.convertList(userRoles));
        // 获得科室信息
        if (user.getDeptId() != null) {
            SysDeptDO dept = deptService.getDept(user.getDeptId());
            resp.setDept(SysUserConvert.INSTANCE.convert02(dept));
        }
        // 获得岗位信息
        if (CollUtil.isNotEmpty(user.getPostIds())) {
            List<SysPostDO> posts = postService.getPosts(user.getPostIds());
            resp.setPosts(SysUserConvert.INSTANCE.convertList02(posts));
        }
        return CommonResult.success(resp);
    }



    @GetMapping("/getCurrentOnlineUser")
    @ApiOperation("获得登录用户信息")
    public CommonResult<List<SysUserDO>> getCurrentOnlineUser(@RequestBody SysUserDO reqVO) {
        // 获得用户基本信息
        List<SysUserDO> users = userService.getCurrentOnlineUser(reqVO);
        return CommonResult.success(users);
    }


    @PutMapping("/update")
    @ApiOperation("修改用户个人信息")
    public CommonResult<Boolean> updateUserProfile(@Valid @RequestBody SysUserProfileUpdateReqVO reqVO) {
        userService.updateUserProfile(SecurityFrameworkUtils.getLoginUserId(), reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/update-password")
    @ApiOperation("修改用户个人密码")
    public CommonResult<Boolean> updateUserProfilePassword(@Valid @RequestBody SysUserProfileUpdatePasswordReqVO reqVO) {
        userService.updateUserPassword(SecurityFrameworkUtils.getLoginUserId(), reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/upload-avatar")
    @ApiOperation("上传用户个人头像")
    public CommonResult<Object> updateUserAvatar(@RequestParam("avatarFile") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw ServiceExceptionUtil.exception(SysErrorCodeConstants.FILE_IS_EMPTY);
        }
        userService.updateUserAvatar(SecurityFrameworkUtils.getLoginUserId(), file.getInputStream());
        SysUserDO user = userService.getUser(SecurityFrameworkUtils.getLoginUserId());

        Map result=new HashMap<>();
        result.put("imgUrl",user.getAvatar());

        return CommonResult.success(result);
    }





}
