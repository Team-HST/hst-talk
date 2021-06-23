package com.hst.hsttalk.core.action;

import com.hst.hsttalk.core.SessionContextHolder;
import com.hst.hsttalk.core.action.factory.ActionFactory;
import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.messaging.MessageProtocol;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
@RequiredArgsConstructor
public class ActionDispatcher {
	private final ActionFactory actionFactory;

	/**
	 * Handle message
	 *
	 * @param session  current user session
	 * @param protocol message
	 */
	public void dispatch(WebSocketSession session, MessageProtocol protocol) throws Exception {
		SessionContextHolder.setCurrentSession(session);
		Action action = actionFactory.createAction(protocol);
		if (action == null) {
			throw new IllegalArgumentException("Action creation fail");
		}
		action.doAction(session, protocol);
	}

}
