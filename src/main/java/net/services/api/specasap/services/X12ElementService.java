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
import net.services.api.specasap.model.X12Element;

public class X12ElementService {
	
	MongoDatabase db = null;
	MongoClient mongoClient = null;
	X12Element element = null;
	Logger logger = Logger.getLogger(X12ElementService.class);
	
	public X12ElementService(ServletContext servletContext) throws IOException {

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
	
	// not sure I need or want this method
//	public List<X12Element> getAllElements() {
//		List<X12Element> X12ElementList = new ArrayList<X12Element>();
//		if(elementMap.isEmpty())
//			System.out.println("Map is empty");
		
//		for(Map.Entry<String, X12Element> entry : elementMap.entrySet()){ 
//			X12ElementList.add(entry.getValue()); 
//			}

//		return X12ElementList;	
//	}
	
	public List<X12Element> getElement(String searchParam, String collectionVersion) {	
		List<X12Element> elementList = new ArrayList<X12Element>();

		if(!("").equals(searchParam)) {
			String collection = "x12" + collectionVersion;

			FindIterable<Document> iterable = db.getCollection(collection).find(
				new Document("tags", java.util.regex.Pattern.compile(searchParam, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
						
				iterable.forEach(new Block<Document>() {
					@SuppressWarnings("unchecked")
					@Override
					public void apply(final Document document) {
						
						String name = document.containsKey("name") ? document.getString("name") : "";
						String elementName = "";
						String segmentId =  "";
						String segmentName = "";
						String dataType = "";
						String usage = "";
						String length = "";
						String implementationName = "";
						int elementRepeat;
						String loop;
						int minimumLength;
						int maximumLength;
						int dataElement;
								
						String[] codes = null;
						String[] transactions = null;
						String[] versions = null;

						List<Document> attributes = document.containsKey("attributes") ? (List<Document>)document.get("attributes") : null;
						if((attributes != null) && !attributes.isEmpty()){
							elementName = attributes.get(0).containsKey("elementName") ? attributes.get(0).getString("elementName") : "";
							segmentId = attributes.get(0).containsKey("segmentId") ? attributes.get(0).getString("segmentId") : "";
							segmentName = attributes.get(0).containsKey("segmentName") ? attributes.get(0).getString("segmentName") : "";
							dataType = attributes.get(0).containsKey("dataType") ? attributes.get(0).getString("dataType") : "";
							usage = attributes.get(0).containsKey("usage") ? attributes.get(0).getString("usage") : "";
							length = attributes.get(0).containsKey("length") ? attributes.get(0).getString("length") : "";
							implementationName = attributes.get(0).containsKey("implementationName") ? attributes.get(0).getString("implementationName") : "";
							elementRepeat = attributes.get(0).containsKey("elementRepeat") ? attributes.get(0).getInteger("elementRepeat") : 0;
							loop = attributes.get(0).containsKey("loop") ? attributes.get(0).getString("loop") : "";
							minimumLength = attributes.get(0).containsKey("minimumLength") ? attributes.get(0).getInteger("minimumLength") : 0;
							maximumLength = attributes.get(0).containsKey("maximumLength") ? attributes.get(0).getInteger("maximumLength") : 0;
							dataElement = attributes.get(0).containsKey("dataElement") ? attributes.get(0).getInteger("dataElement") : 0;

							if(attributes.get(0).containsKey("codes")) {						
								if(attributes.get(0).get("codes") == null){			
									codes = new String[]{};
								} else {
									List<Document> c = (List<Document>) attributes.get(0).get("codes");
									codes = c.stream().toArray(String[]::new);
								}
							}

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
						
							element =  new X12Element(name, segmentId,segmentName,elementName, versions, 
									dataType, usage, transactions, dataElement, implementationName, 
									elementRepeat, codes, loop, length, minimumLength, maximumLength);
				
							elementList.add(element);
						}
					}
				});
		
		} else { 
			System.out.println(searchParam + " is Missing"); 
			throw new DataNotFoundException("Search Parameter " + searchParam + " is missing.");
		}
		
		if(elementList.isEmpty()) {
			throw new DataNotFoundException("Search Parameter " + searchParam + " is not found.");
		} else {
			return elementList;
		}
	}
}
