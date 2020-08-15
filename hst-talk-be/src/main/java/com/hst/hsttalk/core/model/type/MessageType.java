package com.hst.hsttalk.core.model.type;

import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.action.impl.ChatAction;
import com.hst.hsttalk.core.model.action.impl.CreateRoomAction;
import com.hst.hsttalk.core.model.action.impl.JoinAction;
import com.hst.hsttalk.core.model.action.impl.LeaveAction;

/**
 * @author dlgusrb0808@gmail.com
 */
public enum MessageType {
	JOIN(JoinAction.class),
	LEAVE(LeaveAction.class),
	CHAT(ChatAction.class),
	SYSTEM_CHAT(null),
	CREATE_ROOM(CreateRoomAction.class)
	;

	private final Class<? extends Action> actionClass;

	MessageType(Class<? extends Action> actionClass) {
		this.actionClass = actionClass;
	}

	public Class<? extends Action> getActionClass() {
		return actionClass;
	}
}
