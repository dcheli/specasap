package net.services.api.specasap.resources;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import net.services.api.specasap.model.IAPReceipt;
import net.services.api.specasap.services.IAPReceiptService;


@Path("/{version}/IAPReceipt")
@Singleton // not sure if this should be a singleton vs. having a new instance per request; 
public class IAPReceiptResource {
	
	Logger logger = Logger.getLogger(IAPReceiptResource.class);	
	private @Context ServletContext servletContext; 
	
	@POST
	@Path("/verifyapplereceipt")
	@Produces(MediaType.APPLICATION_JSON)
	public IAPReceipt verifyReceipt(@Context UriInfo uriInfo,
			@QueryParam("r") String receipt,
			@Context HttpServletRequest request)throws IOException {
		System.out.println("Hello receipt");
		IAPReceiptService iapReceiptService = new IAPReceiptService(request.getServletContext());
		iapReceiptService.verifyAppleReceipt(receipt);
	
		return null;
	}

}
