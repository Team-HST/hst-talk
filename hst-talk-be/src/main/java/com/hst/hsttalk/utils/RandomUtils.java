package com.hst.hsttalk.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Random;

/**
 * @author dlgusrb0808@gmail.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomUtils {
	private static final String CODE_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
	private static final int CODE_POOL_SIZE = CODE_POOL.length();
	private static final Random RANDOM = new Random();

	/**
	 * Create random string
	 *
	 * @param length length of string
	 * @return random string
	 */
	public static String randomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int index = RANDOM.nextInt(CODE_POOL_SIZE);
			sb.append(CODE_POOL.charAt(index));
		}
		return sb.toString();
	}

}
