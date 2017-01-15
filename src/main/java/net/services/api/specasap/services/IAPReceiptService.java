package net.services.api.specasap.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.servlet.ServletContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;


import net.services.api.specasap.model.IAPReceipt;


public class IAPReceiptService {
	
	IAPReceipt iapReceipt = null;
	Logger logger = Logger.getLogger(IAPReceiptService.class);
	

	public IAPReceiptService(ServletContext servletContext) throws IOException {
		//In sandbox https://sandbox.itunes.apple.com/verifyReceipt as the URL. 
		//In production, use https://buy.itunes.apple.com/verifyReceipt as the URL.
		

	}
	
	public String verifyAppleReceipt(final String receipt) throws UnsupportedEncodingException, IOException {
		// Make call to iTunes
		String iTunesURL = "https://sandbox.itunes.apple.com/verifyReceipt";
		String sharedSecret = "cc22d11bdd4348a4aa9d89c5880e0073";
	    //final String productionUriStr = "https://buy.itunes.apple.com/verifyReceipt";
		//final String receiptData = encoder.encode(receipt.getBytes());

		String jsonData = "{\"receipt-data\" : \"" + receipt + "\", \"password\": \"" + sharedSecret + "\"}";

	    logger.error("trying to verifyAppleReceipt");
		final Client client = ClientBuilder.newClient();
		final WebTarget target = client.target(iTunesURL);
		final Builder request = target.request(MediaType.APPLICATION_FORM_URLENCODED);
		System.out.println("Callin itunes");
		Response response = request.post(Entity.json(jsonData));
		
		//System.out.println("Made call to itunes... now what " + response.readEntity(String.class));
		 logger.error("Made call to itunes... now what " + response.getStatus());
		 logger.error("Response is " + response);
		  return response.readEntity(String.class);
	}
	
	
}
