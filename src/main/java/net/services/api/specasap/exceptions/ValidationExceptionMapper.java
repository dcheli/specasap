package net.services.api.specasap.exceptions;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import net.services.api.specasap.model.ErrorMessage;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException>{
	
	@Override
	public Response toResponse(ValidationException ex) {
		System.out.println("Error message is: " + ex.getMessage());
		ErrorMessage errorMessage = new ErrorMessage("Invalid characters in search parameter. Characters constrained to alpha-numeric and '-'.",400,"http://helppage");	
		return Response.status(Status.BAD_REQUEST)
				.entity(errorMessage)
				.build();
	}

}
