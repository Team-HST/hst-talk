package com.hst.hsttalk.core.model.messaging;

import com.hst.hsttalk.core.model.type.MessageType;
import com.hst.hsttalk.utils.JsonUtils;
import lombok.Getter;

/**
 * @author dlgusrb0808@gmail.com
 */
@Getter
public class ChatMessage {
	private MessageType messageType;
	private Object body;

	public static ChatMessage systemChat(Object body) {
		return of(MessageType.SYSTEM_CHAT, body);
	}

	public static ChatMessage of(MessageType messageType, Object body) {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.messageType = messageType;
		chatMessage.body = body;
		return chatMessage;
	}

	public static ChatMessage from(String payload) {
		return JsonUtils.fromJson(payload, ChatMessage.class);
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}
