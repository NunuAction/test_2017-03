package com.duotin.velocity.module;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视图模块参数
 *
 * @author lrh
 * @create 2015年1月15日下午23:10:20
 */
public class ViewModuleParameter extends HashMap<String, Object> {

    private static final long serialVersionUID = -2541102242141071952L;
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private String moduleName;
    private String position;

    public ViewModuleParameter(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
