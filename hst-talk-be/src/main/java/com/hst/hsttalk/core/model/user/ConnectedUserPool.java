package com.hst.hsttalk.core.model.user;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
public class ConnectedUserPool {

	private final Map<String, ChatUser> pool = new ConcurrentHashMap<>();

	public void put(String nickname, WebSocketSession session) {
		this.pool.put(session.getId(), ChatUser.of(nickname, session));
	}

	public void remove(WebSocketSession session) {
		this.pool.remove(session.getId());
	}

	public ChatUser get(String sessionId) {
		return this.pool.get(sessionId);
	}

}
