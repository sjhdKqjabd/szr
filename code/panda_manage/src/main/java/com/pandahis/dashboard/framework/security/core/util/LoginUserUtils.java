package com.pandahis.dashboard.framework.security.core.util;

import com.pandahis.dashboard.framework.security.core.LoginUser;
import com.pandahis.dashboard.modules.system.dal.dataobject.permission.SysRoleDO;
import com.pandahis.dashboard.modules.system.service.permission.impl.SysRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Set;

@Component
public class LoginUserUtils {

    @Autowired
    SysRoleServiceImpl sysRoleService;


    public   Boolean isLoginUserHasAdminRole(HttpServletRequest request){
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            if (context == null) {
                return null;
            }
            Authentication authentication = context.getAuthentication();
            if (authentication == null) {
                return null;
            }
            LoginUser getuser=   authentication.getPrincipal() instanceof LoginUser ? (LoginUser) authentication.getPrincipal() : null;

            Set<Long> roleIds = getuser.getRoleIds();
            Iterator<Long> iterator = roleIds.iterator();
            while (iterator.hasNext()){
                SysRoleDO role = sysRoleService.getRole(iterator.next());

                if(role!=null){
                    if(role.getName().contains("管理")){
                         return true;
                    }
                }
            }

        }catch(Exception e){
            return false;
        }
        return false;
    }
}
