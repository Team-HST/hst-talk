package com.hst.hsttalk.core.controller;

import com.hst.hsttalk.core.action.ActionDispatcher;
import com.hst.hsttalk.core.model.messaging.MessageProtocol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
@Slf4j
public class ChatSystemFrontController extends TextWebSocketHandler {

	private final ActionDispatcher actionDispatcher;

	public ChatSystemFrontController(ActionDispatcher actionDispatcher) {
		this.actionDispatcher = actionDispatcher;
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
		MessageProtocol message = MessageProtocol.from(textMessage.getPayload());
		if (message == null) {
			session.sendMessage(new TextMessage(MessageProtocol.systemChat("Not readable message").toString()));
			return;
		}
		actionDispatcher.dispatch(session, message);
	}

}
