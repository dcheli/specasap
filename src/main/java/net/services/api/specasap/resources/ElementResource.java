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

import net.services.api.specasap.model.CCDPlusElement;
import net.services.api.specasap.model.HL7Element;
import net.services.api.specasap.model.NCPDPElement;
import net.services.api.specasap.model.X12Element;
import net.services.api.specasap.services.NCPDPElementService;
import net.services.api.specasap.services.X12ElementService;
import net.services.api.specasap.services.CCDPlusElementService;
import net.services.api.specasap.services.HL7ElementService;

@Path("/{version}/elements")
@Singleton // not sure if this should be a singleton vs. having a new instance per request; 
public class ElementResource  {
	Logger logger = Logger.getLogger(ElementResource.class);
	
	private @Context ServletContext servletContext; 


	
	/* enforcing a regular expression @Path("users/{username: [a-zA-Z][a-zA-Z_0-9]*}")
	 * In this type of example the searchParam variable will only match user names that begin with one upper or lower case letter and zero or 
	 * more alpha numeric characters and the underscore character. If a user name does not match that a 404 (Not Found) response will occur.
	 *  
	 */
	
	@GET
	@Path("/ncpdp/{searchParam}")
	@Produces(MediaType.APPLICATION_JSON) 	
	public ArrayList<NCPDPElement> getNCPDPElement(@PathParam("searchParam") 
			@Pattern(regexp = "[a-zA-Z0-9-\\s]+", message="The search parameter contains invalid characters.") String searchParam,
			@DefaultValue("D0") @QueryParam("v") String collectionVersion,
			@Context UriInfo uriInfo,
			@Context HttpServletRequest request) throws IOException{
		
		final List<NCPDPElement> elementList = new ArrayList<>();
		NCPDPElementService ncpdpElementService = new NCPDPElementService(request.getServletContext());
		List<NCPDPElement> returnList = ncpdpElementService.getElement(searchParam, collectionVersion);
		returnList.forEach(element -> elementList.add(element));		
		return (ArrayList<NCPDPElement>) elementList;
	}
	
	@GET
	@Path("/x12/{searchParam}")
	@Produces(MediaType.APPLICATION_JSON) 
	public ArrayList<X12Element> getX12Element(@PathParam("searchParam")
			@Pattern(regexp = "[a-zA-Z0-9-\\s]+", message="The search parameter contains invalid characters.") String searchParam,
			@DefaultValue("5010") @QueryParam("v") String collectionVersion,
			@Context UriInfo uriInfo,
			@Context HttpServletRequest request) throws IOException{
		
		final List<X12Element> elementList = new ArrayList<>();
		X12ElementService x12ElementService = new X12ElementService(request.getServletContext());
		List<X12Element> returnList = x12ElementService.getElement(searchParam, collectionVersion);
		returnList.forEach(element -> elementList.add(element));		
		return (ArrayList<X12Element>) elementList;
	}
	
	
	@GET
	@Path("/hl7/{searchParam}")
	@Produces(MediaType.APPLICATION_JSON) 
	public ArrayList<HL7Element> getHL7Element(@PathParam("searchParam") 
			@Pattern(regexp = "[a-zA-Z0-9-\\s]+", message="The search parameter contains invalid characters.") String searchParam,
			@DefaultValue("282") @QueryParam("v") String collectionVersion,
			@Context UriInfo uriInfo,
			@Context HttpServletRequest request) throws IOException{
		
		final List<HL7Element> elementList = new ArrayList<>();
		HL7ElementService hl7ElementService = new HL7ElementService(request.getServletContext());
		List<HL7Element> returnList = hl7ElementService.getElement(searchParam, collectionVersion);
		returnList.forEach(element -> elementList.add(element));
		return (ArrayList<HL7Element>) elementList;
	}

	@GET
	@Path("/ccdplus/{searchParam}")
	@Produces(MediaType.APPLICATION_JSON) 
	public ArrayList<CCDPlusElement> getCCDPlusElement(@PathParam("searchParam") 
			@Pattern(regexp = "[a-zA-Z0-9-\\s]+", message="The search parameter contains invalid characters.") String searchParam,
			@DefaultValue("2") @QueryParam("v") String collectionVersion,
			@Context UriInfo uriInfo,
			@Context HttpServletRequest request) throws IOException{
		
		final List<CCDPlusElement> elementList = new ArrayList<>();
		CCDPlusElementService ccdPlusElementService = new CCDPlusElementService(request.getServletContext());
		List<CCDPlusElement> returnList = ccdPlusElementService.getElement(searchParam, collectionVersion);
		returnList.forEach(element -> elementList.add(element));
		return (ArrayList<CCDPlusElement>) elementList;
	}

}
