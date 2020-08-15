package com.hst.hsttalk.core.action.factory;

import com.hst.hsttalk.core.model.action.Action;
import com.hst.hsttalk.core.model.messaging.ChatMessage;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;

/**
 * @author dlgusrb0808@gmail.com
 */
@Component
public class DynamicActionFactory implements ActionFactory {

	@Override
	public Action createAction(ChatMessage chatMessage) {
		Class<? extends Action> actionClass = chatMessage.getMessageType().getActionClass();
		try {
			Constructor<? extends Action> actionConstructor = actionClass.getConstructor(ChatMessage.class);
			return actionConstructor.newInstance(chatMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
