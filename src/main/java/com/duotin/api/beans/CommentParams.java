package com.duotin.api.beans;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 15/7/25
 * Time: 下午1:37
 */
public class CommentParams {

    private String userKey="";

    private Integer userId = 0;

    private Integer contentId = 0;

    private String content="";

    private Integer commenttedId = 0;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCommenttedId() {
        return commenttedId;
    }

    public void setCommenttedId(Integer commenttedId) {
        this.commenttedId = commenttedId;
    }
}
