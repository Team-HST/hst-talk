package com.hst.hsttalk.core.model.room;

import com.hst.hsttalk.core.model.user.ChatUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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
}
