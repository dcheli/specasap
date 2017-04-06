package net.services.api.specasap.resources;

import java.io.IOException;
import java.util.List;

import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import net.services.api.specasap.model.Product;
import net.services.api.specasap.model.ProductSet;
import net.services.api.specasap.services.ProductService;

@Path("/{version}/products")
@Singleton // not sure if this should be a singleton vs. having a new instance per request; 
public class ProductResource {
	Logger logger = Logger.getLogger(ElementResource.class);	
	private @Context ServletContext servletContext; 

	@GET
	@Path("/productlist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductSet> getProductList (@Context UriInfo uriInfo,
				@DefaultValue("ios") @QueryParam("os") String os,
				@Context HttpServletRequest request)throws IOException {
		
		ProductService productService = new ProductService(request.getServletContext());
		
		List<ProductSet> productSetList = productService.getProductList(os);		
		return productSetList;
	}	
}
