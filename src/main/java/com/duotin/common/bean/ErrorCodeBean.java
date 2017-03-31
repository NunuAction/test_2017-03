package com.duotin.common.bean;

/**
 * 
 * @author jared
 * 
 * @Description: 错误提示信息
 * 
 * @date Apr 1, 2015 2:48:08 PM
 * 
 */
public final class ErrorCodeBean {

	public static final class CommonErrorCode {
		public static final int SYSTEM = -1;
		public static final int SUCCESS = 0;
		public static final int TOKEN_ILLEGAL = 1;
		public static final int UNLOGIN = 2;
		public static final int FIELD_NULL = 3;
		public static final int DATASOURCE_EXCEPTIONAL = 4;
		public static final int DATA_INVALID = 5;
		public static final int FILE_UPLOAD_FAILURE = 7;

        //网络异常
		public static final int NETWORK_EXCEPTION = 8;
		
		public static final int LINK_DISABLED = 9;
	}

	public static final class UserErrorCode {
		public static final int EMAIL_IS_NULL = 20001;
		public static final int PASSWORD_IS_NULL = 20002;
		public static final int RE_PASSWORD_NOT_MATCH = 20003;
		public static final int EMAIL_EXISTS = 20004;
		public static final int PASSWORD_NOT_MATCH = 20005;
		public static final int USER_NOT_EXISTS = 20006;
		public static final int WEIBO_LOGIN_FAILURE = 20007;
		public static final int QQ_LOGIN_FAILURE = 20008;
		public static final int USER_ID_IS_NULL = 20009;
		public static final int USERNAME_INVALID = 20010;
		public static final int PASSWORD_ILLEGAL = 20011;
        public static final int MOBILE_EXIST=20012;
	}

	public static final class NotifyErrorCode {
		public static final int EMAIL_TO_IS_NULL = 30001;
		public static final int EMAIL_TITLE_IS_NULL = 30002;
		public static final int EMAIL_CONTENT_IS_NULL = 30003;
		public static final int EMAIL_SEND_FAILURE = 30004;
		public static final int EMAIL_TEMPLATE_IS_NULL = 30005;
	}

    public static final class PodcastErrorCode{
        //阅读消息失败
        public static final int READ_MESSAGE_FAILED = 40001;

        //删除消息失败
        public static final int DEL_MESSAGE_FALIED = 40002;

        //回复消息失败
        public static final int MESSAGE_REPLY_FAILED = 40003;
    }
}
