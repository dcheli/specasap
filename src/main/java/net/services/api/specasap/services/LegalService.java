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
			e.printStackTrace();
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
				List<Document> attributes = document.containsKey("components") ? (List<Document>)document.get("components") : null;
				if(attributes != null){
					if(!attributes.isEmpty()){
						generalInformation = attributes.get(0).containsKey("generalInformation") ? attributes.get(0).getString("generalInformation") : "";
						informationGatheringAndUsage = attributes.get(0).containsKey("informationGatheringAndUsage") ? attributes.get(0).getString("informationGatheringAndUsage") : "";
						cookies = attributes.get(0).containsKey("cookies") ? attributes.get(0).getString("cookies") : "";
						dataStorage = attributes.get(0).containsKey("dataStorage") ? attributes.get(0).getString("dataStorage") : "";
						disclosure = attributes.get(0).containsKey("disclosure") ? attributes.get(0).getString("disclosure") : "";
						euAndSwissSafeHarbor = attributes.get(0).containsKey("euAndSwissSafeHarbor") ? attributes.get(0).getString("euAndSwissSafeHarbor") : "";
						changes = attributes.get(0).containsKey("changes") ? attributes.get(0).getString("changes") : "";
						questions = attributes.get(0).containsKey("questions") ? attributes.get(0).getString("questions") : "";
						lastReviewedOrUpdated = attributes.get(0).containsKey("lastReviewedOrUpdated") ? attributes.get(0).getString("lastReviewedOrUpdated") : "";
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
				String paymentRefundsUpgradingandDowngrading = ""; 
				String cancellationAndTermination = "";
				String modificationsToThServiceAndPrices = "";
				String copyrightAndCOntentOwnership = "";
				String generalConditions = "";
				String lastReviewedOrUpdated = "";
				
				List<Document> attributes = document.containsKey("components") ? (List<Document>)document.get("components") : null;
				if(attributes != null){
					if(!attributes.isEmpty()){
						introduction = attributes.get(0).containsKey("introduction") ? attributes.get(0).getString("introduction") : "";
						accountTerms = attributes.get(0).containsKey("accountTerms") ? attributes.get(0).getString("accountTerms") : "";
						paymentRefundsUpgradingandDowngrading = attributes.get(0).containsKey("paymentRefundsUpgradingandDowngrading") ? attributes.get(0).getString("paymentRefundsUpgradingandDowngrading") : "";
						cancellationAndTermination = attributes.get(0).containsKey("cancellationAndTermination") ? attributes.get(0).getString("cancellationAndTermination") : "";
						modificationsToThServiceAndPrices = attributes.get(0).containsKey("modificationsToThServiceAndPrices") ? attributes.get(0).getString("modificationsToThServiceAndPrices") : "";
						copyrightAndCOntentOwnership = attributes.get(0).containsKey("copyrightAndCOntentOwnership") ? attributes.get(0).getString("copyrightAndCOntentOwnership") : "";
						generalConditions = attributes.get(0).containsKey("generalConditions") ? attributes.get(0).getString("generalConditions") : "";
						lastReviewedOrUpdated = attributes.get(0).containsKey("lastReviewedOrUpdated") ? attributes.get(0).getString("lastReviewedOrUpdated") : "";
					}
				}
				termsOfService = new TermsOfService(introduction, accountTerms, paymentRefundsUpgradingandDowngrading,
						cancellationAndTermination, modificationsToThServiceAndPrices, copyrightAndCOntentOwnership, 
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
