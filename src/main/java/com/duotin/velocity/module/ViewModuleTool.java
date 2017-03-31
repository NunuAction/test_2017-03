package com.duotin.velocity.module;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.Renderable;
import org.springframework.context.ApplicationContext;

/**
 * 视图模块渲染
 *
 * @author lrh
 * @create 2015年1月15日下午23:10:20
 */
public class ViewModuleTool implements Renderable {

    private final LinkedList<ViewModuleParameter> parametersStack = new LinkedList<ViewModuleParameter>();
    private final ApplicationContext applicationContext;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public ViewModuleTool(HttpServletRequest request, HttpServletResponse response, ApplicationContext applicationContext) {
        this.request = request;
        this.response = response;
        this.applicationContext = applicationContext;
    }

    public ViewModuleTool module(String moduleName, String position) {
        ViewModuleParameter parameters = getParameters();

        parameters.setModuleName(moduleName);
        parameters.setPosition(position);

        return this;
    }

    public ViewModuleTool module(String moduleName) {
        return module(moduleName, "default");
    }

    /**
     * 设置参数
     *
     * @param name
     * @param value
     * @return
     */
    public ViewModuleTool parameter(String name, Object value) {
        ViewModuleParameter parameters = getParameters();
        parameters.put(name, value);

        return this;
    }

    /**
     * 方法 {@link #parameter(String, Object) } 的别名。
     *
     * @param name
     * @param value
     * @return
     */
    public ViewModuleTool param(String name, Object value) {
        return parameter(name, value);
    }

    @Override
    public boolean render(InternalContextAdapter context, Writer writer) throws IOException, MethodInvocationException, ParseErrorException, ResourceNotFoundException {
        ViewModuleParameter parameter = getParameters();

        ViewModule module = applicationContext.getBean(parameter.getModuleName(), ViewModule.class);
        module.render(context, writer, parameter);
        parametersStack.removeFirst();

        return true;
    }

    private ViewModuleParameter getParameters() {
        if (parametersStack.isEmpty()) {
            parametersStack.addFirst(new ViewModuleParameter(request, response));
        }
        return parametersStack.getFirst();
    }
}