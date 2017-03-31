package com.duotin.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public final class EmailUtils {
	private static final Set<String> INVALID_SUFFIX = new HashSet<String>();
	static {
		INVALID_SUFFIX.add("e.dxy.cn");
		INVALID_SUFFIX.add("dxyer_bis.com");
		INVALID_SUFFIX.add("dxytest.com");
		INVALID_SUFFIX.add("dxytest2.com");
		INVALID_SUFFIX.add("dxy_test.com");

		INVALID_SUFFIX.add("yahoo.cn");
		INVALID_SUFFIX.add("yahoo.com.cn");
		INVALID_SUFFIX.add("sian.com");
		INVALID_SUFFIX.add("souhu.com");
		INVALID_SUFFIX.add("qq.con");
		INVALID_SUFFIX.add("qq.cn");
		INVALID_SUFFIX.add("qq.cm");
		INVALID_SUFFIX.add("qq.com.cn");
		INVALID_SUFFIX.add("sohu.com.cn");
		INVALID_SUFFIX.add("163.cn");
		INVALID_SUFFIX.add("163.con");
		INVALID_SUFFIX.add("xx.xx");
		INVALID_SUFFIX.add("com.cn");
	}

	private static final Pattern PATTERN = Pattern.compile(ConstStrings.REGX_EMAIL);

	public static boolean is(String email) {
		if (email != null && email.length() > 0 && email.length() < 65) {
			if (PATTERN.matcher(email).matches()) {
				return !INVALID_SUFFIX.contains(email.substring(email.indexOf('@') + 1));
			}
		}
		return false;
	}

	public static boolean not(String email) {
		return !is(email);
	}

	private static final Map<String, String> MAIL_HOME = new HashMap<String, String>();
	static {
		MAIL_HOME.put("gmail.com", "https://mail.google.com/");
		MAIL_HOME.put("live.cn", "http://mail.live.com/");
		MAIL_HOME.put("126.com", "http://smart.mail.126.com/");
		MAIL_HOME.put("sina.com", "http://mail.sina.cn/");
		MAIL_HOME.put("sina.com.cn", "http://mail.sina.cn/");
	}

	public static String getSuffix(String email) {
		if (is(email)) {
			try {
				return email.substring(email.indexOf('@') + 1);
			} catch (Throwable e) {
			}
		}

		return ConstStrings.EMPTY;
	}

	private static final Map<String, String> MAIL_HOSTS = new HashMap<String, String>();
	static {
		MAIL_HOSTS.put("gmail.com", "https://mail.google.com/mail/mu/");
		MAIL_HOSTS.put("live.com", "http://mail.live.com/m/");
		MAIL_HOSTS.put("live.cn", "http://mail.live.com/m/");
		MAIL_HOSTS.put("126.com", "http://smart.mail.126.com/");
		MAIL_HOSTS.put("163.com", "http://smart.mail.163.com/");
		MAIL_HOSTS.put("163.net", "http://mail.163.net/");
		MAIL_HOSTS.put("188.com", "http://mail.188.com/");
		MAIL_HOSTS.put("sina.com", "http://mail.sina.cn/");
		MAIL_HOSTS.put("hotmail.com", "http://mail.live.com/m/");
		MAIL_HOSTS.put("yahoo.com.cn", "http://mail.cn.yahoo.com/");
		MAIL_HOSTS.put("yahoo.cn", "http://mail.cn.yahoo.com/");
		MAIL_HOSTS.put("sohu.com", "http://m.mail.sohu.com/");
		MAIL_HOSTS.put("21cn.com", "http://mail.21cn.com/");
		MAIL_HOSTS.put("eyou.com", "http://www.eyou.com/");
		MAIL_HOSTS.put("sina.com.cn", "http://mail.sina.cn/");
		MAIL_HOSTS.put("qq.com", "http://w.mail.qq.com/");
		MAIL_HOSTS.put("tom.com", "http://mail.tom.com/");
		MAIL_HOSTS.put("sogou.com", "http://mail.sogou.com/");
		MAIL_HOSTS.put("aol.com", "http://mail.aol.com/");
		MAIL_HOSTS.put("dxyer.com", "http://mail.dxy.com/");
	}

	public static String getHost(String email) {
		if (StringUtils.isNotEmpty(email)) {
			email = StringUtils.normalize(email);
			String suffix = extractSupplier(email);
			return StringUtils.emptyToDefault(MAIL_HOSTS.get(suffix), "http://mail." + suffix);
		}
		return "/";
	}

	public static String extractSupplier(String email) {
		try {
			return email.substring(email.indexOf('@') + 1);
		} catch (Throwable e) {
		}

		return ConstStrings.EMPTY;
	}
}
