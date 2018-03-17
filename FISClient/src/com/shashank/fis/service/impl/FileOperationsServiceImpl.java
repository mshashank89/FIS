package com.shashank.fis.service.impl;

import com.shashank.fis.bean.FISResponse;
import com.shashank.fis.client.FISClient;
import com.shashank.fis.client.impl.FISRestClient;
import com.shashank.fis.service.FileOperationsService;
import com.shashank.fis.service.util.FISClientConstants;
import com.shashank.fis.service.util.FISClientUtil;

public class FileOperationsServiceImpl implements FileOperationsService{
	
	private FISClient fisClient;
	
	
	public FileOperationsServiceImpl() {
		fisClient = new FISRestClient();
	}
	
	public String handleDirReq(String args) {
		
		FISResponse fisResponse = fisClient.dir();
		
		if (null != args && !"".equals(args)) {
			return FISClientConstants.INVALID_COMMAND_MESSAGE;
		}

		String response = FISClientUtil.getStringResponse(fisResponse);

		return response;
	}

	public String handleInfoReq(String args) {
		
		FISResponse fisResponse = fisClient.info(args);

		String response = FISClientUtil.getStringResponse(fisResponse);

		return response;
	}
	
	public String handlePwdReq(String args) {
		
		FISResponse fisResponse = fisClient.pwd();
		
		if (null != args && !"".equals(args)) {
			return FISClientConstants.INVALID_COMMAND_MESSAGE;
		}

		String response = FISClientUtil.getStringResponse(fisResponse);

		return response;
	}
	
	public String handleCdReq(String args) {
		
		FISResponse fisResponse = fisClient.cd(args);

		String response = FISClientUtil.getStringResponse(fisResponse);

		return response;
	}
	
	public String handleExit(String args) {
				
		if (null != args && !"".equals(args)) {
			return FISClientConstants.INVALID_COMMAND_MESSAGE;
		}

		fisClient.exit();
		return FISClientConstants.GOODBYE_MESSAGE;
	}

	@Override
	public String pingServer() {
		FISResponse fisResponse = fisClient.ping();

		String response = FISClientUtil.getStringResponse(fisResponse);

		return response;

	}
}
