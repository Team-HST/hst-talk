package com.hst.hsttalk.core.model.action.impl;

import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.messaging.MessageProtocol;
import com.hst.hsttalk.core.model.type.MessageType;
import com.hst.hsttalk.core.model.user.ConnectedUserPool;
import com.hst.hsttalk.core.model.user.ConnectedUserPoolAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author dlgusrb0808@gmail.com
 */
@Slf4j
public class InitUserInfoAction implements Action, ConnectedUserPoolAware {
	private ConnectedUserPool pool;

	@Override
	public void doAction(WebSocketSession session, MessageProtocol protocol) throws Exception {
		pool.put(protocol.getPayload().toString(), session);
		session.sendMessage(MessageProtocol.of(MessageType.INIT_USER_INFO, null, session.getId()).toTextMessage());
	}

	@Override
	public void setConnectedUserPool(ConnectedUserPool pool) {
		this.pool = pool;
	}
}
