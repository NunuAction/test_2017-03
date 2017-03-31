package com.duotin.velocity.module;

import java.io.IOException;
import java.io.Writer;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * 视图模块
 *
 * @author lrh
 * @create 2015年1月15日下午23:10:20
 */
public interface ViewModule {

    /**
     * 模块渲染
     *
     * @param context
     * @param writer
     * @param parameter
     * @return
     * @throws java.io.IOException
     */
    boolean render(InternalContextAdapter context, Writer writer, ViewModuleParameter parameter) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException;
}
