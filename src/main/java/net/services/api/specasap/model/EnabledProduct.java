package net.services.api.specasap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class EnabledProduct {
	String productId;
	Boolean isEnabled;
	public EnabledProduct() {
		super();
	}
	
	
	public EnabledProduct(String productId, Boolean isEnabled) {
		super();
		this.productId = productId;
		this.isEnabled = isEnabled;
	}


	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}
