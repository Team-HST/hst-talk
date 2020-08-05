package com.hst.hsttalk.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
public class ChatHandler extends TextWebSocketHandler {

//	private Set<WebSocketSession> sessions = new HashSet<>();

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		session.sendMessage(new TextMessage("ECHO " + message.getPayload()));
	}
}
