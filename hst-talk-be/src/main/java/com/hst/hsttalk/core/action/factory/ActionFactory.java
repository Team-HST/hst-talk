package com.hst.hsttalk.core.action.factory;

import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.messaging.ChatMessage;

/**
 * @author dlgusrb0808@gmail.com
 */
public interface ActionFactory {

	/**
	 * Create Action from ChatMessage
	 * @param chatMessage the chatMessage
	 * @return action
	 */
	Action createAction(ChatMessage chatMessage);

}
