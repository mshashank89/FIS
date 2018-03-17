package com.shashank.fis.exception;

public class InternalServerException extends FISException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4825737110968800892L;

	@Override
	public String getMessage() {
		return "Internal Server Exeception occured.";
	}

}
