package com.hst.hsttalk.core.model.action.impl;

import com.hst.hsttalk.core.SessionContextHolder;
import com.hst.hsttalk.core.model.action.AbstractAction;
import com.hst.hsttalk.core.model.messaging.ChatMessage;
import com.hst.hsttalk.core.model.room.ChatRoom;
import com.hst.hsttalk.core.model.type.MessageType;
import com.hst.hsttalk.core.room.RoomManager;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dlgusrb0808@gmail.com
 */
public class GetRoomMemberListAction extends AbstractAction {

	public GetRoomMemberListAction(ChatMessage chatMessage) {
		super(chatMessage);
	}

	@Override
	public void doAction(RoomManager roomManager) throws Exception {
		WebSocketSession session = SessionContextHolder.getCurrentUser();

		String roomId = (String) getProtocol();
		ChatRoom chatRoom = roomManager.getRoom(roomId);
		Map<String, Object> response = new HashMap<>();
		response.put("owner", chatRoom.getRoomOwner().getId());
		response.put("participants",
				chatRoom.getParticipants().stream().map(WebSocketSession::getId).collect(Collectors.toList()));
		session.sendMessage(ChatMessage.of(MessageType.GET_ROOM_MEMBER_LIST, response).toTextMessage());
	}
}
