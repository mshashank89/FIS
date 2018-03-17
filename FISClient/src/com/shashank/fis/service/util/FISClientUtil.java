package com.shashank.fis.service.util;

import java.util.List;

import com.shashank.fis.bean.FISErrorResponse;
import com.shashank.fis.bean.FISResponse;
import com.shashank.fis.bean.FileList;
import com.shashank.fis.bean.FileMetadata;

public class FISClientUtil {

	public static String getStringResponse(FISResponse fisResponse) {

		String response = "";
		if (null != fisResponse.getDir()) {
			response = getDirResponse(fisResponse.getDir());
		} else if (null != fisResponse.getInfo()) {
			response = getInfoResponse(fisResponse.getInfo());
		} else if (null != fisResponse.getPwd() && !"".equals(fisResponse.getPwd())) {
			response = fisResponse.getPwd();
		} else if (null != fisResponse.getCd()) {
			response = fisResponse.getCd().getMessage();
		} else if (null != fisResponse.getError()) {
			response = getErrorResponse(fisResponse.getError());
		} else if (null != fisResponse.getPing() && !"".equals(fisResponse.getPing())) {
			response = fisResponse.getPing();
		} 
		return response;
	}

	private static String getErrorResponse(FISErrorResponse error) {

		return error.getStatus() + " " + error.getErrorMessage();
	}


	private static String getInfoResponse(FileMetadata info) {

		StringBuilder sb = new StringBuilder();
	
		sb.append("File name: ").append(info.getFileName()).append("\n")
		  .append("Type: ").append(info.getType()).append("\n")
		  .append("Dir Structure: ").append(info.getDirStructure()).append("\n")
		  .append("Size: ").append(info.getSizeInBytes()).append(" bytes").append("\n")
		  .append("Created: ").append(info.getCreationDate()).append("\n")
		  .append("200 OK");
		
		return sb.toString();
	}

	private static String getDirResponse(FileList dir) {
		StringBuilder sb = new StringBuilder();
		List<String> fileList = dir.getFileList();
		for (String fileName : fileList) {
			sb.append(fileName).append("\n");
		}
		sb.append("200 OK");
		
		return sb.toString();
	}

}
