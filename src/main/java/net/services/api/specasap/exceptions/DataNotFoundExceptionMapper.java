package net.services.api.specasap.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import net.services.api.specasap.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404, "http://helppage");	
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
