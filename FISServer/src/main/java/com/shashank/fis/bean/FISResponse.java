package com.shashank.fis.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
//@XmlSeeAlso({FileList.class, FileMetadata.class, FISErrorResponse.class})
public class FISResponse {

	private FileList dir;
	private FileMetadata info;
	private String pwd;
	private FISCDResponse cd;
	private String ping;
	public String getPing() {
		return ping;
	}

	public void setPing(String ping) {
		this.ping = ping;
	}

	private FISErrorResponse error;
	
	
	public FISResponse(FileList fileList, FileMetadata fileMetadata, String pwd,
			FISErrorResponse error) {
		super();
		this.dir = fileList;
		this.info = fileMetadata;
		this.pwd = pwd;
		this.error = error;
	}
	
	public FISResponse(FileList fileList) {
		super();
		this.dir = fileList;
		this.info = null;
		this.pwd = null;
		this.error = null;
	}
	public FISResponse(FileMetadata fileMetadata) {
		super();
		this.dir = null;
		this.info = fileMetadata;
		this.pwd = null;
		this.error = null;
	}
	
	public FISResponse(FISErrorResponse error) {
		super();
		this.dir = null;
		this.info = null;
		this.pwd = null;
		this.error = error;
	}
	public FISResponse() {
		
	}

	public FileList getDir() {
		return dir;
	}

	public void setDir(FileList dir) {
		this.dir = dir;
	}

	public FileMetadata getInfo() {
		return info;
	}

	public void setInfo(FileMetadata info) {
		this.info = info;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public FISCDResponse getCd() {
		return cd;
	}

	public void setCd(FISCDResponse cd) {
		this.cd = cd;
	}

	public FISErrorResponse getError() {
		return error;
	}

	public void setError(FISErrorResponse error) {
		this.error = error;
	}
	
	
}
