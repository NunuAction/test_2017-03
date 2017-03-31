package com.duotin.velocity.module;

import java.io.IOException;
import java.io.Writer;

import com.duotin.velocity.VelocityLayoutViewResolverExt;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;


/**
 * 视图模块
 *
 * @author lrh
 * @create 2015年1月15日下午23:10:20
 */
public abstract class AbstractViewModule implements ViewModule {

    /**
     * 默认没有显示页面模板
     */
    private static final String DEFAULT_VIEW_NAME = "NOVIEW";

    @Autowired
    private VelocityLayoutViewResolverExt viewResolver;

    @Autowired
    private VelocityConfigurer velocityConfig;

    @Override
    public boolean render(InternalContextAdapter context, Writer writer, ViewModuleParameter parameter) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        context.put("cmodule", parameter);

        addModule(context, parameter);

        String viewName = getViewName(parameter);
        if (DEFAULT_VIEW_NAME.equals(viewName)) {
            // 以字符串作为模板
            return velocityConfig.getVelocityEngine().evaluate(
                    context,
                    writer,
                    parameter.getModuleName(),
                    getTemplateContent(parameter));
        } else {
            // 从文件中获取模板
            return velocityConfig.getVelocityEngine().mergeTemplate(
                    viewResolver.getUrl(viewName),
                    getEncoding(),
                    context,
                    writer
            );
        }
    }

    /**
     * 获取模板内容
     *
     * @param parameter
     * @return
     */
    protected String getTemplateContent(ViewModuleParameter parameter) {
        return "";
    }

    /**
     * 获取模板名称
     *
     * @param parameter
     * @return
     */
    protected String getViewName(ViewModuleParameter parameter) {
        return DEFAULT_VIEW_NAME;
    }

    /**
     * 获取模板编码
     *
     * @return
     */
    protected String getEncoding() {
        return (String) velocityConfig.getVelocityEngine().getProperty(RuntimeConstants.INPUT_ENCODING);
    }

    /**
     * 设置模型数据供模板使用
     *
     * @param context
     * @param parameter
     */
    protected void addModule(InternalContextAdapter context, ViewModuleParameter parameter) {
        // DO NOTING
    }
}
