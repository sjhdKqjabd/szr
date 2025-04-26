package com.pandahis.dashboard.common.chat;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;

import java.net.Proxy;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

// 该类主要是处理接口请求，处理接口响应逻辑
@Slf4j
@Data
public class BaiduService {

    private static final long TIME_OUT = 30;

    private OkHttpClient okHttpClient;
    private String appKey;
    private String secretKey;

    public BaiduService(@NonNull String appKey, @NonNull String secretKey) {
        this.appKey = appKey;
        this.secretKey = secretKey;
        this.okHttpClient(30, 30, 30, null);
    }

    public BaiduService(@NonNull String appKey, @NonNull String secretKey, long connectTimeout, long writeTimeout, long readTimeout, Proxy proxy) {
        this.appKey = appKey;
        this.secretKey = secretKey;
        this.okHttpClient(connectTimeout, writeTimeout, readTimeout, proxy);
    }


    private void okHttpClient(long connectTimeout, long writeTimeout, long readTimeout, Proxy proxy) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(connectTimeout, TimeUnit.SECONDS);
        client.writeTimeout(writeTimeout, TimeUnit.SECONDS);
        client.readTimeout(readTimeout, TimeUnit.SECONDS);
        if (Objects.nonNull(proxy)) {
            client.proxy(proxy);
        }
        this.okHttpClient = client.build();
    }

    // 该方法是同步请求API，会等大模型将数据完全生成之后，返回响应结果，可能需要等待较长时间，视生成文本长度而定
    public ErnieBotTurboResponse ernieBotTurbo(ErnieBotTurboStreamParam param) {
        if (param == null) {
            log.error("参数异常：param不能为空");
            throw new RuntimeException("参数异常：param不能为空");
        }
        if (param.isStream()) {
            param.setStream(false);
        }
        String post = HttpUtil.post(ApiConstant.ERNIE_BOT_TURBO_INSTANT + ApiConstant.getToken(appKey, secretKey), JSONUtil.toJsonStr(param));
        return JSONUtil.toBean(post, ErnieBotTurboResponse.class);
    }

    // 该方法是通过流的方式请求API，大模型每生成一些字符，就会通过流的方式相应给客户端，
    // 我们是在 BaiduEventSourceListener.java 的 onEvent 方法中获取大模型响应的数据，其中data就是具体的数据，
    // 我们获取到数据之后，就可以通过 SSE/webscocket 的方式实时相应给前端页面展示
    public void ernieBotTurboStream(ErnieBotTurboStreamParam param, EventSourceListener eventSourceListener) {
        if (Objects.isNull(eventSourceListener)) {
            log.error("参数异常：EventSourceListener不能为空");
            throw new RuntimeException("参数异常：EventSourceListener不能为空");
        }
        if (param == null) {
            log.error("参数异常：param不能为空");
            throw new RuntimeException("参数异常：param不能为空");
        }
        if (!param.isStream()) {
            param.setStream(true);
        }
        try {
            EventSource.Factory factory = EventSources.createFactory(this.okHttpClient);
            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(param);
            Request request = new Request.Builder()
                    .url(ApiConstant.ERNIE_BOT_TURBO_INSTANT + ApiConstant.getToken(appKey, secretKey))
                    .post(RequestBody.create(MediaType.parse(ContentType.JSON.getValue()), requestBody))
                    .build();
            //创建事件
            EventSource eventSource = factory.newEventSource(request, eventSourceListener);
        } catch (JsonProcessingException e) {
            log.error("请求参数解析是失败！", e);
            throw new RuntimeException("请求参数解析是失败！", e);
        }

    }
}
