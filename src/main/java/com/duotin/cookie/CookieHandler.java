package com.duotin.cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duotin.util.CookieUtils;
import com.duotin.util.ObjectUtils;
import org.springframework.web.util.CookieGenerator;


/**
 * 
 * @author jared
 * 
 * @Description:cookie处理方法类
 * 
 * @date Nov 7, 2014 3:19:39 PM
 * 
 */
public class CookieHandler extends CookieGenerator {

	private boolean httpOnly = false;

	/**
	 * 通过request请求头获取cookie
	 * 
	 * @param request
	 * @return
	 */
	public String getCookieValue(HttpServletRequest request) {
		return CookieUtils.getValue(request, getCookieName());
	}

	@Override
	public void addCookie(HttpServletResponse response, String value) {
		addCookie(response, value, ObjectUtils.nullToDefault(this.getCookieMaxAge(), -1));
	}

	public void addCookie(HttpServletResponse response, String value, int time) {
		CookieUtils.add(response, getCookieName(), value, getCookieDomain(), getCookiePath(), httpOnly, time);
	}

	public boolean isHttpOnly() {
		return httpOnly;
	}

	public void setHttpOnly(boolean httpOnly) {
		this.httpOnly = httpOnly;
	}
}
