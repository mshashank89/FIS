package com.shashank.fis.resource;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.shashank.fis.bean.FISCDResponse;
import com.shashank.fis.bean.FISErrorResponse;
import com.shashank.fis.bean.FISRequest;
import com.shashank.fis.bean.FISResponse;
import com.shashank.fis.bean.FileList;
import com.shashank.fis.bean.FileMetadata;
import com.shashank.fis.exception.InternalServerException;
import com.shashank.fis.exception.ResourceNotFoundException;
import com.shashank.fis.exception.UnknownCommandException;
import com.shashank.fis.filesystem.FileSystemInterface;
import com.shashank.fis.filesystem.impl.FileSystemImpl;
import com.shashank.fis.filesystem.util.FISConstants;
import com.shashank.fis.filesystem.util.FileSystemUtil;

/**
 * Root resource (exposed at "fileops" path)
 */
@Path("/fileops")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FileSystemResource {

	private FileSystemInterface fileSystem;
	
	public FileSystemResource() {
		 fileSystem = new FileSystemImpl();
	}
    
	@GET
    @Path("/ping")
    public FISResponse ping() {
    	
    	FISResponse response = new FISResponse();
    	response.setPing(FISConstants.PING_MESSAGE);
    	return response;
    }
	
    @POST
    @Path("/dir")
    public FISResponse dir(FISRequest request) {
    	String pwd = request.getUserPwd();
    	
    	FileList fileList = fileSystem.dir(pwd);
    	FISResponse response = new FISResponse(fileList);
    	
    	return response;
    }
    
       
    @POST
    @Path("/info/{fileName}")
    public FISResponse info(@PathParam("fileName") String fileName,
    						FISRequest request,
							@Context HttpServletResponse httpResponse) {
    	
    	String pwd = request.getUserPwd();
    	FISResponse response = null;
    	FISErrorResponse error = null;
		try {
    		
    		FileMetadata fileMetadata = fileSystem.info(pwd, fileName);
    		
    		response = new FISResponse(fileMetadata);
					
		} catch (ResourceNotFoundException e) {
			
			int status = HttpServletResponse.SC_NOT_IMPLEMENTED; //status 501
			error  = FileSystemUtil.getErrorResponse(httpResponse, e, status);
			response = new FISResponse(error);
			
		} catch (InternalServerException e) {
			
			int status = HttpServletResponse.SC_NOT_IMPLEMENTED; //status 501
			error = FileSystemUtil.getErrorResponse(httpResponse, e, status);
			response = new FISResponse(error);
			
		} catch (UnknownCommandException e) {
			int status = HttpServletResponse.SC_SERVICE_UNAVAILABLE; //status 503
			error = FileSystemUtil.getErrorResponse(httpResponse, e, status);
			response = new FISResponse(error);
		}
    	
    	return response;
    }

    
   /* @POST
    @Path("/pwd")
    public FISResponse pwd(FISRequest request) {
    	
    	FISResponse response = new FISResponse();
    	response.setPwd(request.getUserPwd());
    	return response;
    	
    }*/
    
    @POST
    @Path("/cd/{dirName}")
    public FISResponse cd(@PathParam("dirName") String dirName,
    					  FISRequest request,
						  @Context HttpServletResponse httpResponse) {
    	
    	FISResponse response = new FISResponse();
    	String pwd = request.getUserPwd();
    	
    	try {
			String dir = fileSystem.cd(pwd, dirName);
			FISCDResponse cd = new FISCDResponse(dir, "Current directory is " + dir);
			response.setCd(cd);
			
			
		} catch (ResourceNotFoundException e) {
			
			int status = HttpServletResponse.SC_NOT_IMPLEMENTED; //status 501
			httpResponse.setStatus(status);
			FISErrorResponse error = FileSystemUtil.getErrorResponse(httpResponse, e, status);
			response = new FISResponse(error);
		} catch (UnknownCommandException e) {
			int status = HttpServletResponse.SC_SERVICE_UNAVAILABLE; //status 503
			FISErrorResponse error = FileSystemUtil.getErrorResponse(httpResponse, e, status);
			httpResponse.setStatus(status);
			response = new FISResponse(error);
		}
    	
    	return response;
    }
    
   
}
