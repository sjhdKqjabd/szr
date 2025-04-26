package com.pandahis.dashboard.common.chat;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.Objects;

// 这个类主要是为了与文心一言API建立流式连接，实现数据的实时返回，而不是等完整的数据生成之后才将数据返回
// 可以减少用户等待时间，实现更好的交互体验
@Slf4j
@Data
@Component
public class BaiduEventSourceListener extends EventSourceListener {


    private Session session;

    private Long questionId;


    @Autowired
    private WebSocketServiceChat websocketServiceChat;



    @Override
    public void onOpen(EventSource eventSource, Response response) {

        log.info("baidu建立sse连接...");
    }

    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data) {
        try {
            JSONObject parse = (JSONObject) JSONObject.parse(data);
            String result = (String) parse.get("result");

            if(result!=null){
                websocketServiceChat.sendMessage(result,session,questionId);
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("baidu返回数据：{}", data);
    }

    @Override
    public void onClosed(EventSource eventSource) {
        log.info("baidu关闭sse连接...");
    }

    @SneakyThrows
    @Override
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        if(Objects.isNull(response)){
            log.error("baidu  sse连接异常:{}", t);
            eventSource.cancel();
            return;
        }
        ResponseBody body = response.body();
        if (Objects.nonNull(body)) {
            log.error("baidu  sse连接异常data：{}，异常：{}", body.string(), t);
        } else {
            log.error("baidu  sse连接异常data：{}，异常：{}", response, t);
        }
        eventSource.cancel();
    }
}
