package net.services.api.specasap.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import net.services.api.specasap.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements  ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage("An error occurred with a Server; support staff have been notified. "
				+ "Please try again later or contact support at support@something.com",	500, "http://helppage");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}

}
