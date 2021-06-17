package com.hst.hsttalk.core.model.action.impl;

import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.messaging.MessageProtocol;
import com.hst.hsttalk.core.model.room.ChatRoom;
import com.hst.hsttalk.core.model.roommanager.RoomManager;
import com.hst.hsttalk.core.model.roommanager.RoomManagerAware;
import com.hst.hsttalk.core.model.type.MessageType;
import com.hst.hsttalk.core.model.user.ChatUser;
import com.hst.hsttalk.core.model.user.ConnectedUserPool;
import com.hst.hsttalk.core.model.user.ConnectedUserPoolAware;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dlgusrb0808@gmail.com
 */
public class GetRoomMemberListAction implements Action, RoomManagerAware, ConnectedUserPoolAware {

	private RoomManager roomManager;
	private ConnectedUserPool pool;

	@Override
	public void doAction(WebSocketSession session, MessageProtocol protocol) throws Exception {
		String roomId = protocol.getRoomId();
		ChatRoom chatRoom = roomManager.getRoom(roomId);
		Map<String, Object> payload = new HashMap<>();
		payload.put("owner", chatRoom.getRoomOwner().toResponse());
		payload.put("participants",
				chatRoom.getParticipants().stream().map(ChatUser::toResponse).collect(Collectors.toList()));
		session.sendMessage(MessageProtocol.of(MessageType.GET_ROOM_MEMBER_LIST, roomId, payload).toTextMessage());
	}

	@Override
	public void setRoomManager(RoomManager roomManager) {
		this.roomManager = roomManager;
	}

	@Override
	public void setConnectedUserPool(ConnectedUserPool pool) {
		this.pool = pool;
	}
}
