package net.services.api.specasap.services;

import java.io.IOException;
import java.util.regex.Pattern;

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

public class NCPDPCodeSetService {
	
	MongoDatabase db = null;
	MongoClient mongoClient = null;
	Logger logger = Logger.getLogger(NCPDPCodeSetService.class);
	CodeSet codeSet = new CodeSet();
	
	public NCPDPCodeSetService(ServletContext servletContext) throws IOException {

		try{
			mongoClient = (MongoClient) servletContext.getAttribute("MONGODB_CLIENT");
			db = mongoClient.getDatabase(servletContext.getInitParameter("MONGODB_DATABASE"));
			System.out.println("User credentials are: " + mongoClient.getCredentialsList());
		} catch(Exception e) {
			logger.error("NCPDPElementService: " + e);
			logger.error("Mongo error " + e.getMessage());
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public CodeSet getCodeSet(String searchParam, String collectionVersion) {
		
		if(!("").equals(searchParam)) {
			String collection = "ncpdpD0CodeSets";

			FindIterable<Document> iterable = db.getCollection(collection).find(
				new Document("elementIds", java.util.regex.Pattern.compile(searchParam, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
		
			iterable.forEach(new Block<Document>() {
				@SuppressWarnings("unchecked")
				@Override
				public void apply(final Document document) {
					System.out.print("Codeset is " + document);
					ObjectMapper mapper = new ObjectMapper();
					mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					String json = document.toJson();

		    		try {
		    			
						codeSet = mapper.readValue(json, CodeSet.class);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			});
		}
		
		
		return codeSet;
	}



}
