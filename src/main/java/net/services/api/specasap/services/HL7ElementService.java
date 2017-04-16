package net.services.api.specasap.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import net.services.api.specasap.exceptions.DataNotFoundException;
import net.services.api.specasap.model.HL7Element;

public class HL7ElementService {
	
	MongoDatabase db = null;
	MongoClient mongoClient = null;
	HL7Element element = null;
	Logger logger = Logger.getLogger(HL7ElementService.class);
	
	public HL7ElementService(ServletContext servletContext) throws IOException {

		try{
			mongoClient = (MongoClient) servletContext.getAttribute("MONGODB_CLIENT");
			db = mongoClient.getDatabase(servletContext.getInitParameter("MONGODB_DATABASE"));
		} catch(Exception e) {
			logger.error("HL7ElementService: " + e);
			logger.error("Mongo error " + e.getMessage());
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	public List<HL7Element> getElement(String searchParam, String collectionVersion) {
		List<HL7Element> elementList = new ArrayList<HL7Element>();
		
		if(!("").equals(searchParam)) {
			String collection = "hl7Elements" + collectionVersion;
			FindIterable<Document> iterable = db.getCollection(collection).find(
					new Document("tags", java.util.regex.Pattern.compile(searchParam, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
			
			iterable.forEach(new Block<Document>() {
				@SuppressWarnings("unchecked")
				@Override
				public void apply(final Document document) {

					String elementId = document.containsKey("elementId") ? document.getString("elementId") : "";           		    
					String segmentId;
					String segmentName;
					String elementName;
					Integer sequence;
					String length;
					String conformanceLength;
					String dataType;
					String optionality;
					String repetition;
					String tableNumber;
					String itemNumber;
					String definition;

					String[] versions = null;
					String[] transactions = null;
					
					List<Document> attributes = document.containsKey("attributes") ? (List<Document>)document.get("attributes") : null;
					if((attributes != null) && !attributes.isEmpty()){
						segmentId = attributes.get(0).containsKey("segmentId") ? attributes.get(0).getString("segmentId") : "";
						segmentName = attributes.get(0).containsKey("segmentName") ? attributes.get(0).getString("segmentName") : "";
						elementName = attributes.get(0).containsKey("elementName") ? attributes.get(0).getString("elementName") : "";
						sequence = attributes.get(0).containsKey("sequence") ? attributes.get(0).getInteger("sequence") : 0;
						length = attributes.get(0).containsKey("length") ? attributes.get(0).getString("length") : "";						
						conformanceLength = attributes.get(0).containsKey("conformanceLength") ? attributes.get(0).getString("conformanceLength") : "";
						dataType = attributes.get(0).containsKey("dataType") ? attributes.get(0).getString("dataType") : "";
						optionality = attributes.get(0).containsKey("optionality") ? attributes.get(0).getString("optionality") : "";
						repetition = attributes.get(0).containsKey("repetition") ? attributes.get(0).getString("repetition") : "";
						tableNumber = attributes.get(0).containsKey("tableNumber") ? attributes.get(0).getString("tableNumber") : "";
						itemNumber = attributes.get(0).containsKey("itemNumber") ? attributes.get(0).getString("itemNumber") : "";
						definition = attributes.get(0).containsKey("definition") ? attributes.get(0).getString("definition") : "";
							
						if(attributes.get(0).containsKey("versions")) {
							if(attributes.get(0).get("versions") == null) {
								versions = new String[]{};
							} else {
								List<Document> v = (List<Document>) attributes.get(0).get("versions");
								versions = v.stream().toArray(String[]::new);
							}
						}
						
						if(attributes.get(0).containsKey("transactions")){
							if(attributes.get(0).get("transactions") == null){
								transactions = new String[]{};
							} else {	
								List<Document> transactionList = (List<Document>) attributes.get(0).get("transactions");
								transactions = transactionList.stream().toArray(String[]::new);
							}
						}
						
						element =  new HL7Element(elementId, segmentId, segmentName, elementName, sequence, 
								length, conformanceLength, dataType, optionality, repetition, tableNumber, 
								itemNumber, versions, transactions, definition);
						elementList.add(element);
					}	
				}
			});
		
		} else {

			logger.error("HL7ElementService: getList: "+ searchParam + "; is Missing");
			throw new DataNotFoundException("A search parameter of one or more characters is required.");
		}
		
		if(elementList.isEmpty()) {
			logger.error("HL7ElementService: getList: EmptyList");
			throw new DataNotFoundException("Nothing was found for Search Parameter " + searchParam + ".");
		} else {
			return elementList;
		}
	}
}
