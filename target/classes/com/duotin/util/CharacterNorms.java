package com.duotin.util;

public final class CharacterNorms {

	public static final char[] CACHE = new char[Character.MAX_VALUE];
	static {
		for (int i = 1; i < Character.MAX_VALUE; i++) {
			CACHE[i] = CharacterUtils.toLowerCase(CharacterUtils.toHalfWidth((char) i));
		}
	}

	public static char[] normalize(char[] chars) {
		if (chars != null && chars.length > 0) {
			for (int i = 0; i < chars.length; i++) {
				chars[i] = CACHE[chars[i]];
			}
		}
		return chars;
	}
}
