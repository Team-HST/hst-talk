package com.hst.hsttalk.core.model.room;

import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.Set;

/**
 * @author dlgusrb0808@gmail.com
 */
@Getter
public class ChatRoom {
	private String roomId;
	private Set<WebSocketSession> clients;
}
