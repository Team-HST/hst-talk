package com.hst.hsttalk.core.model.messaging;

import com.hst.hsttalk.core.model.type.MessageType;
import com.hst.hsttalk.utils.JsonUtils;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.socket.TextMessage;

/**
 * @author dlgusrb0808@gmail.com
 */
@Getter
@ToString
public class MessageProtocol {
	private MessageType messageType;
	private String roomId;
	private Object payload;

	public TextMessage toTextMessage() {
		return new TextMessage(JsonUtils.toJson(this));
	}

	public static MessageProtocol errorResponse(Object body) {
		return of(MessageType.SYSTEM_ERROR, null, body);
	}

	public static MessageProtocol fromType(MessageType messageType) {
		return of(messageType, null, null);
	}

	public static MessageProtocol of(MessageType messageType, String roomId, Object payload) {
		MessageProtocol protocol = new MessageProtocol();
		protocol.messageType = messageType;
		protocol.roomId = roomId;
		protocol.payload = payload;
		return protocol;
	}

	public static MessageProtocol from(String payload) {
		return JsonUtils.fromJson(payload, MessageProtocol.class);
	}

}
