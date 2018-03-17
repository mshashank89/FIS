package com.shashank.fis.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.shashank.fis.bean.FISErrorResponse;
import com.shashank.fis.bean.FISResponse;
import com.shashank.fis.filesystem.util.FISConstants;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable arg0) {
		
		FISResponse response = new FISResponse();
		FISErrorResponse error = new FISErrorResponse();
		error.setStatus(503);
		error.setErrorMessage(FISConstants.ERROR_MSG_503);
		response.setError(error);
		
		return Response.status(Status.SERVICE_UNAVAILABLE)
				.entity(response)
				.type(MediaType.APPLICATION_JSON)
				.build();
	}

}
