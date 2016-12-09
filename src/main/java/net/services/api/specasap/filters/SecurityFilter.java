package net.services.api.specasap.filters;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

@Provider
public class SecurityFilter implements ContainerRequestFilter{

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";	 //keep the space
	private static final String SECURED_ELEMENT_URI_PREFIX = "v1/elements";
	private static final String SECURED_PRODUCTLIST_URI_PREFIX = "v1/product";
	private static final String SECURED_LEGAL_URI_PREFIX = "v1/legal";
	private static final String UNAUTHORIZED_ERROR_MESSAGE = "User cannot access this resource.";
	
	final static Logger logger = Logger.getLogger(SecurityFilter.class);
	MongoDatabase db = null;
	String authToken = null;
	private Boolean isAuthorized = false;
	MongoClient mongoClient;
	@Context private ServletContext servletContext;
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
			
		if(requestContext.getUriInfo().getPath().contains(SECURED_ELEMENT_URI_PREFIX) ||
				requestContext.getUriInfo().getPath().contains(SECURED_PRODUCTLIST_URI_PREFIX) ||
				requestContext.getUriInfo().getPath().contains(SECURED_LEGAL_URI_PREFIX)){
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if(authHeader != null && authHeader.size() > 0){
				authToken = authHeader.get(0);
				authToken = authToken.replace(AUTHORIZATION_HEADER_PREFIX, "");

				mongoClient = (MongoClient) servletContext.getAttribute("MONGODB_CLIENT");
				System.out.println("User credentials are: " + mongoClient.getCredentialsList());
				
				FindIterable<Document> iterable = null;
				try{

					db = mongoClient.getDatabase(servletContext.getInitParameter("MONGODB_DATABASE"));
					System.out.println("Security - db collection names :" + db);

			
				} catch (MongoException e) {
					e.printStackTrace();
					logger.error("Mongo Exception Error");
					System.err.println(e.getClass().getName() + ": " + e.getMessage());
			
				}

				iterable = db.getCollection("credentials").find(
						new Document("basicAuth", authToken));

				iterable.forEach(new Block<Document>() {
					
					@Override
					public void apply(final Document document) {
						if(authToken.equals(document.getString("basicAuth"))){
							setIsAuthorized(true);
							}
						}
					});
			}
			
	
			if(getIsAuthorized()){
				setIsAuthorized(false);
				return;
			} else {
			logger.error("Unauthorized access from " + requestContext.getHeaderString(AUTHORIZATION_HEADER_KEY));
			Response unauthorizedStatus = Response
					.status(Status.UNAUTHORIZED)
					.entity(UNAUTHORIZED_ERROR_MESSAGE)
					.type(MediaType.TEXT_PLAIN)
					.build();
			setIsAuthorized(false);
			requestContext.abortWith(unauthorizedStatus);
			}
		}
	}
	
	private Boolean getIsAuthorized() {
		return isAuthorized;
	}
	
	private void setIsAuthorized(Boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}
}

