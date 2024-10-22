package net.services.api.specasap.resources;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.json.JsonObject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import org.apache.log4j.Logger;
import net.services.api.specasap.model.EnabledProduct;
import net.services.api.specasap.services.IAPReceiptService;

@Path("/{version}/IAPReceipt")
@Singleton // not sure if this should be a singleton vs. having a new instance per request; 
public class IAPReceiptResource {
	
	Logger logger = Logger.getLogger(IAPReceiptResource.class);	
	private @Context ServletContext servletContext; 
	
	@POST
	@Path("/verifyapplereceipt")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(value={MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public ArrayList<EnabledProduct> verifyReceipt(@Context UriInfo uriInfo,
			String receipt,
			@Context HttpServletRequest request)throws IOException, ParseException {
		//you need to get what is sent in the body of the request
		System.out.println("/verifyapplereceipt is called with ");
		//logger.error("/verifyapplereceipt is called with " );
		IAPReceiptService iapReceiptService = new IAPReceiptService(request.getServletContext());
		List<EnabledProduct> enabledProducts = iapReceiptService.verifyAppleReceipt(receipt);
		System.out.println("received " + enabledProducts);
	
		return (ArrayList<EnabledProduct>)enabledProducts;
		
	}

}
