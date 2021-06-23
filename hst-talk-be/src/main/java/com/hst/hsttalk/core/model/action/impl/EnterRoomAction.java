package com.hst.hsttalk.core.model.action.impl;

import com.hst.hsttalk.core.SessionContextHolder;
import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.messaging.MessageProtocol;
import com.hst.hsttalk.core.model.room.ChatRoom;
import com.hst.hsttalk.core.model.roommanager.RoomManager;
import com.hst.hsttalk.core.model.roommanager.RoomManagerAware;
import com.hst.hsttalk.core.model.type.MessageType;
import com.hst.hsttalk.core.model.user.ChatUser;
import com.hst.hsttalk.core.model.user.ConnectedUserPool;
import com.hst.hsttalk.core.model.user.ConnectedUserPoolAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author dlgusrb0808@gmail.com
 */
@Slf4j
public class EnterRoomAction implements Action, RoomManagerAware, ConnectedUserPoolAware {

	private RoomManager roomManager;
	private ConnectedUserPool pool;

	@Override
	public void doAction(WebSocketSession session, MessageProtocol protocol) throws Exception {
		ChatUser user = pool.get(SessionContextHolder.getCurrentSession().getId());
		ChatRoom room = roomManager.getRoom(protocol.getRoomId());
		room.enter(user);
		session.sendMessage(MessageProtocol.of(MessageType.ENTER_ROOM, room.getRoomId(), user.toResponse()).toTextMessage());
	}

	@Override
	public void setConnectedUserPool(ConnectedUserPool pool) {
		this.pool = pool;
	}

	@Override
	public void setRoomManager(RoomManager roomManager) {
		this.roomManager = roomManager;
	}
}
