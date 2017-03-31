package com.duotin.util;


import com.duotin.page.beans.Page;

import java.util.Date;
import java.util.Set;

/**
 * @author jared
 * @Description:页面使用相关工具方法
 * @date Nov 10, 2014 10:08:25 AM
 */
public class ToolUtils {

	public final static ToolUtils toolUtil = new ToolUtils();

	public static final String ASSERT_JS_KEY = "jsName";

	private ToolUtils() {
		super();
	}

	/**
	 * date类型转换为字符串yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public String getShortTime(Date date) {
		return TimeUtils.toString("yyyy-MM-dd", date);
	}

	/**
	 * date类型转换成特定格式的字符串
	 * 
	 * @param date
	 *            时间
	 * @param pattern
	 *            转换成字符串后的格式
	 * @return
	 */
	public String getShortTime(Date date, String pattern) {
		return TimeUtils.toString(pattern, date);
	}

	/**
	 * 字符串截取
	 * 
	 * @param source
	 *            待截取字符串
	 * @param size
	 *            截取字数
	 * @param suffix
	 *            截取字符后添加后缀
	 * @return
	 */
	public String truncate(String source, Integer size, String suffix) {
		return StringUtils.truncate(source, size, suffix);
	}

	/**
	 * 获取分页html
	 * 
	 * @param page
	 *            分页bean
	 * @param url
	 *            分页前部分链接
	 * @return
	 */
	public <T> String getPageHtml(Page page, String url) {
		StringBuilder pageHtml = new StringBuilder();
		pageHtml.append("<div class=\"pagination\">\n<ul>\n");
		pageHtml.append(buildPage(page, url));
		pageHtml.append("</ul>\n</div>");
		return pageHtml.toString();
	}

	public <T> String pageOfBootstrap3(Page page, String url) {
		StringBuilder pageHtml = new StringBuilder();
		pageHtml.append("<nav>\n<ul class=\"pagination\">\n");
		pageHtml.append(buildPage(page, url));
		pageHtml.append("</ul>\n</nav>");
		return pageHtml.toString();
	}

	private <T> String buildPage(Page page, String url) {
		if (page != null && page.getTotalPageNum() > 1) {
			int index = url.indexOf("?");
			StringBuilder link = new StringBuilder();
			link.append(url);
			if (index > 0) {
				link.append("&size=");
			} else {
				link.append("?size=");
			}
			link.append(page.getPageSize());
			link.append("&page=");
			url = link.toString();

			StringBuilder pageHtml = new StringBuilder();
			if (page.getCurrentPage() > 1) {
				pageHtml.append("<li><a href=\"");
				pageHtml.append(url);
				pageHtml.append("1\">首页</a></li>\n<li><a href=\"");
				pageHtml.append(url);
				pageHtml.append(page.getCurrentPage() - 1);
				pageHtml.append("\">上一页</a></li>\n");
			} else {
				pageHtml.append("<li class=\"disabled\"><a href=\"javascript:void();\">首页</a></li>\n<li class=\"disabled\"><a href=\"javascript:void();\">上一页</a></li>\n");
			}
			if (page.getCurrentPage() >= 3) {
				if (page.getTotalPageNum() >= (page.getCurrentPage() + 2)) {
					pageHtml.append(getPagesLi(page.getCurrentPage() - 2, page.getCurrentPage() + 2, url,
							page.getCurrentPage()));
				} else {
					pageHtml.append(getPagesLi(((page.getTotalPageNum() - 4) > 1) ? (page.getTotalPageNum() - 4) : 1,
							page.getTotalPageNum(), url, page.getCurrentPage()));
				}
			} else {
				pageHtml.append(getPagesLi(1, (page.getTotalPageNum() > 5) ? 5 : page.getTotalPageNum(), url,
						page.getCurrentPage()));
			}
			if (page.getCurrentPage() < page.getTotalPageNum()) {
				pageHtml.append("<li><a href=\"");
				pageHtml.append(url);
				pageHtml.append(page.getCurrentPage() + 1);
				pageHtml.append("\">下一页</a></li>\n");
				pageHtml.append("<li><a href=\"");
				pageHtml.append(url);
				pageHtml.append(page.getTotalPageNum());
				pageHtml.append("\">尾页</a></li>\n");
			} else {
				pageHtml.append("<li class=\"disabled\"><a href=\"javascript:void();\">下一页</a></li>\n<li class=\"disabled\"><a href=\"javascript:void();\">尾页</a></li>\n");
			}
			return pageHtml.toString();
		}
		return "";
	}

	/**
	 * 页面生成
	 * 
	 * @param start
	 *            起始页码
	 * @param end
	 *            结束页码
	 * @param url
	 *            链接
	 * @param currentPage
	 *            当前页码
	 * @return
	 */
	private String getPagesLi(int start, int end, String url, int currentPage) {
		StringBuilder pageHtml = new StringBuilder();
		for (int i = start; i <= end; i++) {
			if (i == currentPage) {
				pageHtml.append("<li class=\"active\"><a href=\"javascript:void();\">");
			} else {
				pageHtml.append("<li><a href=\"");
				pageHtml.append(url);
				pageHtml.append(i);
				pageHtml.append("\">");
			}
			pageHtml.append(i);
			pageHtml.append("</a></li>\n");
		}

		return pageHtml.toString();
	}

	/**
	 * 获取js静态资源html
	 * 
	 * @param key
	 * @return
	 */
	public String getAssetsJsHtml(String key) {
		return AssetsUtils.getJsResourceByKey(key);
	}

	/**
	 * 获取css静态资源html
	 * 
	 * @param key
	 * @return
	 */
	public String getAssetsCssHtml(String key) {
		return AssetsUtils.getCssResourceByKey(key);
	}

	public String escapeHtml(String str) {
		if (StringUtils.isNotEmpty(str)) {
			return WebUtils.escapeHtml(str);
		}
		return ConstStrings.EMPTY;
	}

	/**
	 * 判断通过","分隔的字符串中，是否包含目标字符串
	 * 
	 * @param source
	 *            通过,分隔的字符串
	 * @param target
	 *            需要查找的字符串
	 * @return
	 */
	public boolean isContain(String source, String target) {
		if (StringUtils.isNotEmpty(source, target)) {
			Set<String> set = CollectionUtils.parseSet(source);
			if (CollectionUtils.isNotEmpty(set)) {
				return set.contains(target);
			}
		}
		return Boolean.FALSE;
	}

}
