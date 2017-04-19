package net.services.api.specasap.services;

import java.io.IOException;
import java.util.ArrayList;


import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import net.services.api.specasap.model.CodeSet;
import net.services.api.specasap.model.Product;
import net.services.api.specasap.model.ProductSet;

public class ProductService {
	
	ProductSet productSet = null;
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
	
	public ArrayList<ProductSet> getProductList(String os) {
		
		ArrayList<ProductSet> productSetList = new ArrayList<ProductSet>();
		
		String collection = "products";
		System.out.println("database is " + db.getCollection(collection).toString());
		
		FindIterable<Document> iterable = db.getCollection(collection).find(
				new Document("operatingSystems",os));
			
		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document document) {
								
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				String json = document.toJson();
	    		try {
					productSet = mapper.readValue(json, ProductSet.class);
					productSetList.add(productSet);
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});
		
		return productSetList;
	}
}
