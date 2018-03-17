package com.shashank.fis.filesystem;

import com.shashank.fis.bean.FileList;
import com.shashank.fis.bean.FileMetadata;
import com.shashank.fis.exception.InternalServerException;
import com.shashank.fis.exception.ResourceNotFoundException;
import com.shashank.fis.exception.UnknownCommandException;

public interface FileSystemInterface {

	/**
	 * Returns the list of files and directories in the pwd
	 */
	public FileList dir(String pwd);

	
	/**
	 * Returns the file metadata of the file 
	 */
	public FileMetadata info(String pwd, String fileName)
			throws ResourceNotFoundException, InternalServerException,
			UnknownCommandException;

	
	/**
	 * Checks if dir exists. Returns the new dir if cd successful
	 */
	public String cd(String pwd, String dirName)
			throws ResourceNotFoundException, UnknownCommandException;

}
