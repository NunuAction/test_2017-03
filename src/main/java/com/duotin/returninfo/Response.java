package com.duotin.returninfo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: guoqi
 * Date: 15/6/27
 * Time: 下午3:53
 */
public class Response<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1234567890L;

    public Response() {
        this(ReturnCode.SUCCESS);
    }

    public Response(T result) {
        this(ReturnCode.SUCCESS, result);
    }

    public Response(KeyValuePair<String, String> returnInfo, String errorMsg, T result) {
        this(new DtoBase(returnInfo.getKey(), errorMsg), result);
    }

    public Response(KeyValuePair<String, String> returnInfo, T result) {
        this(new DtoBase(returnInfo.getKey(), returnInfo.getValue()), result);
    }

    public Response(KeyValuePair<String, String> returnInfo) {
        this(new DtoBase(returnInfo.getKey(), returnInfo.getValue()));
    }

    public Response(DtoBase baseResponse) {
        this(baseResponse, null);
    }

    public Response(DtoBase baseResponse, T result) {
        this.baseResponse = baseResponse;
        this.result = result;
    }

    private DtoBase baseResponse;

    private T result;

    public DtoBase getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(DtoBase baseResponse) {
        this.baseResponse = baseResponse;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return ReturnCode.SUCCESS.getKey().equals(
                baseResponse.getReturnCode());
    }

    @Override
    public String toString() {
        return "Response [baseResponse=" + baseResponse + ", result=" + result
                + "]";
    }

    public String getErrorMsg() {
        return baseResponse.getDescription();
    }

    /**
     * 获取错误码
     *
     * @return
     */
    public String getErrorCode() {
        return baseResponse.getReturnCode();
    }


    /**
     * 设置信息
     *
     * @param keyValuePair
     */
    public Response<T> baseResponse(KeyValuePair<String, String> keyValuePair) {
        this.baseResponse = new DtoBase(keyValuePair.getKey(), keyValuePair.getValue());
        return this;
    }

    public Response<T> result(T result) {
        this.result = result;
        return this;
    }
}

