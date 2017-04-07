package net.services.api.specasap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	String productId;
	String version;
	String displayName;
	
	public Product() {
		super();
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	

}
