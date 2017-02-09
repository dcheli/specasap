package net.services.api.specasap.services;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.json.JsonArray;
import javax.servlet.ServletContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import net.services.api.specasap.model.EnabledProduct;
import net.services.api.specasap.model.IAPReceipt;
import net.services.api.specasap.model.LatestReceiptInfo;



public class IAPReceiptService {
	
	IAPReceipt iapReceipt = null;
	Logger logger = Logger.getLogger(IAPReceiptService.class);
	ArrayList<String> productList = new ArrayList<String>();
	List<EnabledProduct> enabledProducts = new ArrayList<EnabledProduct>();
	JsonArray eProducts;
	


	public IAPReceiptService(ServletContext servletContext) throws IOException {
		//In sandbox https://sandbox.itunes.apple.com/verifyReceipt as the URL. 
		//In production, use https://buy.itunes.apple.com/verifyReceipt as the URL.
		// get productlist
		productList.add("com.dataasap.hl7asap");
		productList.add("com.dataasap.ncpdpasap");
		productList.add("com.dataasap.x12asap");
	}
	
	public List<EnabledProduct> verifyAppleReceipt(final String receipt) throws UnsupportedEncodingException, IOException, ParseException {
		// Make call to iTunes
		String iTunesURL = "https://sandbox.itunes.apple.com/verifyReceipt";
		String sharedSecret = "cc22d11bdd4348a4aa9d89c5880e0073";
	    //final String productionUriStr = "https://buy.itunes.apple.com/verifyReceipt";

		String jsonData = "{\"receipt-data\" : \"" + receipt + "\", \"password\": \"" + sharedSecret + "\"}";

	    logger.error("trying to verifyAppleReceipt");
		final Client client = ClientBuilder.newClient();
		final WebTarget target = client.target(iTunesURL);		
		System.out.println("Calling itunes");
		Response response = target.request().post(Entity.json(jsonData));
		
		// the issue is that iTunes connect is returning a text/plain mediatype 
		// parse the response.readEntity(String.class)
		// find the most current transaction record of each list productId; find record with max expires_date
		// determine if it's active and set the isActive accordingly
		IAPReceipt iapReceipt = null;
		if(response.getStatus() == 200) {
			System.out.println("Metadata " + response.getMetadata());
			String responseString = response.readEntity(String.class);
			ObjectMapper mapper = new ObjectMapper();
			iapReceipt = mapper.readValue(responseString, IAPReceipt.class);
			
			List<LatestReceiptInfo> receiptList = iapReceipt.getLatest_receipt_info();
			for(String product : productList) {
				ArrayList<String> pList = new ArrayList<String>();
				for(LatestReceiptInfo latestReceiptInfo : receiptList) {
					if(latestReceiptInfo.getProduct_id().equals(product)) {
						pList.add(latestReceiptInfo.getExpires_date());
					}
				}
				if(pList.size() > 0){
					Comparator<String> comparator = Collections.reverseOrder();
					Collections.sort(pList,comparator);
					DateFormat df;
					df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss 'Etc/GMT'");
					Date expirationDate = df.parse(pList.get(0));
					Date currentDate = new Date();
					if (expirationDate.after(currentDate) || expirationDate.equals(currentDate)){
						logger.error("Expiration Date is after current Date so subsription is valid");
						logger.error("Expiration Date is " + expirationDate + " and current Date is " + df.format(currentDate));
						enabledProducts.add(new EnabledProduct(product, "true"));
					} else {
						logger.error("Expiration Date is " + expirationDate + " and current Date is " + df.format(currentDate));
						logger.error("Expiration Date is before current Date so subscription is expired");
						enabledProducts.add(new EnabledProduct(product, "false"));
					}
				}
			}
			logger.error("Enabled Products are: " + enabledProducts);
			System.out.println("Enabled Products are: " + enabledProducts);
			
		} else {
			System.out.println("What???");
		}

	
		
		//IAPReceipt iapReceipt = response.readEntity(IAPReceipt.class);
		 logger.error("Made call to itunes... now what " + response.getStatus());
		 logger.error("Response is " + response);
		 // this will be a problem if getStatus != 200
		 return enabledProducts;
	}
}

