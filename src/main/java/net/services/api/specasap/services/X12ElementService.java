package net.services.api.specasap.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import net.services.api.specasap.exceptions.DataNotFoundException;
import net.services.api.specasap.model.X12Element;

public class X12ElementService {
	
	HashMap<String, X12Element> elementMap = new HashMap<>();
	MongoDatabase db = null;
	MongoCollection<Document> collection = null;
	X12Element element = null;
	
	public X12ElementService(ServletContext servletContext) throws IOException {

		try{

			MongoClient mongoClient = (MongoClient) servletContext.getAttribute("MONGODB_CLIENT");
			db = mongoClient.getDatabase("spec");	
			
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} 
	}
	
	public List<X12Element> getAllElements() {
		List<X12Element> X12ElementList = new ArrayList<X12Element>();
		if(elementMap.isEmpty())
			System.out.println("Map is empty");
		
		for(Map.Entry<String, X12Element> entry : elementMap.entrySet()){ 
			X12ElementList.add(entry.getValue()); 
			}

		return X12ElementList;	
	}
	
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

						List<Document> attributes = (List<Document>)document.get("attributes");

						String[] transactions = null;
						if(attributes.get(0).containsKey("transactions")){
							if(attributes.get(0).get("transactions") == null){
								transactions = new String[]{};
							} else {	
								List<Document> transactionList = (List<Document>) attributes.get(0).get("transactions");
								transactions = transactionList.stream().toArray(String[]::new);
							}
						}
						
						String[] codeSet = null;
						if(attributes.get(0).containsKey("codeSet")) {						
							if(attributes.get(0).get("codeSet") == null){			
								codeSet = new String[]{};
							} else {
								List<Document> codes = (List<Document>) attributes.get(0).get("codeSet");
								codeSet = codes.stream().toArray(String[]::new);
							}
						}
	
						element =  new X12Element(document.getString("name"), attributes.get(0).getString("segmentId"), 
								attributes.get(0).getString("segmentName"), attributes.get(0).getString("elementName"), 
								attributes.get(0).getString("version"), attributes.get(0).getString("dataType"),
								transactions, attributes.get(0).getString("requirementDesignator"), attributes.get(0).getInteger("dataElementNumber"),
								attributes.get(0).getString("implementationName"), attributes.get(0).getInteger("elementRepeat"), 
								codeSet, attributes.get(0).getString("loop"), attributes.get(0).getString("length"), 
								attributes.get(0).getInteger("minimumLength"),attributes.get(0).getInteger("maximumLength"));
				
						elementList.add(element);

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
