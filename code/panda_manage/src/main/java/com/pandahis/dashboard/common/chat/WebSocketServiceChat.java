package com.pandahis.dashboard.common.chat;


import cn.smallbun.screw.core.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.pandahis.dashboard.modules.dev.sendMsg.controller.sendMsg.vo.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint(value = "/ws/chat")
@Slf4j
@Component
public class WebSocketServiceChat  {


    public  static  final CopyOnWriteArrayList<Session> sessions = new CopyOnWriteArrayList<>();



    /**
     * WebSocket 连接成功调用方法
     *
     * @param session 连接对象
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        sessions.add(session);
        session.getBasicRemote().sendText("sessionId:"+session.getId());
        log.info("Test Socket 连接成功，sessionId：{}", session.getId());
    }

    /**
     * 接收普通文本消息
     *
     * @param message 消息
     * @param session 当前socket连接对象
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        if (StringUtils.isBlank(message)) {
            return;
        }
        log.info("Test Socket 收到消息：{}", message);
    }

    public void sendMessage(String message,Session session,Long questionId)  {
        try {
            session.getBasicRemote().sendText(JSON.toJSONString(new ChatResponse(message,questionId)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 关闭连接调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        log.info("Test Socket 关闭，sessionId：{}", session.getId());
    }

    /**
     * socket 异常调用的方法
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("Test Socket 处理异常，sessionId：{}, 异常原因：{}", session.getId(), error.getMessage());
    }


}
