package net.services.api.specasap.resources;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import net.services.api.specasap.model.PrivacyPolicy;
import net.services.api.specasap.model.TermsOfService;
import net.services.api.specasap.services.LegalService;


@Path("/{version}/legal")
@Singleton // not sure if this should be a singleton vs. having a new instance per request; 
public class LegalResource {
	
	Logger logger = Logger.getLogger(ElementResource.class);	
	private @Context ServletContext servletContext; 
	
	@GET
	@Path("/privacypolicy")
	@Produces(MediaType.APPLICATION_JSON)
	public PrivacyPolicy getPrivacyPolicy(@Context UriInfo uriInfo,
			@Context HttpServletRequest request)throws IOException {
		LegalService legalService = new LegalService(request.getServletContext());
		PrivacyPolicy privacyPolicy = legalService.getPrivacyPolicy();
		return privacyPolicy;
	}

	@GET
	@Path("/termsofservice")
	@Produces(MediaType.APPLICATION_JSON)
	public TermsOfService getTermsOfService(@Context UriInfo uriInfo,
		@Context HttpServletRequest request)throws IOException {
		
		LegalService legalService = new LegalService(request.getServletContext());
		TermsOfService termsOfService = legalService.getTermsOfService();
		return termsOfService;
	}
}
	
