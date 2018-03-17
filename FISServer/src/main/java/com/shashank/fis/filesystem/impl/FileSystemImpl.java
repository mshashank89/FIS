package com.shashank.fis.filesystem.impl;

import java.io.File;
import java.util.Arrays;

import com.shashank.fis.bean.FileList;
import com.shashank.fis.bean.FileMetadata;
import com.shashank.fis.exception.InternalServerException;
import com.shashank.fis.exception.ResourceNotFoundException;
import com.shashank.fis.exception.UnknownCommandException;
import com.shashank.fis.filesystem.FileSystemInterface;
import com.shashank.fis.filesystem.util.FISConstants;
import com.shashank.fis.filesystem.util.FileSystemUtil;

public class FileSystemImpl implements FileSystemInterface {


	/**
	 * Returns the list of files and directories in the pwd
	 */
	public FileList dir(String pwd) {
		// method to return the list of files in the given path
		File currentDir = FileSystemUtil.getCurrentWorkingDir(pwd);
		String[] fileList = currentDir.list();

		return new FileList(Arrays.asList(fileList));
	}

	/**
	 * Returns the file metadata of the file 
	 */
	public FileMetadata info(String pwd, String fileName)
			throws ResourceNotFoundException, InternalServerException, UnknownCommandException {
		if (null == fileName || "".equals(fileName)) {
			throw new UnknownCommandException();
		}
		FileMetadata fileMetaData = new FileMetadata();

		File file = FileSystemUtil.getFile(pwd, fileName);

		fileMetaData = FileSystemUtil.getFileMetadataFromFile(file);

		return fileMetaData;
	}

	
	/**
	 * Checks if dir exists. Returns the new dir if cd successful
	 */
	public String cd(String pwd, String dirName) throws ResourceNotFoundException, UnknownCommandException {
		if (null == dirName || "".equals(dirName)) {
			throw new UnknownCommandException();
		}
		pwd = pwd.replaceAll("[\\\\]", "/");
		if (pwd.endsWith("/")) {
			pwd = pwd.substring(0, pwd.lastIndexOf("/"));
		}
		File dir = FileSystemUtil.getDirectoryByName(pwd, dirName);
		
		if (".".equals(dirName)) {
			return pwd;
		}
		if ("..".equals(dirName)) {
			
			if (pwd.equals(FISConstants.BASE_DIR)) {
				return pwd;
			}
			if (dir.getPath().replaceAll("[\\\\]", "/").equals(FISConstants.REMOTE_FS_BASE_PATH)) {
				return FISConstants.BASE_DIR;
			}
			return pwd.substring(0, pwd.lastIndexOf("/"));
		}
		StringBuilder sb = new StringBuilder();
		sb.append(pwd);
		if (!sb.toString().endsWith("/")) {
			sb.append(FileSystemUtil.PATH_SEPARATOR);
		}
		
		sb.append(dirName).append(FileSystemUtil.PATH_SEPARATOR);
		
		return sb.toString();
	}
	

}
