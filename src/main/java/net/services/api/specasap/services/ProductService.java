package net.services.api.specasap.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import net.services.api.specasap.exceptions.DataNotFoundException;
import net.services.api.specasap.model.PrivacyPolicy;
import net.services.api.specasap.model.Product;
import net.services.api.specasap.model.X12Element;


public class ProductService {
	
	Product product = null;
	MongoDatabase db = null;
	Logger logger = Logger.getLogger(ProductService.class);
	MongoClient mongoClient = null;
	
	public ProductService(ServletContext servletContext) throws IOException {

		try{
			mongoClient = (MongoClient) servletContext.getAttribute("MONGODB_CLIENT");
			db = mongoClient.getDatabase(servletContext.getInitParameter("MONGODB_DATABASE"));
			System.out.println("User credentials are: " + mongoClient.getCredentialsList());
		} catch(Exception e) {
			e.printStackTrace();
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
				String productId = "";
				String operatingSystem = "";
				String version = "";
				System.out.println(document);
				if(!document.isEmpty()) {
					productId = document.containsKey("productId") ? document.getString("productId") : "";
					operatingSystem = document.containsKey("operatingSystem") ? document.getString("operatingSystem") : "";
					version = document.containsKey("version") ? document.getString("version") : "";
					
					product = new Product(productId, operatingSystem, version);
					productList.add(product);
				}
			}
		});
		return productList;
	}
}
