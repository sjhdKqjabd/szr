package com.pandahis.dashboard.modules.system.convert.common;

import com.pandahis.dashboard.modules.system.controller.common.vo.SysCaptchaImageRespVO;
import cn.hutool.captcha.AbstractCaptcha;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysCaptchaConvert {

    SysCaptchaConvert INSTANCE = Mappers.getMapper(SysCaptchaConvert.class);

    default SysCaptchaImageRespVO convert(String uuid, AbstractCaptcha captcha) {
        return SysCaptchaImageRespVO.builder().uuid(uuid).img(captcha.getImageBase64()).build();
    }

}
