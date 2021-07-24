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
import org.springframework.web.socket.WebSocketSession;

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

		room.leave(user);

		if (room.getParticipants().isEmpty()) {
			roomManager.destroyRoom(roomId);
		} else {
			MessageProtocol chatProtocol =
					MessageProtocol.builder().messageType(MessageType.CHAT).roomId(roomId).payload(ChatResponse.of(
							"SYSTEM", String.format("%s님이 퇴장하셨습니다ㅠㅠ", user.getNickname()), false)).build();
			MessageProtocol roomMemberRefreshProtocol =
					MessageProtocol.builder().messageType(MessageType.GET_ROOM_MEMBER_LIST).roomId(roomId).payload(RoomMemberListResponse.from(room)).build();
			room.broadcast(chatProtocol, roomMemberRefreshProtocol);
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
