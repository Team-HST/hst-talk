package com.hst.hsttalk.core.model.action.impl;

import com.hst.hsttalk.core.SessionContextHolder;
import com.hst.hsttalk.core.model.action.AbstractAction;
import com.hst.hsttalk.core.model.messaging.ChatMessage;
import com.hst.hsttalk.core.model.type.MessageType;
import com.hst.hsttalk.core.room.RoomManager;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author dlgusrb0808@gmail.com
 */
public class CreateRoomAction extends AbstractAction {

	public CreateRoomAction(ChatMessage chatMessage) {
		super(chatMessage);
	}

	@Override
	public void doAction(RoomManager roomManager) throws Exception {
		WebSocketSession session = SessionContextHolder.getCurrentUser();
		String createdRoomId = roomManager.createRoom(session);
		session.sendMessage(ChatMessage.of(MessageType.CREATE_ROOM, createdRoomId).toTextMessage());
	}
}
