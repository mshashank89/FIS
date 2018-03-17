package com.shashank.fis.bean;


//@XmlType(name="3")
public class FISErrorResponse {

	private int status;
	private String errorMessage;
	
	public FISErrorResponse() {
		
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
