package com.duotin.controller.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class LoggableExceptionResolver extends SimpleMappingExceptionResolver {

	private static final Logger log = LoggerFactory.getLogger(LoggableExceptionResolver.class);

    /**
     * 最早的Order
     */
    private int order = -100;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver
	 * #logException(java.lang.Exception, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void logException(Exception ex, HttpServletRequest request) {
		// super.logException(ex, request);
		String uri = request.getRequestURI();
		log.error("Uncaught exception for  at [" + request.getMethod() + ":" + uri + "]", ex);
	}

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public void setOrder(int order) {
        this.order = order;
    }
}
