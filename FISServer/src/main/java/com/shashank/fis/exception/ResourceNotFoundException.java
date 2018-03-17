package com.shashank.fis.exception;

public class ResourceNotFoundException extends FISException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8947549834879673204L;
	
	private String type;
	private String name;
	
	public ResourceNotFoundException(String type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return type + " " + name + " not found.";
	}
	
	
}
