package com.duotin.common.bean;
import java.io.Serializable;


/**
 * Created with IntelliJ IDEA.
 * Author: wangyongxing(wangyongxing@duotin.com)
 * Date: 15-7-20 下午6:36
 *
 * @Description:
 */
public class EmailBean implements Serializable {

    private static final long serialVersionUID = -1429991595564390461L;

    public static final int STATUS_SEND = 1;
    public static final int STATUS_FAILURE = 2;
    public static final int STATUS_OPEN = 3;

    public static final int TEMPLATE_TYPE_EMAIL_PODCAST_REGISTER = 1;
    public static final int TEMPLATE_TYPE_EMAIL_PODCAST_INVITE = 2;
    public static final int TEMPLATE_TYPE_EMAIL_PODCAST_PASSWORD_FIND = 3;
    public static final int TEMPLATE_TYPE_EMAIL_AUTH = 4;

    public static final int SOURCE_PODCAST = 1;
    public static final int SOURCE_INTRA_NOTIFY = 2;
    public static final int SOURCE_DUOTIM_FM = 3;

    private int id;

    private String to;

    private String toPersonal;

    private String formPersonal;

    private String replyTo;

    private String replyToPersonal;

    private String title;

    private String body;

    private String[] cc;

    private int source;

    private int contentId;

    private boolean isHtml = true;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public void setHtml(boolean isHtml) {
        this.isHtml = isHtml;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getToPersonal() {
        return toPersonal;
    }

    public void setToPersonal(String toPersonal) {
        this.toPersonal = toPersonal;
    }

    public String getFormPersonal() {
        return formPersonal;
    }

    public void setFormPersonal(String formPersonal) {
        this.formPersonal = formPersonal;
    }

    public String getReplyToPersonal() {
        return replyToPersonal;
    }

    public void setReplyToPersonal(String replyToPersonal) {
        this.replyToPersonal = replyToPersonal;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

