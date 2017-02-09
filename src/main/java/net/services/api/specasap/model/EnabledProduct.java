package net.services.api.specasap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class EnabledProduct {
	String productId;
	Boolean isEnabled;
	String enabled;
	public EnabledProduct() {
		super();
	}
	
	
	public EnabledProduct(String productId, String enabled) {
		super();
		this.productId = productId;
		this.enabled = enabled;
	}


	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}
