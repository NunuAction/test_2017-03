package com.duotin.returninfo;

/**
 * Created with IntelliJ IDEA.
 * User: guoqi
 * Date: 15/6/27
 * Time: 下午3:55
 */
public class ReturnCode {
    /**
     * 返回成功
     */
    public final static String SUCCESS_CODE = "0000";
    public final static KeyValuePair<String, String> SUCCESS = new KeyValuePair<String, String>("0000", "接口执行成功");

    /**
     * 接口内部异常
     */
    public final static String EXCEPTION_CODE = "0001";
    public final static KeyValuePair<String, String> EXCEPTION = new KeyValuePair<String, String>("0001", "接口内部异常");

    /**
     * 获取参数失败，可能类型不匹配
     */
    public final static String ARGUMENT_CODE = "0002";
    public final static KeyValuePair<String, String> ARGUMENT = new KeyValuePair<String, String>("0002", "获取参数失败，可能类型不匹配");

    /**
     * 请求的接口不存在
     */
    public final static String NO_API_FOUND_CODE = "0003";
    public final static KeyValuePair<String, String> NO_API_FOUND = new KeyValuePair<String, String>("0003", "请求的接口不存在");

    /**
     * 参数不正确
     */
    public final static String ARGUMENT_ERROR_CODE = "0004";
    public final static KeyValuePair<String, String> ARGUMENT_ERROR = new KeyValuePair<String, String>("0004", "参数不正确");

    /**
     * 业务异常
     */
    public final static String BUSINESS_ERROR_CODE = "0005";
    public final static KeyValuePair<String, String> BUSINESS_ERROR = new KeyValuePair<String, String>(BUSINESS_ERROR_CODE, "业务异常");


    /*************************************标签相关 *************************************************************/


    /**
     * 标签已存在
     */
    public final static KeyValuePair<String, String> TAG_ALREADY_EXIST = new KeyValuePair<String, String>("2001", "标签已经存在");


    /**
     * 已经点赞
     */
    public final static KeyValuePair<String, String> PRAISE_ALREADY_EXIST = new KeyValuePair<String, String>("3001", "已经赞过!");

    /**
     * 未点赞
     */
    public final static KeyValuePair<String, String> PRAISE_NOT_EXIST = new KeyValuePair<String, String>("3002", "未赞过!");

    /**
     *
     * 已经投票
     */
    public final static KeyValuePair<String, String> VOTE_ALREADY_EXIST = new KeyValuePair<String, String>("4001", "已经投票!");

    /**
     * 投票子项已经存在
     */
    public final static KeyValuePair<String, String> VOTE_DETAIL_ALREADY_EXIST = new KeyValuePair<String, String>("4002", "投票子项已经存在!");

    public final static KeyValuePair<String, String> VOTE_NOT_EXIST = new KeyValuePair<String, String>("4003", "投票项不存在!");

    /**
     * 投票已过期
     */
    public final static KeyValuePair<String, String> VOTE_EXPIRE = new KeyValuePair<String, String>("4004", "投票已经过期!");



    /******************************************商城*************************************************/
    public final static KeyValuePair<String, String> MALL_OPERATION_NO_EXPIRE_NOT_EXIST = new KeyValuePair<String, String>("5001", "加分事件不存在!");


    public final static KeyValuePair<String, String> MALL_ADD_INTEGRAL_LIMIT = new KeyValuePair<String, String>("5002", "已经达加分上限!");


    public final static KeyValuePair<String, String> MALL_USER_INTEGRAL_NOT_ENOUGH = new KeyValuePair<String, String>("5003", "用户积分不够!");





    /**
     * 已经签到
     */
    public final static KeyValuePair<String, String> ALREADY_SIGN_IN = new KeyValuePair<String, String>("6001", "已经签到 !");



    public static KeyValuePair<String, String> newKeyValuePair(String errorCode, String errorMsg) {
        return new KeyValuePair<String, String>(errorCode, errorMsg);
    }


}

