package com.duotin.velocity;

import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

/**
 *
 * @author lrh
 * @create 2015年1月15日下午23:10:20
 */
public class VelocityLayoutViewResolverExt extends VelocityLayoutViewResolver {

    /**
     * 获取完整的模板路径及文件名
     *
     * @param viewName
     * @return
     */
    public String getUrl(String viewName) {
        return getPrefix() + viewName + getSuffix();
    }
}
