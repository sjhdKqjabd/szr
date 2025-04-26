package com.pandahis.dashboard.common.chat;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;


/**
 * <h2></h2>
 *
 * @author LiYang
 * @since 2022年12月05日 16:52
 */

public class TestSocketHandler{

	public final static Logger LOGGER = LoggerFactory.getLogger(TestSocketHandler.class);

	/**
	 * WebSocket 连接成功调用方法
	 *
	 * @param session 连接对象
	 */
	@OnOpen
	public void onOpen(Session session) throws IOException {
		LOGGER.info("Test Socket 连接成功，sessionId：{}", session.getId());
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
		LOGGER.info("Test Socket 收到消息：{}", message);
	}

	/**
	 * 关闭连接调用的方法
	 */
	@OnClose
	public void onClose(Session session) {
		LOGGER.info("Test Socket 关闭，sessionId：{}", session.getId());
	}

	/**
	 * socket 异常调用的方法
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		LOGGER.error("Test Socket 处理异常，sessionId：{}, 异常原因：{}", session.getId(), error.getMessage());
	}


}
