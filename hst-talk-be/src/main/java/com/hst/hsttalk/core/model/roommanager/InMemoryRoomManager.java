package com.hst.hsttalk.core.model.roommanager;

import com.hst.hsttalk.core.model.room.ChatRoom;
import com.hst.hsttalk.core.model.user.ConnectedUserPool;
import com.hst.hsttalk.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
public class InMemoryRoomManager implements RoomManager {

	private static final int ROOM_ID_LENGTH = 8;

	private final Map<String, ChatRoom> chatRoomContainer = new ConcurrentHashMap<>();
	private ConnectedUserPool pool;

	@Override
	public Map<String, ChatRoom> getChatRoomContainer() {
		return this.chatRoomContainer;
	}

	@Override
	public ChatRoom createRoom(WebSocketSession session) {
		ChatRoom createdRoom = ChatRoom.of(generateRandomRoomId(), pool.get(session.getId()));
		getChatRoomContainer().put(createdRoom.getRoomId(), createdRoom);
		return createdRoom;
	}

	@Override
	public ChatRoom getRoom(String roomId) {
		return this.chatRoomContainer.get(roomId);
	}

	private String generateRandomRoomId() {
		return RandomUtils.randomString(ROOM_ID_LENGTH);
	}

	@Override
	@Autowired
	public void setConnectedUserPool(ConnectedUserPool pool) {
		this.pool = pool;
	}
}
