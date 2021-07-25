package com.hst.hsttalk.core.model.roommanager;

import com.hst.hsttalk.core.model.room.ChatRoom;
import com.hst.hsttalk.core.model.user.ChatUser;
import com.hst.hsttalk.utils.RandomUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
public class InMemoryRoomManager implements RoomManager {

	private static final int ROOM_ID_LENGTH = 8;

	private final Map<String, ChatRoom> chatRoomContainer = new ConcurrentHashMap<>();

	@Override
	public Map<String, ChatRoom> getChatRoomContainer() {
		return this.chatRoomContainer;
	}

	@Override
	public ChatRoom createRoom(ChatUser owner) {
		ChatRoom createdRoom = ChatRoom.of(generateRandomRoomId(), owner);
		getChatRoomContainer().put(createdRoom.getRoomId(), createdRoom);
		return createdRoom;
	}

	@Override
	public ChatRoom getRoom(String roomId) {
		return Optional.ofNullable(this.chatRoomContainer.get(roomId)).orElseThrow(() -> new IllegalArgumentException(String.format("Can not find room. id: %s", roomId)));
	}

	private String generateRandomRoomId() {
		return RandomUtils.randomString(ROOM_ID_LENGTH);
	}

	@Override
	public void destroyRoom(String roomId) {
		chatRoomContainer.remove(roomId);
	}
}
