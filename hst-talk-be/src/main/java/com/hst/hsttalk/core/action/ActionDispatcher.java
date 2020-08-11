package com.hst.hsttalk.core.action;

import com.hst.hsttalk.core.action.factory.ActionFactory;
import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.messaging.ChatMessage;
import com.hst.hsttalk.core.room.RoomManager;
import org.springframework.stereotype.Component;

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

	public void dispatch(ChatMessage chatMessage) {
		Action action = actionFactory.createAction(chatMessage);
		if (action == null) {
			throw new IllegalArgumentException("Action could not null");
		}
		action.doAction(roomManager);
	}

}
