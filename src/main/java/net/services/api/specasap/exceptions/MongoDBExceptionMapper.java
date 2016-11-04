package net.services.api.specasap.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import net.services.api.specasap.model.ErrorMessage;

public class MongoDBExceptionMapper implements ExceptionMapper<MongoDBException>{

	@Override
	public Response toResponse(MongoDBException e) {
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage(),500, "http://helppage");	
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}

}
