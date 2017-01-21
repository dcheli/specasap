package net.services.api.specasap.model;

public class Subscription {

	private String productId;
	private boolean isActive;
		
	public  Subscription() {
		super();
	}
	public  Subscription(String productId, boolean isActive) {
		super();
		this.productId = productId;
		this.isActive = isActive;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public boolean isActive() {
		return isActive;
	}		
}
