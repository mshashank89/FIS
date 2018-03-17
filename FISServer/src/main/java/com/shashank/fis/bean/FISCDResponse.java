package com.shashank.fis.bean;

public class FISCDResponse {
	
	private String newDir;
	private String message;
	public FISCDResponse(String newDir, String message) {
		super();
		this.newDir = newDir;
		this.message = message;
	}
	
	public FISCDResponse() {}

	public String getNewDir() {
		return newDir;
	}

	public void setNewDir(String newDir) {
		this.newDir = newDir;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
