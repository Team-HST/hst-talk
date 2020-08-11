package com.hst.hsttalk.core.model.action;

import com.hst.hsttalk.core.model.messaging.ChatMessage;

/**
 * @author dlgusrb0808@gmail.com
 */
public abstract class AbstractAction implements Action {

	private Object protocol;

	public AbstractAction(ChatMessage chatMessage) {
		this.protocol = chatMessage.getBody();
	}

	public Object getProtocol() {
		return protocol;
	}
}
