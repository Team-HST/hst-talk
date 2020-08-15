package com.hst.hsttalk.core.action.factory;

import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.action.impl.ChatAction;
import com.hst.hsttalk.core.model.action.impl.JoinAction;
import com.hst.hsttalk.core.model.action.impl.LeaveAction;
import com.hst.hsttalk.core.model.messaging.ChatMessage;

/**
 * @author dlgusrb0808@gmail.com
 */
public class DefaultActionFactory implements ActionFactory {

	@Override
	public Action createAction(ChatMessage chatMessage) {
		Action action;
		switch (chatMessage.getMessageType()) {
			case CHAT:
				action = new ChatAction(chatMessage);
				break;
			case JOIN:
				action = new JoinAction(chatMessage);
				break;
			case LEAVE:
				action = new LeaveAction(chatMessage);
				break;
			default:
				throw new UnsupportedOperationException("Not supported action type");
		}
		return action;
	}

}
