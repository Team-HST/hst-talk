package com.hst.hsttalk.core.model.action;

import com.hst.hsttalk.core.room.RoomManager;

/**
 * @author dlgusrb0808@gmail.com
 */
public interface Action {

	/**
	 * Define some job for Message
	 * @param roomManager the roomManager
	 */
	void doAction(RoomManager roomManager);

	/**
	 * get protocol
	 * @return
	 */
	Object getProtocol();
}
