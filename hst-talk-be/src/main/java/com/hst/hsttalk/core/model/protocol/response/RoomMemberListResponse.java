package com.hst.hsttalk.core.model.protocol.response;

import com.hst.hsttalk.core.model.user.ChatUser;
import com.hst.hsttalk.core.model.user.ChatUser.ChatUserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author dlgusrb0808@gmail.com
 */
@Getter
@AllArgsConstructor(staticName = "of")
public class RoomMemberListResponse {
	private final ChatUserResponse owner;
	private final List<RoomMemberResponse> participants;

	@Getter
	public static class RoomMemberResponse {
		private boolean me;
		private ChatUserResponse userInfo;

		public static RoomMemberResponse of(String id, ChatUser user) {
			RoomMemberResponse response = new RoomMemberResponse();
			response.userInfo = user.toResponse();
			response.me = response.userInfo.getId().equals(id);
			return response;
		}
	}
}
