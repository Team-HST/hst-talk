package com.hst.hsttalk.core.room;

import com.hst.hsttalk.core.model.room.ChatRoom;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
public class RoomManager {

	private Map<String, ChatRoom> chatRooms;

	@PostConstruct
	public void init() {
		this.chatRooms = new ConcurrentHashMap<>();
	}

	public Map<String, ChatRoom> getChatRooms() {
		return chatRooms;
	}

}
