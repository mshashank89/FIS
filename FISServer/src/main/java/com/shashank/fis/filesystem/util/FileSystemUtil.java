package com.shashank.fis.filesystem.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import com.shashank.fis.bean.FISErrorResponse;
import com.shashank.fis.bean.FileMetadata;
import com.shashank.fis.exception.FISException;
import com.shashank.fis.exception.InternalServerException;
import com.shashank.fis.exception.ResourceNotFoundException;

public final class FileSystemUtil {

	public static final String PATH_SEPARATOR = "/";

	
	/**
	 * Method to get the directory by name
	 * 
	 * @param pwd
	 * @param dirName
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public static File getDirectoryByName(String pwd, String dirName)
			throws ResourceNotFoundException {
		
		if ("..".equals(dirName)) {
			File presentWorkingDir = getPresentWorkingDir(pwd);
			if (FISConstants.BASE_DIR.equals(pwd)) {
				return presentWorkingDir;
			}
			
			return getParentDir(presentWorkingDir);
		}
		if (".".equals(dirName)) {
			return getPresentWorkingDir(pwd);
		}
		
		
		File dir = new File(FISConstants.REMOTE_FS_BASE_PATH + pwd + PATH_SEPARATOR
				+ dirName);

		if (!dir.exists()) {
			throw new ResourceNotFoundException("Directory", dirName);
		}
		if (!dir.isDirectory()) {
			throw new ResourceNotFoundException("Directory", dirName);
		}

		return dir;
	}

	private static File getParentDir(File presentWorkingDir) {
		return new File(presentWorkingDir.getParent());
	}

	private static File getPresentWorkingDir(String pwd) {
		return  new File(FISConstants.REMOTE_FS_BASE_PATH + pwd + PATH_SEPARATOR);
	}
	/**
	 * method to get the current working dir of the client
	 * 
	 * @param pwd
	 * @return
	 */
	public static File getCurrentWorkingDir(String pwd) {
		return new File(FISConstants.REMOTE_FS_BASE_PATH + pwd);
	}

	/**
	 * Method to get the file by name
	 * 
	 * @param pwd
	 * @param fileName
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public static File getFile(String pwd, String fileName)
			throws ResourceNotFoundException {
		File file = new File(FISConstants.REMOTE_FS_BASE_PATH + pwd + PATH_SEPARATOR
				+ fileName);

		if (!file.exists()) {
			throw new ResourceNotFoundException("File", fileName);
		}

		return file;
	}

	/**
	 * Method to populate fileMetaData obj with file details
	 * 
	 * @param file
	 * @return
	 * @throws InternalServerException
	 */
	public static FileMetadata getFileMetadataFromFile(File file)
			throws InternalServerException {
		Path filePath = file.toPath();
		BasicFileAttributes attributes = null;
		FileMetadata fileMetadata = new FileMetadata();
		try {
			attributes = Files.readAttributes(filePath,
					BasicFileAttributes.class);
		} catch (IOException e) {
			e.printStackTrace();
			throw new InternalServerException();
		}

		long creationDateInMS = attributes.creationTime().to(
				TimeUnit.MILLISECONDS);
		long lastModifiedDateInMS = attributes.lastModifiedTime().to(
				TimeUnit.MILLISECONDS);

		fileMetadata.setDirStructure(file.getPath().substring(FISConstants.REMOTE_FS_BASE_PATH.length()).replaceAll("[\\\\]", "/"));
		fileMetadata.setCreationDate(new Date(creationDateInMS));
		fileMetadata.setFileName(file.getName());
		fileMetadata.setLastModifiedDate(new Date(lastModifiedDateInMS));
		fileMetadata.setSizeInBytes(file.length());
		fileMetadata.setType(file.isFile() ? "File" : "Directory");

		return fileMetadata;
	}

	
	/**
	 * MEthod to get FISErrorResponse
	 * 
	 * @param httpResponse
	 * @param e
	 * @return
	 */
	public static FISErrorResponse getErrorResponse(
			HttpServletResponse httpResponse, FISException e, int status) {

		FISErrorResponse errorResponse = new FISErrorResponse();
		httpResponse.setStatus(status);
		errorResponse.setStatus(status);
		errorResponse.setErrorMessage(e.getMessage());

		return errorResponse;
	}

}
