package com.shashank.fis.service;


public interface FileOperationsService {
	
	public String pingServer();
	
	public String handleDirReq(String args);

	public String handleInfoReq(String args);
	
	public String handlePwdReq(String args);
	
	public String handleCdReq(String args);
	
	public String handleExit(String args);
}
