package com.shashank.fis.client;

import com.shashank.fis.bean.FISResponse;

public interface FISClient {
	
	/**
	 * Gets the welcome message if the server is up
	 * @return
	 */
	public FISResponse ping();
	
	/**
	 * lists the files in the present working directory
	 * @return
	 */
	public FISResponse dir();

	/**
	 * returns file metadata
	 * @param fileName
	 * @return
	 */
	public FISResponse info(String fileName);
	
	/**
	 * returns the present working directory
	 * @return
	 */
	public FISResponse pwd();
	
	/**
	 * moves the dir
	 * @param newDir
	 * @return
	 */
	public FISResponse cd(String newDir);
	
	/**
	 * closes the connection
	 */
	public void exit();
}
