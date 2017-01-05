package net.services.api.specasap.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.servlet.ServletContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
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
	
	public IAPReceipt verifyAppleReceipt(final String receipt) {
		// Make call to iTunes
		String iTunesURL = "https:sandbox.itunes.apple.com/verifyReceipt";
		//final Base64Encoder encoder = new Base64Encoder();
	    String receiptData = "";
		try {
			receiptData = Base64.getEncoder().encodeToString(receipt.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error("verifyAppleReceipt: " + e);
		}

	    final String jsonData = "{\"receipt-data\" : \"" + receiptData + "\"}";

	    System.out.println(receipt);
	    System.out.println(jsonData);
	    
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(iTunesURL);
		Builder builder = target.request(MediaType.APPLICATION_JSON);
		
//		Response response = builder.get();
		//System.out.println(response);
		
		return null;
	}

	
	
	
}
