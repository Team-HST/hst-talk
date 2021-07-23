package com.hst.hsttalk.core.controller;

import com.hst.hsttalk.core.action.ActionDispatcher;
import com.hst.hsttalk.core.model.messaging.MessageProtocol;
import com.hst.hsttalk.core.model.room.ChatRoom;
import com.hst.hsttalk.core.model.roommanager.RoomManager;
import com.hst.hsttalk.core.model.type.MessageType;
import com.hst.hsttalk.core.model.user.ChatUser;
import com.hst.hsttalk.core.model.user.ConnectedUserPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
@Slf4j
public class ChatSystemFrontController extends TextWebSocketHandler {

	private final ActionDispatcher actionDispatcher;
	private final RoomManager roomManager;
	private final ConnectedUserPool pool;

	public ChatSystemFrontController(ActionDispatcher actionDispatcher, RoomManager roomManager,
									 ConnectedUserPool pool) {
		this.actionDispatcher = actionDispatcher;
		this.roomManager = roomManager;
		this.pool = pool;
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
		MessageProtocol message = MessageProtocol.from(textMessage.getPayload());
		if (message == null) {
			session.sendMessage(MessageProtocol.errorResponse("Not readable message").toTextMessage());
			return;
		}
		try {
			actionDispatcher.dispatch(session, message);
		} catch (Exception e) {
			log.error(String.format("Fail to dispatch action. protocol: %s", message), e);
			session.sendMessage(MessageProtocol.errorResponse(String.format("%s: %s", e.getClass(), e.getMessage())).toTextMessage());
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 커넥션 끊긴 유저가 속한 방 찾아 LEAVE_ROOM 액션 실행
		ChatUser user = pool.get(session.getId());
		for (ChatRoom room : roomManager.getChatRoomContainer().values()) {
			if (room.getParticipants().contains(user)) {
				actionDispatcher.dispatch(session, MessageProtocol.of(MessageType.LEAVE_ROOM, room.getRoomId(), null));
			}
		}
		// 유저 풀에서 제거
		pool.remove(session);
	}
}
