package com.hst.hsttalk.core.model.room;

import com.hst.hsttalk.core.model.messaging.MessageProtocol;
import com.hst.hsttalk.core.model.user.ChatUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.socket.TextMessage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author dlgusrb0808@gmail.com
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public class ChatRoom {
	private final String roomId;
	private final ChatUser roomOwner;
	private final Set<ChatUser> participants = new HashSet<>();

	public boolean isOwner(String participantId) {
		return roomOwner.getSession().getId().equals(participantId);
	}

	public void enter(ChatUser user) {
		participants.add(user);
	}

	public void leave(ChatUser user) {
		participants.remove(user);
	}

	public Set<ChatUser> getParticipants() {
		return participants;
	}

	public void broadcast(MessageProtocol... messages) throws Exception {
		List<TextMessage> textMessages =
				Arrays.stream(messages).map(MessageProtocol::toTextMessage).collect(Collectors.toList());
		for (ChatUser participant : participants) {
			for (TextMessage message : textMessages) {
				participant.getSession().sendMessage(message);
			}
		}
	}
}
