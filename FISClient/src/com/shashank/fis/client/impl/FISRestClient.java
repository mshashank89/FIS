package com.shashank.fis.client.impl;

import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import com.shashank.fis.bean.FISRequest;
import com.shashank.fis.bean.FISResponse;
import com.shashank.fis.client.FISClient;
import com.shashank.fis.service.util.FISClientConstants;

public class FISRestClient implements FISClient{
	
	private Client client = ClientBuilder.newClient();
	private WebTarget baseTarget;
	private WebTarget pingTarget;
	private WebTarget dirTarget;
	private WebTarget infoTarget;
//	private WebTarget pwdTarget;
	private WebTarget cdTarget;
	
	private String pwd;
	
	Properties properties;
	
	public FISRestClient() {
		
		properties = new Properties();
		loadProperties();
		
		client = ClientBuilder.newClient();
		baseTarget = client.target(properties.getProperty(FISClientConstants.PROPERTIES_BASE_URL));
		pingTarget = baseTarget.path(properties.getProperty(FISClientConstants.PROPERTIES_PING_URI));
		dirTarget = baseTarget.path(properties.getProperty(FISClientConstants.PROPERTIES_DIR_URI));
		infoTarget = baseTarget.path(properties.getProperty(FISClientConstants.PROPERTIES_INFO_URI)).path("{fileName}");
//		pwdTarget = baseTarget.path(properties.getProperty(FISClientConstants.PROPERTIES_PWD_URI));
		cdTarget = baseTarget.path(properties.getProperty(FISClientConstants.PROPERTIES_CD_URI)).path("{dirName}");
	}

	private void loadProperties() {

		try {
			properties.load(this.getClass()
					.getResourceAsStream("../../properties/FISClient.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * lists the files in the present working directory
	 * @return
	 */
	public FISResponse ping() {
		FISResponse response = new FISResponse();
		response = pingTarget.request().get(FISResponse.class);
		pwd = FISClientConstants.BASE_DIR;
		return response;
	}
	
	/**
	 * lists the files in the present working directory
	 * @return
	 */
	public FISResponse dir() {
		FISResponse response = new FISResponse();
		FISRequest dirRequest = new FISRequest(pwd);
		response = dirTarget.request().post(Entity.json(dirRequest), FISResponse.class);
		return response;
	}

	/**
	 * returns file metadata
	 * @param fileName
	 * @return
	 */
	public FISResponse info(String fileName) {
		FISResponse response = new FISResponse();
		FISRequest infoRequest = new FISRequest(pwd);
		response = infoTarget
				   .resolveTemplate("fileName", fileName)
				   .request()
				   .post(Entity.json(infoRequest), FISResponse.class);
		return response;
	}
	
	/**
	 * returns the present working directory
	 * @return
	 */
	public FISResponse pwd() {
		FISResponse response = new FISResponse();
//		response = pwdTarget.request().get(FISResponse.class);
		response.setPwd(pwd);
		return response;
	}
	
	/**
	 * moves the dir
	 * @param newDir
	 * @return
	 */
	public FISResponse cd(String newDir) {
		FISResponse response = new FISResponse();
		FISRequest cdRequest = new FISRequest(pwd);
		response = cdTarget
				   .resolveTemplate("dirName", newDir)
				   .request()
				   .post(Entity.json(cdRequest), FISResponse.class);
		pwd = (null != response.getCd()) ? response.getCd().getNewDir() : pwd;
		return response;
	}
	
	/**
	 * closes the connection
	 */
	public void exit() {
		client.close();
	}

}
