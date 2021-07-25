package com.hst.hsttalk.core.model.protocol.response;

import com.hst.hsttalk.core.model.room.ChatRoom;
import com.hst.hsttalk.core.model.user.ChatUser.ChatUserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dlgusrb0808@gmail.com
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class RoomMemberListResponse {
	private final List<ChatUserResponse> participants;

	public static RoomMemberListResponse from(ChatRoom room) {
		String ownerId = room.getRoomOwner().getSession().getId();
		return of(room.getParticipants().stream().map(e -> e.toResponse(ownerId)).collect(Collectors.toList()));
	}
}
