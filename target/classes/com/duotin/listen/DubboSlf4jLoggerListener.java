package com.duotin.listen;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author penglai
 * @Date 2017/1/22.
 */
public class DubboSlf4jLoggerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //设置dubbo的日志为slf4j
        System.setProperty("dubbo.application.logger", "slf4j");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
