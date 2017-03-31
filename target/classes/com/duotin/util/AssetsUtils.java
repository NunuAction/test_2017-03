package com.duotin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author jared
 * 
 * @Description:前段相关工具类
 * 
 * @date Dec 11, 2014 10:03:30 AM
 * 
 */
@SuppressWarnings("unchecked")
public class AssetsUtils {

	public static final String ASSETS_HOST = "http://c4.duotin.com/2014";
	public static final String ASSETS_HOST_DEV = "http://assets.danxinben.com";

	public static final String ASSETS_TYPE_CSS = "css";
	public static final String ASSETS_TYPE_JS = "js";

	public static Map<String, String> _cssResourceMap = new HashMap<String, String>();
	public static Map<String, String> _jsResourceMap = new HashMap<String, String>();

	/**
	 * 获取静态资源文件json
	 * 
	 * @param isDev
	 * @return
	 */
	public static String getAssetsResourceMapJson(boolean isDev) {
		StringBuilder url = new StringBuilder();
		url.append(getAssetsHost(isDev));
		url.append("/web/resource_map.json?t=");
		url.append(System.currentTimeMillis());
		String json = HttpUtils.getHtmlGet(url.toString());
		if (StringUtils.isNotEmpty(json)) {
			return json;
		}
		return ConstStrings.EMPTY;
	}

	/**
	 * 获取podcast相关的静态资源
	 *
	 * @param isDev
	 * @return
	 */
	public static String getAssetsResourceOfPodcastMapJson(boolean isDev) {
		StringBuilder url = new StringBuilder();
		url.append(getAssetsHost(isDev));
		url.append("/podcast/resource_map.json?t=");
		url.append(System.currentTimeMillis());
		String json = HttpUtils.getHtmlGet(url.toString());
		if (StringUtils.isNotEmpty(json)) {
			return json;
		}
		return ConstStrings.EMPTY;
	}

	/**
	 * 获取assets的host
	 *
	 * @param isDev
	 * @return
	 */
	public static String getAssetsHost(boolean isDev) {
		return isDev ? ASSETS_HOST_DEV : ASSETS_HOST;
	}

	/**
	 * 构建静态资源文件map
	 *
	 * @param json
	 * @param isDev
	 */
	public static void buildAssetsResourceMap(String json, boolean isDev, String prefix) {
		if (StringUtils.isNotEmpty(json)) {
			Map<String, Object> map = JsonUtils.toMap(json);
			if (CollectionUtils.isNotEmpty(map)) {
				Map<String, String> cssResourceMap = buildResourceMap(
						(List<Map<String, Object>>) map.get("cssMapJson"), isDev, ASSETS_TYPE_CSS, prefix);
				if (CollectionUtils.isNotEmpty(cssResourceMap)) {
					_cssResourceMap.putAll(cssResourceMap);
				}
				Map<String, String> jsResourceMap = buildResourceMap((List<Map<String, Object>>) map.get("jsMapJson"),
						isDev, ASSETS_TYPE_JS, prefix);
				if (CollectionUtils.isNotEmpty(jsResourceMap)) {
					_jsResourceMap.putAll(jsResourceMap);
				}
			}
		}
	}

	/**
	 * 构建单类静态资源文件map
	 *
	 * @param List
	 * @param isDev
	 * @param type
	 * @param prefix
	 * @return
	 */
	private static Map<String, String> buildResourceMap(List<Map<String, Object>> List, boolean isDev, String type,
			String prefix) {
		Map<String, String> result = new HashMap<String, String>();
		if (CollectionUtils.isNotEmpty(List)) {
			String valueKey = isDev ? "exploded" : "packaged";
			for (Map<String, Object> item : List) {
				if (item != null && item.get("key") != null && item.get(valueKey) != null) {
					Object object = item.get(valueKey);
					List<String> values = new ArrayList<String>();
					if (object instanceof String) {
						values.add((String) object);
					} else {
						values = (java.util.List<String>) object;
					}

					result.put(
							(StringUtils.isNotEmpty(prefix) ? prefix : ConstStrings.EMPTY) + (String) item.get("key"),
							buildHtmlResourceMap(values, isDev, type));
				}
			}
		}
		return result;
	}

	/**
	 * 构建静态资源html
	 *
	 * @param list
	 * @param isDev
	 * @param type
	 * @return
	 */
	private static String buildHtmlResourceMap(List<String> list, boolean isDev, String type) {
		StringBuilder html = new StringBuilder();
		if (CollectionUtils.isNotEmpty(list) && StringUtils.isNotEmpty(type)) {
			String head = null;
			String foot = null;
			if (type.equals(ASSETS_TYPE_CSS)) {
				head = "<link rel=\"stylesheet\" href=\"";
				foot = "\"/>";
			} else {
				head = "<script data-main=\"";
				foot = "\" src=\"http://c4.duotin.com/2014/podcast/bower_components/requirejs/require.js\"></script>";
			}

			for (String item : list) {
				if (StringUtils.isNotEmpty(item)) {
					html.append(head);
					html.append(getAssetsHost(isDev));
					html.append(item);
					html.append(foot);
					html.append("\r\n");
				}
			}
		}
		return html.toString();
	}

	/**
	 * 获取js静态资源
	 *
	 * @param key
	 * @return
	 */
	public static String getJsResourceByKey(String key) {
		if (StringUtils.isNotEmpty(key)) {
			return _jsResourceMap.get(key);
		}
		return ConstStrings.EMPTY;
	}

	/**
	 * 获取css静态资源
	 * 
	 * @param key
	 * @return
	 */
	public static String getCssResourceByKey(String key) {
		if (StringUtils.isNotEmpty(key)) {
			return _cssResourceMap.get(key);
		}
		return ConstStrings.EMPTY;
	}
}
