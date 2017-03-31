package com.duotin.controller.handler;

import com.duotin.util.JsonUtils;

import java.util.Map;


/**
 * 自定义的解析参数的
 * @author lrh
 * @create 2015年4月10日下午10:43:41
 */
public abstract class CustomResolverParam {
	
	protected Object parse(Map<String,String> requestParam){
		try{
			return JsonUtils.toObject(this.getClass(), JsonUtils.toString(requestParam));
		}catch(Exception e){
			
		}
		return null;
	}
}
