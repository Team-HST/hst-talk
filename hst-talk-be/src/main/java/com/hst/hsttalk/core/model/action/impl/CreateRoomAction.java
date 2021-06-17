package com.hst.hsttalk.core.model.action.impl;

import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.messaging.MessageProtocol;
import com.hst.hsttalk.core.model.room.ChatRoom;
import com.hst.hsttalk.core.model.roommanager.RoomManager;
import com.hst.hsttalk.core.model.roommanager.RoomManagerAware;
import com.hst.hsttalk.core.model.type.MessageType;
import org.springframework.web.socket.WebSocketSession;


/**
 * @author dlgusrb0808@gmail.com
 */
public class CreateRoomAction implements Action, RoomManagerAware {

	private RoomManager roomManager;

	@Override
	public void doAction(WebSocketSession session, MessageProtocol protocol) throws Exception {
		ChatRoom room = roomManager.createRoom(session);
		session.sendMessage(MessageProtocol.of(MessageType.CREATE_ROOM, room.getRoomId(), null).toTextMessage());
	}

	@Override
	public void setRoomManager(RoomManager roomManager) {
		this.roomManager = roomManager;
	}
}
