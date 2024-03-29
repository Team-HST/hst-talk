package com.hst.hsttalk.core.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author dlgusrb0808@gmail.com
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public class ChatUser {
	private final String nickname;
	private final WebSocketSession session;

	public ChatUserResponse toResponse(String ownerId) {
		return ChatUserResponse.builder().id(session.getId()).nickname(nickname).owner(ownerId.equals(session.getId())).build();
	}

	@Getter
	@Builder
	public static class ChatUserResponse {
		private final String id;
		private final String nickname;
		private final boolean owner;
	}
}
