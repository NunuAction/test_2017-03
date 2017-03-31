package com.duotin.controller.handler;

import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * 
 * @author ganlv
 * @date 2013-10-17 下午7:15:30
 */
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.PropertyEditorRegistrar#registerCustomEditors
	 * (org.springframework.beans.PropertyEditorRegistry)
	 */
	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		registry.registerCustomEditor(Date.class, new DateTypeEditor());
	}

}
