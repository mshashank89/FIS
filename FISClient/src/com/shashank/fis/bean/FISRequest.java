package com.shashank.fis.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FISRequest {
	
	private String userPwd;

	public FISRequest() {
	}

	
	public FISRequest(String userPwd) {
		super();
		this.userPwd = userPwd;
	}
	
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	
}
