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
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.stream.Collectors;

/**
 * Copyright 2021 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author hyungyu.lee@nhn.com
 * @date 2021-07-14
 */
public class LeaveRoomAction implements Action, RoomManagerAware, ConnectedUserPoolAware {

	private RoomManager roomManager;
	private ConnectedUserPool pool;

	@Override
	public void doAction(WebSocketSession session, MessageProtocol protocol) throws Exception {
		String roomId = protocol.getRoomId();
		ChatUser user = pool.get(SessionContextHolder.getCurrentSession().getId());
		ChatRoom room = roomManager.getRoom(protocol.getRoomId());
		String ownerId = room.getRoomOwner().getSession().getId();

		room.leave(user);
		pool.remove(session);

		RoomMemberListResponse roomMemberListResponse =
				RoomMemberListResponse.of(room.getParticipants().stream().map(e -> e.toResponse(ownerId)).collect(Collectors.toList()));

		// 해당 방에 노티
		for (ChatUser participant : room.getParticipants()) {
			String chatMessage = String.format("%s님이 퇴장하셨습니다ㅠㅠ", user.getNickname());
			ChatResponse response = ChatResponse.of("SYSTEM", chatMessage, false);
			TextMessage responseProtocol =
					MessageProtocol.of(MessageType.CHAT, protocol.getRoomId(), response).toTextMessage();
			participant.getSession().sendMessage(responseProtocol);
			participant.getSession().sendMessage(MessageProtocol.of(MessageType.GET_ROOM_MEMBER_LIST, roomId,
					roomMemberListResponse).toTextMessage());
		}
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
