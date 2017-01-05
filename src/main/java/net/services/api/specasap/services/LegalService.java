package net.services.api.specasap.services;

import java.io.IOException;
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
import net.services.api.specasap.model.TermsOfService;

public class LegalService {

	PrivacyPolicy privacyPolicy = null;
	TermsOfService termsOfService = null;
	MongoDatabase db = null;
	Logger logger = Logger.getLogger(LegalService.class);
	MongoClient mongoClient = null;
	
	public LegalService(ServletContext servletContext) throws IOException {

		try{
			mongoClient = (MongoClient) servletContext.getAttribute("MONGODB_CLIENT");
			db = mongoClient.getDatabase(servletContext.getInitParameter("MONGODB_DATABASE"));
			System.out.println("User credentials are: " + mongoClient.getCredentialsList());
		} catch(Exception e) {
			logger.error("LegalService: " + e);
			logger.error("Mongo error " + e.getMessage());
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	public PrivacyPolicy getPrivacyPolicy() {
		String collection = "legal";
		System.out.println("database is " + db.getCollection(collection).toString());
		
		FindIterable<Document> iterable = db.getCollection(collection).find(
				new Document("documentType","privacyPolicy"));
		
		iterable.forEach(new Block<Document>() {
			@SuppressWarnings("unchecked")
			@Override
			public void apply(final Document document) {
				String generalInformation = "";
				String informationGatheringAndUsage = "";
				String cookies = "";
				String dataStorage = "";
				String disclosure = "";
				String euAndSwissSafeHarbor = "";
				String changes = "";
				String questions = "";
				String lastReviewedOrUpdated = "";
				List<Document> components = document.containsKey("components") ? (List<Document>)document.get("components") : null;
				if(components != null){
					if(!components.isEmpty()){
						generalInformation = components.get(0).containsKey("generalInformation") ? components.get(0).getString("generalInformation") : "";
						informationGatheringAndUsage = components.get(0).containsKey("informationGatheringAndUsage") ? components.get(0).getString("informationGatheringAndUsage") : "";
						cookies = components.get(0).containsKey("cookies") ? components.get(0).getString("cookies") : "";
						dataStorage = components.get(0).containsKey("dataStorage") ? components.get(0).getString("dataStorage") : "";
						disclosure = components.get(0).containsKey("disclosure") ? components.get(0).getString("disclosure") : "";
						euAndSwissSafeHarbor = components.get(0).containsKey("euAndSwissSafeHarbor") ? components.get(0).getString("euAndSwissSafeHarbor") : "";
						changes = components.get(0).containsKey("changes") ? components.get(0).getString("changes") : "";
						questions = components.get(0).containsKey("questions") ? components.get(0).getString("questions") : "";
						lastReviewedOrUpdated = components.get(0).containsKey("lastReviewedOrUpdated") ? components.get(0).getString("lastReviewedOrUpdated") : "";
					}
				}
				privacyPolicy = new PrivacyPolicy(generalInformation, informationGatheringAndUsage, cookies,
						dataStorage, disclosure, euAndSwissSafeHarbor, changes, questions, lastReviewedOrUpdated);
				
			}
		});

		if(privacyPolicy == null) {
			System.out.println("No Privacy Policy");
			throw new DataNotFoundException("The requested Privacy Policy was not found");
		} else {
			return privacyPolicy;
		}	
	}

	public TermsOfService getTermsOfService() {

		String collection = "legal";
		System.out.println("database is " + db.getCollection(collection).toString());
		
		FindIterable<Document> iterable = db.getCollection(collection).find(
				new Document("documentType","termsOfService"));
		
		iterable.forEach(new Block<Document>() {
			@SuppressWarnings("unchecked")
			@Override
			public void apply(final Document document) {
				
				String introduction = ""; 
				String accountTerms = "";
				String paymentRefundsUpgradingAndDowngrading = ""; 
				String cancellationAndTermination = "";
				String modificationsToTheServiceAndPrices = "";
				String copyrightAndContentOwnership = "";
				String generalConditions = "";
				String lastReviewedOrUpdated = "";
				
				List<Document> components = document.containsKey("components") ? (List<Document>)document.get("components") : null;
				if(components != null){
					if(!components.isEmpty()){
						introduction = components.get(0).containsKey("introduction") ? components.get(0).getString("introduction") : "";
						accountTerms = components.get(0).containsKey("accountTerms") ? components.get(0).getString("accountTerms") : "";
						paymentRefundsUpgradingAndDowngrading = components.get(0).containsKey("paymentRefundsUpgradingAndDowngrading") ? components.get(0).getString("paymentRefundsUpgradingAndDowngrading") : "";
						cancellationAndTermination = components.get(0).containsKey("cancellationAndTermination") ? components.get(0).getString("cancellationAndTermination") : "";
						modificationsToTheServiceAndPrices = components.get(0).containsKey("modificationsToTheServiceAndPrices") ? components.get(0).getString("modificationsToTheServiceAndPrices") : "";
						copyrightAndContentOwnership = components.get(0).containsKey("copyrightAndContentOwnership") ? components.get(0).getString("copyrightAndContentOwnership") : "";
						generalConditions = components.get(0).containsKey("generalConditions") ? components.get(0).getString("generalConditions") : "";
						lastReviewedOrUpdated = components.get(0).containsKey("lastReviewedOrUpdated") ? components.get(0).getString("lastReviewedOrUpdated") : "";
					}
				}
				termsOfService = new TermsOfService(introduction, accountTerms, paymentRefundsUpgradingAndDowngrading,
						cancellationAndTermination, modificationsToTheServiceAndPrices, copyrightAndContentOwnership, 
						generalConditions,lastReviewedOrUpdated);
				
			}
		});

		if(termsOfService == null) {
			System.out.println("No Terms of Service");
			throw new DataNotFoundException("The requested Terms of Service was not found");
		} else {
			return termsOfService;
		}	
	}

	
}
