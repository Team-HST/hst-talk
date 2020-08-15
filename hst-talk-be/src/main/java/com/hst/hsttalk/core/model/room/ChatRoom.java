package com.hst.hsttalk.core.model.room;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dlgusrb0808@gmail.com
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public class ChatRoom {
	private final String roomId;
	private final WebSocketSession roomOwner;
	private Set<WebSocketSession> participants = new HashSet<>();
}
