package com.pandahis.dashboard.common.chat;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiConstant {





    /**
     * ERNIE_BOT_TURBO 发起会话接口
     */
    public static final String ERNIE_BOT_TURBO_INSTANT = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/ernie-4.0-turbo-8k?access_token=";

    public static String getToken(String appKey, String secretKey) {
        String url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=" + appKey + "&client_secret=" + secretKey;
        String s = HttpUtil.get(url);
        BaiDuToken bean = JSONUtil.toBean(s, BaiDuToken.class);
        return bean.getAccess_token();
    }
}
