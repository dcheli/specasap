package net.services.api.specasap.resources;

import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

@Path("/")
@Singleton // not sure if this should be a singleton vs. having a new instance per request; 
public class LegalResource {
	
	Logger logger = Logger.getLogger(ElementResource.class);
	
	private @Context ServletContext servletContext; 
	
	@GET
	@Path("/privacypolicy")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPrivacyPolicy() {
		return "Privacy Policy";
	}

	@GET
	@Path("/termsofservice")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTermsOfService() {
		return "Terms Of Service";
	}
	


}
