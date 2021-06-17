package com.hst.hsttalk.core.model.action;

import com.hst.hsttalk.core.model.messaging.MessageProtocol;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author dlgusrb0808@gmail.com
 */
public interface Action {

	/**
	 * Define some job for Message
	 *
	 * @param session  the session
	 * @param protocol the protocol
	 */
	void doAction(WebSocketSession session, MessageProtocol protocol) throws Exception;
	
}
