package net.services.api.specasap.services;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

	HashMap<String, NCPDPElement> elementMap = new HashMap<>();
	NCPDPElement element = null;
	MongoDatabase db = null;
	Logger logger = Logger.getLogger(NCPDPElementService.class);
	MongoClient mongoClient = null;
	
	public NCPDPElementService(ServletContext servletContext) throws IOException {

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
	

	// this needs to expand to include searching for a name, not just and ID and include wild cards
	public List<NCPDPElement> getElement(String searchParam, String collectionVersion) {
		List<NCPDPElement> elementList = new ArrayList<NCPDPElement>();

		if(!("").equals(searchParam)) {
			String collection = "ncpdp" + collectionVersion;
			System.out.println("database is " + db.getCollection(collection).toString());
			
			FindIterable<Document> iterable = db.getCollection(collection).find(
				new Document("tags", java.util.regex.Pattern.compile(searchParam, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
							
			iterable.forEach(new Block<Document>() {
				@SuppressWarnings("unchecked")
				@Override
				public void apply(final Document document) {
					
					String name = document.containsKey("name") ? document.getString("name") : "";
					String segmentId =  "";
					String segmentName = "";
					String elementName = "";
					String version = "";
					String dataType = "";
					String usage = "";
					String fieldFormat = "";
					String codeSet = "";
					String definition = "";
					//Integer minimumLength = 0;
					//Integer maximumLength = 0;
					List<Document> lengthList = null;
					List<Document> transactionList = null;
					List<Document> standardFormatsList = null;
					List<Document> fieldFormatList = null;
					String[] transactions = null;
					String[] standardFormats = null;
					String[] fieldFormats = null;
					String[] length = null;
					
					List<Document> attributes = document.containsKey("attributes") ? (List<Document>)document.get("attributes") : null;
					if(attributes != null){
						if(!attributes.isEmpty()){
							elementName = attributes.get(0).containsKey("elementName") ? attributes.get(0).getString("elementName") : "";
							segmentId = attributes.get(0).containsKey("segmentId") ? attributes.get(0).getString("segmentId") : "";
							segmentName = attributes.get(0).containsKey("segmentName") ? attributes.get(0).getString("segmentName") : "";
							version = attributes.get(0).containsKey("version") ? attributes.get(0).getString("version")  : "";
							dataType = attributes.get(0).containsKey("dataType") ? attributes.get(0).getString("dataType") : "";
							usage = attributes.get(0).containsKey("usage") ? attributes.get(0).getString("usage") : "";
							definition = attributes.get(0).containsKey("definition") ? attributes.get(0).getString("definition") : "";
							//fieldFormat = attributes.get(0).containsKey("fieldFormat") ? attributes.get(0).getString("fieldFormat") : "";							
							//minimumLength = attributes.get(0).containsKey("minimumLength") ? attributes.get(0).getInteger("minimumLength") : 0;
							//maximumLength = attributes.get(0).containsKey("maximumLength") ? attributes.get(0).getInteger("maximumLength") : 0;
							
							lengthList = attributes.get(0).containsKey("length") ? (List<Document>) attributes.get(0).get("length") : null;
							fieldFormatList = attributes.get(0).containsKey("fieldFormats") ? (List<Document>) attributes.get(0).get("fieldFormats") : null;
							transactionList = attributes.get(0).containsKey("transactions") ?(List<Document>) attributes.get(0).get("transactions") : null;
							standardFormatsList = attributes.get(0).containsKey("standardFormats") ? (List<Document>) attributes.get(0).get("standardFormats") : null;
							
							if(lengthList != null){
								length = lengthList.stream().toArray(String[]::new);
							}
							if(transactionList != null)
								transactions = transactionList.stream().toArray(String[]::new);
							if(standardFormatsList != null)
								standardFormats = standardFormatsList.stream().toArray(String[]::new);
							if(fieldFormatList != null) {
								fieldFormats = fieldFormatList.stream().toArray(String[]::new);
							}
						}
					}
					
					element = new NCPDPElement(name, segmentId, segmentName, elementName, version, dataType, 
							transactions, usage, fieldFormats, codeSet, length,	standardFormats, definition);
			
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
