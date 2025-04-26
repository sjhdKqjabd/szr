package com.pandahis.dashboard.modules.system.service.common.impl;

import com.pandahis.dashboard.framework.captcha.config.CaptchaProperties;
import com.pandahis.dashboard.modules.system.controller.common.vo.SysCaptchaImageRespVO;
import com.pandahis.dashboard.modules.system.dal.redis.common.SysCaptchaRedisDAO;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.IdUtil;
import com.pandahis.dashboard.modules.system.convert.common.SysCaptchaConvert;
import com.pandahis.dashboard.modules.system.service.common.SysCaptchaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 验证码 Service 实现类
 */
@Service
public class SysCaptchaServiceImpl implements SysCaptchaService {

    @Resource
    private CaptchaProperties captchaProperties;

    @Resource
    private SysCaptchaRedisDAO captchaRedisDAO;

    @Override
    public SysCaptchaImageRespVO getCaptchaImage() {
        // 生成验证码
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(captchaProperties.getWidth(), captchaProperties.getHeight(),4,0);
        // 缓存到 Redis 中
        String uuid = IdUtil.fastSimpleUUID();
        captchaRedisDAO.set(uuid, captcha.getCode(), captchaProperties.getTimeout());
        // 返回
        return SysCaptchaConvert.INSTANCE.convert(uuid, captcha);
    }

    @Override
    public String getCaptchaCode(String uuid) {
        return captchaRedisDAO.get(uuid);
    }

    @Override
    public void deleteCaptchaCode(String uuid) {
        captchaRedisDAO.delete(uuid);
    }

}
