package com.duotin.velocity;

import static org.springframework.core.io.ResourceLoader.CLASSPATH_URL_PREFIX;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.ToolboxFactory;
import org.apache.velocity.tools.config.XmlFactoryConfiguration;
import org.apache.velocity.tools.view.ViewToolContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;

/**
 * 增加 {@link #setToolboxConfigLocation(String)} 值对CLASSPATH的支持
 *
 * @author lrh
 * @create 2015年1月15日下午23:10:20
 * 
 *  */
public class VelocityLayoutViewExt extends VelocityLayoutView {

    @Override
    protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String toolboxConfigLocation = getToolboxConfigLocation();

		if (toolboxConfigLocation != null && toolboxConfigLocation.startsWith(CLASSPATH_URL_PREFIX)) {
            ViewToolContext velocityContext = new ViewToolContext(getVelocityEngine(), request, response, getServletContext());
            //velocityContext.(model);

            XmlFactoryConfiguration cfg = new XmlFactoryConfiguration(true);

            ResourceLoader resourceLoader = new DefaultResourceLoader();

            Resource resource = resourceLoader.getResource(toolboxConfigLocation);

            cfg.read(resource.getURL());

            ToolboxFactory factory = cfg.createFactory();
            velocityContext.addToolbox(factory.createToolbox(Scope.APPLICATION));
            velocityContext.addToolbox(factory.createToolbox(Scope.REQUEST));
            velocityContext.addToolbox(factory.createToolbox(Scope.SESSION));

            return velocityContext;
		} else {
            return super.createVelocityContext(model, request, response);
        }
    }
}