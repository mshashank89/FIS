package com.shashank.fis.exception;

public class UnknownCommandException extends FISException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1541516701661299145L;

	@Override
	public String getMessage() {
		
		return "Invalid command. Missing parameters";
	}

}
