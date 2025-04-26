package com.pandahis.dashboard.modules.system.dal.redis.common;

import com.pandahis.dashboard.modules.system.dal.redis.SysRedisKeyConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * 验证码的 Redis DAO
 *
 * @author 源码乐园
 */
@Repository
public class SysCaptchaRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public String get(String uuid) {
        String redisKey = formatKey(uuid);
        return stringRedisTemplate.opsForValue().get(redisKey);
    }

    public void set(String uuid, String code, Duration timeout) {
        String redisKey = formatKey(uuid);
        stringRedisTemplate.opsForValue().set(redisKey, code, timeout);
    }

    public void delete(String uuid) {
        String redisKey = formatKey(uuid);
        stringRedisTemplate.delete(redisKey);
    }

    private static String formatKey(String uuid) {
        return String.format(SysRedisKeyConstants.CAPTCHA_CODE.getKeyTemplate(), uuid);
    }


}
