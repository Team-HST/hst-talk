package com.hst.hsttalk.core.model.action.impl;

import com.hst.hsttalk.core.model.action.AbstractAction;
import com.hst.hsttalk.core.model.messaging.ChatMessage;
import com.hst.hsttalk.core.room.RoomManager;

/**
 * @author dlgusrb0808@gmail.com
 */
public class JoinAction extends AbstractAction {

	public JoinAction(ChatMessage chatMessage) {
		super(chatMessage);
	}

	@Override
	public void doAction(RoomManager roomManager) {
		System.out.println("Do Join Action");
	}

}
