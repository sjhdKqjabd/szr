package com.pandahis.dashboard.modules.system.dal.redis.auth;

import com.pandahis.dashboard.framework.security.core.LoginUser;
import com.pandahis.dashboard.modules.system.dal.redis.SysRedisKeyConstants;
import com.pandahis.dashboard.util.json.JsonUtils;
import com.pandahis.dashboard.modules.system.service.auth.SysUserSessionService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * {@link LoginUser} 的 RedisDAO
 *
 * @author 源码乐园
 */
@Repository
public class SysLoginUserRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private SysUserSessionService sysUserSessionService;

    public LoginUser get(String sessionId) {
        String redisKey = formatKey(sessionId);
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), LoginUser.class);
    }

    public void set(String sessionId, LoginUser loginUser) {
        String redisKey = formatKey(sessionId);
        stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(loginUser),
                Duration.ofMillis(sysUserSessionService.getSessionTimeoutMillis()));
    }

    public void delete(String sessionId) {
        String redisKey = formatKey(sessionId);
        stringRedisTemplate.delete(redisKey);
    }

    private static String formatKey(String sessionId) {
        return String.format(SysRedisKeyConstants.LOGIN_USER.getKeyTemplate(), sessionId);
    }

}
