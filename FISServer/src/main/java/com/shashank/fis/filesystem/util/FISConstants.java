package com.shashank.fis.filesystem.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class FISConstants {

	public static final String REMOTE_FS_BASE_PATH;
	
	static {

		Properties properties = new Properties();

		try {
			properties.load(FISConstants.class
					.getResourceAsStream("../../properties/FIS.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		REMOTE_FS_BASE_PATH = (String) properties.get("BASE_DIR");
	}

	
	public static final String USER_PWD = "USER_PWD";
	
	public static final String BASE_DIR = "/";

	public static final String ERROR_MSG_500 = "Invalid command. Missing parameters";
	
	public static final String ERROR_MSG_503 = "Unknown command";
	
	public static final String PING_MESSAGE = "200 Welcome to File Information Service";
}
