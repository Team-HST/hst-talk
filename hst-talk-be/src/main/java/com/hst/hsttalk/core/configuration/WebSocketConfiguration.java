package com.hst.hsttalk.core.configuration;

import com.hst.hsttalk.core.controller.ChatSystemFrontController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author dlgusrb0808@gmail.com
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {
	private static final String WEB_SOCKET_URL = "/ws/chat";

	private final ChatSystemFrontController chatSystemFrontController;

	public WebSocketConfiguration(ChatSystemFrontController chatSystemFrontController) {
		this.chatSystemFrontController = chatSystemFrontController;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatSystemFrontController, WEB_SOCKET_URL).setAllowedOrigins("*");
	}
}