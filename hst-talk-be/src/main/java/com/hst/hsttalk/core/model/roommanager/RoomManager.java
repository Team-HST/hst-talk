package com.hst.hsttalk.core.model.roommanager;

import com.hst.hsttalk.core.model.room.ChatRoom;
import com.hst.hsttalk.core.model.user.ChatUser;

import java.util.Map;

/**
 * @author dlgusrb0808@gmail.com
 */
public interface RoomManager {

	/**
	 * Return chat room container instance
	 *
	 * @return chat room container instance
	 */
	Map<String, ChatRoom> getChatRoomContainer();

	/**
	 * Create new chat room
	 *
	 * @param owner the room creator
	 * @return created room
	 */
	ChatRoom createRoom(ChatUser owner);

	/**
	 * Get chat room
	 *
	 * @param roomId the roomId
	 * @return chat room
	 */
	ChatRoom getRoom(String roomId);

	/**
	 * Destroy empty room
	 *
	 * @param roomId the roomId
	 */
	void destroyRoom(String roomId);
}
