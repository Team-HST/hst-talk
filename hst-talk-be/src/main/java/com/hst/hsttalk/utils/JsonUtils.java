package com.hst.hsttalk.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

/**
 * @author dlgusrb0808@gmail.com
 */
@UtilityClass
public class JsonUtils {

	private static final ObjectMapper OM = new ObjectMapper();

	/**
	 * Object to Json String
	 * @param o the object
	 * @return json string
	 */
	public static String toJson(Object o) {
		try {
			return OM.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Json String to Object
	 * @param jsonString the json string
	 * @param type the type for object
	 * @return object
	 */
	public static <T> T fromJson(String jsonString, Class<T> type) {
		try {
			return OM.readValue(jsonString, type);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
