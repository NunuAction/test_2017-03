package com.duotin.common.bean;

/**
 * 
 * @author jared
 *
 * @Description:
 *
 * @date Mar 24, 2015 2:38:33 PM
 *
 */
public class UploadBean {

	private String mp3UploadUrl;
	
	private String fileUploadTempPath;
	
	private String mp3GetUrl;

	private String carRadioTokenPath;


    private String mp3UploadTempPath;

	public String getFileUploadTempPath() {
		return fileUploadTempPath;
	}

	public void setFileUploadTempPath(String fileUploadTempPath) {
		this.fileUploadTempPath = fileUploadTempPath;
	}

	public String getMp3UploadUrl() {
		return mp3UploadUrl;
	}

	public void setMp3UploadUrl(String mp3UploadUrl) {
		this.mp3UploadUrl = mp3UploadUrl;
	}

	public String getMp3GetUrl() {
		return mp3GetUrl;
	}

	public void setMp3GetUrl(String mp3GetUrl) {
		this.mp3GetUrl = mp3GetUrl;
	}

	public String getCarRadioTokenPath() {
		return carRadioTokenPath;
	}

	public void setCarRadioTokenPath(String carRadioTokenPath) {
		this.carRadioTokenPath = carRadioTokenPath;
	}

    public String getMp3UploadTempPath() {
        return mp3UploadTempPath;
    }

    public void setMp3UploadTempPath(String mp3UploadTempPath) {
        this.mp3UploadTempPath = mp3UploadTempPath;
    }
}
