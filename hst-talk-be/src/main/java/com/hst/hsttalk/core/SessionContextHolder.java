package com.hst.hsttalk.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author dlgusrb0808@gmail.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessionContextHolder {

	private static final ThreadLocal<WebSocketSession> SESSION_CONTEXT = new ThreadLocal<>();

	/**
	 * Set current user session in thread local
	 *
	 * @param session current user session
	 */
	public static void setCurrentUser(WebSocketSession session) {
		SESSION_CONTEXT.set(session);
	}

	/**
	 * Get current user session in thread local
	 *
	 * @return current user session
	 */
	public static WebSocketSession getCurrentUser() {
		return SESSION_CONTEXT.get();
	}

}
