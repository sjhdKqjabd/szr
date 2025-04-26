package com.pandahis.dashboard.modules.system.dal.redis;

import com.pandahis.dashboard.framework.redis.core.RedisKeyDefine;
import com.pandahis.dashboard.framework.security.core.LoginUser;

/**
 * System Redis Key 枚举类
 *
 * @author 源码乐园
 */
public interface SysRedisKeyConstants {

    RedisKeyDefine LOGIN_USER = new RedisKeyDefine("登陆用户的缓存",
            "login_user:%s", // 参数为 sessionId
            RedisKeyDefine.KeyTypeEnum.STRING, LoginUser.class, RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);

    RedisKeyDefine CAPTCHA_CODE = new RedisKeyDefine("验证码的缓存",
            "captcha_code:%s", // 参数为 uuid
            RedisKeyDefine.KeyTypeEnum.STRING, String.class, RedisKeyDefine.TimeoutTypeEnum.DYNAMIC);

}
