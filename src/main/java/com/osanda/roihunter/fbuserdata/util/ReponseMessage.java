package com.osanda.roihunter.fbuserdata.util;

import java.util.HashMap;

public class ReponseMessage {

	public static HashMap<String, Object> createMessage(Object message) {
		HashMap<String, Object> h = new HashMap<String, Object>() {

			private static final long serialVersionUID = -206519932607308207L;

			{
				put("message", message);
			}
		};
		return h;
	}// createMessage()

	public static HashMap<String, Object> error(Object message) {
		HashMap<String, Object> h = new HashMap<String, Object>() {

			private static final long serialVersionUID = -8190644218885561360L;

			{
				put("error", message);
			}
		};
		return h;
	}// error()

}
