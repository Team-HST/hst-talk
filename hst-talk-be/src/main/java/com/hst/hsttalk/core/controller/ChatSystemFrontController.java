package com.hst.hsttalk.core.controller;

import com.hst.hsttalk.core.action.ActionDispatcher;
import com.hst.hsttalk.core.model.messaging.ChatMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
public class ChatSystemFrontController extends TextWebSocketHandler {

	private final ActionDispatcher actionDispatcher;

	public ChatSystemFrontController(ActionDispatcher actionDispatcher) {
		this.actionDispatcher = actionDispatcher;
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
		ChatMessage message = ChatMessage.from(textMessage.getPayload());
		if (message == null) {
			session.sendMessage(new TextMessage(ChatMessage.systemChat("Not readable message").toString()));
			return;
		}
		actionDispatcher.dispatch(message);
	}

}
