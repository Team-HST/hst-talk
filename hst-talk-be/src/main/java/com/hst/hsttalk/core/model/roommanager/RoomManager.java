package com.hst.hsttalk.core.model.roommanager;

import com.hst.hsttalk.core.model.room.ChatRoom;
import com.hst.hsttalk.core.model.user.ConnectedUserPool;
import com.hst.hsttalk.core.model.user.ConnectedUserPoolAware;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

/**
 * @author dlgusrb0808@gmail.com
 */
public interface RoomManager extends ConnectedUserPoolAware {

	/**
	 * Return chat room container instance
	 *
	 * @return chat room container instance
	 */
	Map<String, ChatRoom> getChatRoomContainer();

	/**
	 * Create new chat room
	 *
	 * @param session room creator
	 * @return created room
	 */
	ChatRoom createRoom(WebSocketSession session);

	/**
	 * Get chat room
	 *
	 * @param roomId roomId
	 * @return chat room
	 */
	ChatRoom getRoom(String roomId);

	@Override
	default void setConnectedUserPool(ConnectedUserPool pool) {
		// Do nothing
	}
}
