package com.hst.hsttalk.core.room;

import com.hst.hsttalk.core.model.room.ChatRoom;
import com.hst.hsttalk.utils.RandomUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
public class RoomManager {

	private static final int ROOM_ID_LENGTH = 8;

	private Map<String, ChatRoom> chatRoomContainer;

	@PostConstruct
	public void init() {
		this.chatRoomContainer = new ConcurrentHashMap<>();
	}

	/**
	 * Return chat room container instance
	 *
	 * @return chat room container instance
	 */
	public Map<String, ChatRoom> getChatRoomContainer() {
		return chatRoomContainer;
	}

	/**
	 * Create new chat room
	 *
	 * @param session room creator
	 * @return created roomId
	 */
	public String createRoom(WebSocketSession session) {
		String roomId = generateRandomRoomId();
		chatRoomContainer.put(roomId, ChatRoom.of(roomId, session));
		return roomId;
	}

	/**
	 * Get chat room
	 *
	 * @param roomId roomId
	 * @return chat room
	 */
	public ChatRoom getRoom(String roomId) {
		return chatRoomContainer.get(roomId);
	}

	private String generateRandomRoomId() {
		return RandomUtils.randomString(ROOM_ID_LENGTH);
	}

}
