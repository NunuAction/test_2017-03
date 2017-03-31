package com.duotin.returninfo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: guoqi
 * Date: 15/6/27
 * Time: 下午3:59
 */
public class DtoBase implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 111093L;

    /**
     * 构造函数，默认以成功来构造结果
     */
    public DtoBase(){
        this(ReturnCode.SUCCESS);
    }

    /**
     * 以指定的返回信息构造对象
     * @param returnInfo
     */
    public DtoBase(KeyValuePair<String, String> returnInfo) {
        this.returnCode = returnInfo.getKey();
        this.description = returnInfo.getValue();
    }

    /**
     * 以指定的返回信息构造对象
     * @param returnCode
     * @param description
     */
    public DtoBase(String returnCode,String description) {
        this.description = description;
        this.returnCode = returnCode;
    }
    /**
     * 返回描述
     */
    private String description;
    /**
     * 返回码
     */
    private String returnCode;

    /**
     * 获取返回描述
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置返回描述
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取返回码
     * @return
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * 设置返回码
     * @param returnCode
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    @Override
    public String toString() {
        return "DtoBase [description=" + description + ", returnCode="
                + returnCode + "]";
    }

}

