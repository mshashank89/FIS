package com.shashank.fis.bean;

import java.util.List;

//@XmlType(name="1")
public class FileList {

	private List<String> fileList;
	
	public FileList() {
		
	}
	
	public FileList(List<String> fileList) {
		this.fileList = fileList;
	}

	public List<String> getFileList() {
		return fileList;
	}

	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}
}
