package com.shashank.fis.bean;

import java.util.Date;

//@XmlType(name="2")
public class FileMetadata {

	private String fileName;
	private String type;
	private String dirStructure;
	private long sizeInBytes;
	private Date creationDate;
	private Date lastModifiedDate;
	
	public FileMetadata() {
		
	}

	public FileMetadata(String fileName, String type, String dirStructure,
			long sizeInBytes, Date creationDate, Date lastModifiedDate) {
		super();
		this.fileName = fileName;
		this.type = type;
		this.dirStructure = dirStructure;
		this.sizeInBytes = sizeInBytes;
		this.creationDate = creationDate;
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDirStructure() {
		return dirStructure;
	}

	public void setDirStructure(String dirStructure) {
		this.dirStructure = dirStructure;
	}

	public long getSizeInBytes() {
		return sizeInBytes;
	}

	public void setSizeInBytes(long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Path : " + getDirStructure()).append("\n");
		sb.append("Name : " + getFileName()).append("\n");
		sb.append("Type : " + getType()).append("\n");
		sb.append("Creation Date : " + getCreationDate()).append("\n");
		sb.append("LastModified Date : " + getLastModifiedDate()).append("\n");
		sb.append("Size : " + getSizeInBytes());
		
		return sb.toString();
	}

	
}
