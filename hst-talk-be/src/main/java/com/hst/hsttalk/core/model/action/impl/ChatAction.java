package com.hst.hsttalk.core.model.action.impl;

import com.hst.hsttalk.core.model.action.AbstractAction;
import com.hst.hsttalk.core.model.messaging.ChatMessage;
import com.hst.hsttalk.core.room.RoomManager;

/**
 * @author dlgusrb0808@gmail.com
 */
public class ChatAction extends AbstractAction {

	public ChatAction(ChatMessage chatMessage) {
		super(chatMessage);
	}

	@Override
	public void doAction(RoomManager roomManager) {
		System.out.println("Do Chat Action");
	}

}
