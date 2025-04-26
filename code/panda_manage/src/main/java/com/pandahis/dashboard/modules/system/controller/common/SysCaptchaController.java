package com.pandahis.dashboard.modules.system.controller.common;

import com.pandahis.dashboard.common.pojo.CommonResult;
import com.pandahis.dashboard.modules.system.controller.common.vo.SysCaptchaImageRespVO;
import com.pandahis.dashboard.modules.system.service.common.SysCaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "验证码")
@RestController
@RequestMapping("/system/captcha")
public class SysCaptchaController {

    @Resource
    private SysCaptchaService captchaService;

    @GetMapping("/get-image")
    @ApiOperation("生成图片验证码")
    public CommonResult<SysCaptchaImageRespVO> getCaptchaImage() {
        return CommonResult.success(captchaService.getCaptchaImage());
    }

}
