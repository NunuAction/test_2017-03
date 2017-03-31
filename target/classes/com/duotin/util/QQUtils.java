package com.duotin.util;

import java.util.regex.Pattern;

public final class QQUtils {

	private static final Pattern PATTERN = Pattern.compile(ConstStrings.REGX_QQ);

	public static boolean is(String qq) {
		if (StringUtils.isNotEmpty(qq)) {
			if (PATTERN.matcher(qq).matches()) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
}
