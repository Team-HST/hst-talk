package com.hst.hsttalk.core.action;

import com.hst.hsttalk.core.SessionContextHolder;
import com.hst.hsttalk.core.action.factory.ActionFactory;
import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.messaging.ChatMessage;
import com.hst.hsttalk.core.room.RoomManager;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
public class ActionDispatcher {
	private final ActionFactory actionFactory;
	private final RoomManager roomManager;

	public ActionDispatcher(ActionFactory actionFactory, RoomManager roomManager) {
		this.actionFactory = actionFactory;
		this.roomManager = roomManager;
	}

	/**
	 * Handle message
	 *
	 * @param session     current user session
	 * @param chatMessage message
	 */
	public void dispatch(WebSocketSession session, ChatMessage chatMessage) throws Exception {
		SessionContextHolder.setCurrentUser(session);
		Action action = actionFactory.createAction(chatMessage);
		if (action == null) {
			throw new IllegalArgumentException("Action creation fail");
		}
		action.doAction(roomManager);
	}

}
