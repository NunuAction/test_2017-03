package com.duotin.api.beans;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: yujianfu (yujianfu@duotin.com)
 * Date: 15/7/23
 * Time: 下午4:31
 */
public class PlayParams implements Serializable {

    private Integer contentId = 0;

    private String listened = "";

    private String progress = "00:00:00";

    private Integer hackTimes = 1;

    private String userKey = "";

    private Integer mobileId = 0;

    private Integer times = 1;

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getListened() {
        return listened;
    }

    public void setListened(String listened) {
        this.listened = listened;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Integer getHackTimes() {
        return hackTimes;
    }

    public void setHackTimes(Integer hackTimes) {
        this.hackTimes = hackTimes;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Integer getMobileId() {
        return mobileId;
    }

    public void setMobileId(Integer mobileId) {
        this.mobileId = mobileId;
    }
}
