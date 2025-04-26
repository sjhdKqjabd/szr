package com.pandahis.dashboard.common.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * <h2></h2>
 * <p>
 *
 * </p>
 *
 * @author LiYang
 * @createTime 2022年07月19日 9:57?上午
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig {

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

}
