package com.hst.hsttalk.core.model.protocol.response;

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
	private final List<ChatUserResponse> participants;
}
