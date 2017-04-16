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
import net.services.api.specasap.model.NCPDPElement;

public class NCPDPElementService {

	MongoDatabase db = null;
	MongoClient mongoClient = null;
	NCPDPElement element = null;
	Logger logger = Logger.getLogger(NCPDPElementService.class);
	
	public NCPDPElementService(ServletContext servletContext) throws IOException {

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
	
	public List<NCPDPElement> getElement(String searchParam, String collectionVersion) {
		List<NCPDPElement> elementList = new ArrayList<NCPDPElement>();

		if(!("").equals(searchParam)) {
			String collection = "ncpdpElements" + collectionVersion;
			System.out.println("database is " + db.getCollection(collection).toString());
			
			FindIterable<Document> iterable = db.getCollection(collection).find(
				new Document("tags", java.util.regex.Pattern.compile(searchParam, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
							
			iterable.forEach(new Block<Document>() {
				@SuppressWarnings("unchecked")
				@Override
				public void apply(final Document document) {
					
					String elementId = document.containsKey("elementId") ? document.getString("elementId") : "";
					String elementName = "";
					String definition = "";
					String comments = "";
					String[] segmentIds = null;
					String[] segmentNames = null;
					String[] codes = null;
					String[] transactions = null;
					String[] requestTransactions = null;
					String[] responseTransactions = null;
					String[] standardFormats = null;
					String[] fieldFormats = null;
					String[] lengths = null;
					String[] versions = null;
					
					List<Document> attributes = document.containsKey("attributes") ? (List<Document>)document.get("attributes") : null;
					if(attributes != null){
						if(!attributes.isEmpty()){
							elementName = attributes.get(0).containsKey("elementName") ? attributes.get(0).getString("elementName") : "";
							comments = attributes.get(0).containsKey("comments") ? attributes.get(0).getString("comments") : "";
							definition = attributes.get(0).containsKey("definition") ? attributes.get(0).getString("definition") : "";	

							if(attributes.get(0).containsKey("segmentIds")) {						
								if(attributes.get(0).get("segmentIds") == null){			
									segmentIds = new String[]{};
								} else {
									List<Document> c = (List<Document>) attributes.get(0).get("segmentIds");
									segmentIds = c.stream().toArray(String[]::new);
								}
							}

							if(attributes.get(0).containsKey("segmentNames")) {						
								if(attributes.get(0).get("segmentNames") == null){			
									segmentNames = new String[]{};
								} else {
									List<Document> c = (List<Document>) attributes.get(0).get("segmentNames");
									segmentNames = c.stream().toArray(String[]::new);
								}
							}

							
							if(attributes.get(0).containsKey("codes")) {						
								if(attributes.get(0).get("codes") == null){			
									codes = new String[]{};
								} else {
									List<Document> c = (List<Document>) attributes.get(0).get("codes");
									codes = c.stream().toArray(String[]::new);
								}
							}

							if(attributes.get(0).containsKey("versions")) {						
								if(attributes.get(0).get("versions") == null){			
									versions = new String[]{};
								} else {
									List<Document> c = (List<Document>) attributes.get(0).get("versions");
									versions = c.stream().toArray(String[]::new);
								}
							}

							if(attributes.get(0).containsKey("lengths")) {						
								if(attributes.get(0).get("lengths") == null){			
									lengths = new String[]{};
								} else {
									List<Document> c = (List<Document>) attributes.get(0).get("lengths");
									lengths = c.stream().toArray(String[]::new);
								}
							}
							
							if(attributes.get(0).containsKey("transactions")) {						
								if(attributes.get(0).get("transactions") == null){			
									transactions = new String[]{};
								} else {
									List<Document> c = (List<Document>) attributes.get(0).get("transactions");
									transactions = c.stream().toArray(String[]::new);
								}
							}

							if(attributes.get(0).containsKey("requestTransactions")) {						
								if(attributes.get(0).get("requestTransactions") == null){			
									requestTransactions = new String[]{};
								} else {
									List<Document> c = (List<Document>) attributes.get(0).get("requestTransactions");
									requestTransactions = c.stream().toArray(String[]::new);
								}
							}

							if(attributes.get(0).containsKey("responseTransactions")) {						
								if(attributes.get(0).get("responseTransactions") == null){			
									responseTransactions = new String[]{};
								} else {
									List<Document> c = (List<Document>) attributes.get(0).get("responseTransactions");
									responseTransactions = c.stream().toArray(String[]::new);
								}
							}

							
							if(attributes.get(0).containsKey("standardFormats")) {						
								if(attributes.get(0).get("standardFormats") == null){			
									standardFormats = new String[]{};
								} else {
									List<Document> c = (List<Document>) attributes.get(0).get("standardFormats");
									standardFormats = c.stream().toArray(String[]::new);
								}
							}

							if(attributes.get(0).containsKey("fieldFormats")) {						
								if(attributes.get(0).get("fieldFormats") == null){			
									fieldFormats = new String[]{};
								} else {
									List<Document> c = (List<Document>) attributes.get(0).get("fieldFormats");
									fieldFormats = c.stream().toArray(String[]::new);
								}
							}
						}
					}
					
					element = new NCPDPElement(elementId, segmentIds, segmentNames, elementName, versions, 
							transactions, fieldFormats, codes, lengths,	standardFormats, definition, 
							requestTransactions, responseTransactions, comments);
					
						elementList.add(element);
				}
			});

		} else {
			System.out.println(searchParam + "; is Missing"); 
			throw new DataNotFoundException("A search parameter of one or more characters is required.");
		}
		if(elementList.isEmpty() || elementList == null) {
			System.out.println("Emtpy List");
			throw new DataNotFoundException("Nothing was found for Search Parameter " + searchParam + ".");
		} else {
			return elementList;
		}
	}
}
