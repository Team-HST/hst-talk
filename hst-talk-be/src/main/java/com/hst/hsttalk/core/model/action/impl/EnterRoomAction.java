package com.hst.hsttalk.core.model.action.impl;

import com.hst.hsttalk.core.SessionContextHolder;
import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.messaging.MessageProtocol;
import com.hst.hsttalk.core.model.protocol.response.ChatResponse;
import com.hst.hsttalk.core.model.protocol.response.RoomMemberListResponse;
import com.hst.hsttalk.core.model.room.ChatRoom;
import com.hst.hsttalk.core.model.roommanager.RoomManager;
import com.hst.hsttalk.core.model.roommanager.RoomManagerAware;
import com.hst.hsttalk.core.model.type.MessageType;
import com.hst.hsttalk.core.model.user.ChatUser;
import com.hst.hsttalk.core.model.user.ConnectedUserPool;
import com.hst.hsttalk.core.model.user.ConnectedUserPoolAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.stream.Collectors;

/**
 * @author dlgusrb0808@gmail.com
 */
@Slf4j
public class EnterRoomAction implements Action, RoomManagerAware, ConnectedUserPoolAware {
	private RoomManager roomManager;
	private ConnectedUserPool pool;

	@Override
	public void doAction(WebSocketSession session, MessageProtocol protocol) throws Exception {
		String id = SessionContextHolder.getCurrentSession().getId();
		String roomId = protocol.getRoomId();
		ChatUser user = pool.get(SessionContextHolder.getCurrentSession().getId());
		ChatRoom room = roomManager.getRoom(protocol.getRoomId());
		String ownerId = room.getRoomOwner().getSession().getId();

		// 유저 방 입장 처리
		room.enter(user);
		session.sendMessage(MessageProtocol.of(MessageType.ENTER_ROOM, room.getRoomId(),
				room.getParticipants().stream().map(e -> e.toResponse(ownerId)).collect(Collectors.toList())).toTextMessage());

		RoomMemberListResponse roomMemberListResponse =
				RoomMemberListResponse.of(room.getParticipants().stream().map(e -> e.toResponse(ownerId)).collect(Collectors.toList()));

		// 해당 방에 노티
		ChatResponse response = ChatResponse.of("SYSTEM", String.format("%s님이 입장하셨습니다^^", user.getNickname()), false);
		TextMessage responseProtocol =
				MessageProtocol.of(MessageType.CHAT, protocol.getRoomId(), response).toTextMessage();
		TextMessage roomMemberListResponseProtocol = MessageProtocol.of(MessageType.GET_ROOM_MEMBER_LIST, roomId,
				roomMemberListResponse).toTextMessage();
		for (ChatUser participant : room.getParticipants()) {
			participant.getSession().sendMessage(responseProtocol);
			participant.getSession().sendMessage(roomMemberListResponseProtocol);
		}
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
