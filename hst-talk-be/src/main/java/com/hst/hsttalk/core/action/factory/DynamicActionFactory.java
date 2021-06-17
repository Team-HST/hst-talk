package com.hst.hsttalk.core.action.factory;

import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.messaging.MessageProtocol;
import com.hst.hsttalk.core.model.roommanager.RoomManager;
import com.hst.hsttalk.core.model.roommanager.RoomManagerAware;
import com.hst.hsttalk.core.model.user.ConnectedUserPool;
import com.hst.hsttalk.core.model.user.ConnectedUserPoolAware;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
@RequiredArgsConstructor
public class DynamicActionFactory implements ActionFactory {

	private final ConnectedUserPool pool;
	private final RoomManager roomManager;

	@Override
	public Action createAction(MessageProtocol protocol) {
		Class<? extends Action> actionClass = protocol.getMessageType().getActionClass();
		try {
			Constructor<? extends Action> actionConstructor = actionClass.getConstructor();
			Action actionInstance = actionConstructor.newInstance();
			processSystemComponentAware(actionInstance);
			return actionInstance;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void processSystemComponentAware(Action actionInstance) {
		if (actionInstance instanceof RoomManagerAware) {
			((RoomManagerAware) actionInstance).setRoomManager(roomManager);
		}
		if (actionInstance instanceof ConnectedUserPoolAware) {
			((ConnectedUserPoolAware) actionInstance).setConnectedUserPool(pool);
		}
	}

}
