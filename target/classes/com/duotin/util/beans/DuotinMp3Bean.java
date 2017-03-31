package com.duotin.util.beans;

/**
 * 
 * @author jared
 * 
 * @Description:
 * 
 * @date Mar 25, 2015 11:16:56 AM
 * 
 */
public class DuotinMp3Bean {

	public static final int STATUS_SUCCESS = 1;
	public static final int STATUS_FAILURE = 2;
	public static final int STATUS_WAITING = 3;
    public static final int STATUS_PROCESSING = 4;

	private int status;

	private String fileUrl16;

	private int fileSize16;

	private String fileUrl32;

	private int fileSize32;

	private String fileUrl64;

	private int fileSize64;

	private String playtime;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFileUrl16() {
		return fileUrl16;
	}

	public void setFileUrl16(String fileUrl16) {
		this.fileUrl16 = fileUrl16;
	}

	public int getFileSize16() {
		return fileSize16;
	}

	public void setFileSize16(int fileSize16) {
		this.fileSize16 = fileSize16;
	}

	public String getFileUrl32() {
		return fileUrl32;
	}

	public void setFileUrl32(String fileUrl32) {
		this.fileUrl32 = fileUrl32;
	}

	public int getFileSize32() {
		return fileSize32;
	}

	public void setFileSize32(int fileSize32) {
		this.fileSize32 = fileSize32;
	}

	public String getFileUrl64() {
		return fileUrl64;
	}

	public void setFileUrl64(String fileUrl64) {
		this.fileUrl64 = fileUrl64;
	}

	public int getFileSize64() {
		return fileSize64;
	}

	public void setFileSize64(int fileSize64) {
		this.fileSize64 = fileSize64;
	}

	public String getPlaytime() {
		return playtime;
	}

	public void setPlaytime(String playtime) {
		this.playtime = playtime;
	}
}
