package net.services.api.specasap.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter{
	

	Logger logger = Logger.getLogger(LoggingFilter.class);

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		logger.error("LoggingFilter: ResponseContext: "  + responseContext.getHeaders());
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.error("LoggingFilter: RequestContext: "  + requestContext.getHeaders());		
	}

}
