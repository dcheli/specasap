package net.services.api.specasap.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.apache.log4j.Logger;
import net.services.api.specasap.model.CodeSet;
import net.services.api.specasap.model.NCPDPElement;
import net.services.api.specasap.services.NCPDPCodeSetService;

@Path("/{version}/codesets")
@Singleton
public class CodeSetResource {
	Logger logger = Logger.getLogger(CodeSetResource.class);
	
	private @Context ServletContext servletContext; 
	
	@GET
	@Path("/ncpdp/{searchParam}")
	@Produces(MediaType.APPLICATION_JSON) 	
	public CodeSet getNCPDPCodeSet(@PathParam("searchParam") 
	@Pattern(regexp = "[a-zA-Z0-9-\\s]+", message="The search parameter contains invalid characters.") String searchParam,
	@DefaultValue("D0") @QueryParam("v") String collectionVersion,
	@Context UriInfo uriInfo,
	@Context HttpServletRequest request) throws IOException{
		System.out.print("searchParamater is " + searchParam);

		CodeSet codeSet = new CodeSet();
		NCPDPCodeSetService ncpdpCodeSetService = new NCPDPCodeSetService(request.getServletContext());
		codeSet = ncpdpCodeSetService.getCodeSet(searchParam, collectionVersion);
		
		
		return codeSet;
	}

}
