package com.hst.hsttalk.core.model.type;

import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.action.impl.*;
import com.hst.hsttalk.core.model.messaging.MessageProtocol;

/**
 * @author dlgusrb0808@gmail.com
 */
public enum MessageType {
	INIT_USER_INFO(InitUserInfoAction.class),
	CHAT(ChatAction.class),
	SYSTEM_ERROR(null),
	CREATE_ROOM(CreateRoomAction.class),
	ENTER_ROOM(EnterRoomAction.class),
	GET_ROOM_MEMBER_LIST(GetRoomMemberListAction.class),
	LEAVE_ROOM(LeaveRoomAction.class);

	private final Class<? extends Action> actionClass;

	MessageType(Class<? extends Action> actionClass) {
		this.actionClass = actionClass;
	}

	public Class<? extends Action> getActionClass() {
		return actionClass;
	}

	public MessageProtocol toMessageProtocol(String roomId, Object payload) {
		return MessageProtocol.of(this, roomId, payload);
	}
}
