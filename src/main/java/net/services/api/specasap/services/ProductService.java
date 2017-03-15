package net.services.api.specasap.services;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import net.services.api.specasap.model.Product;

public class ProductService {
	
	Product product = null;
	MongoDatabase db = null;
	Logger logger = Logger.getLogger(ProductService.class);
	MongoClient mongoClient = null;
	
	
	public ProductService() {
		super();
	}

	public ProductService(ServletContext servletContext) throws IOException {

		try{
			mongoClient = (MongoClient) servletContext.getAttribute("MONGODB_CLIENT");
			db = mongoClient.getDatabase(servletContext.getInitParameter("MONGODB_DATABASE"));
			System.out.println("User credentials are: " + mongoClient.getCredentialsList());
		} catch(Exception e) {
			logger.error("ProductService: " + e);
			logger.error("Mongo error " + e.getMessage());
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public ArrayList<Product> getProductList(String os) {
		
		ArrayList<Product> productList = new ArrayList<Product>();
		
		String collection = "products";
		System.out.println("database is " + db.getCollection(collection).toString());
		
		FindIterable<Document> iterable = db.getCollection(collection).find(
				new Document("operatingSystem",os));
			
		iterable.forEach(new Block<Document>() {
			@SuppressWarnings("unchecked")
			@Override
			public void apply(final Document document) {
				String productId;
				String operatingSystem;
				String version;
				String displayName;
				System.out.println(document);
				if(!document.isEmpty()) {
					productId = document.containsKey("productId") ? document.getString("productId") : "";
					operatingSystem = document.containsKey("operatingSystem") ? document.getString("operatingSystem") : "";
					version = document.containsKey("version") ? document.getString("version") : "";
					displayName = document.containsKey("displayName") ? document.getString("displayName") : "";
					product = new Product(productId, operatingSystem, version, displayName);
					productList.add(product);
				}
			}
		});
		return productList;
	}
}
