package net.services.api.specasap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	String productId;
	String operatingSystem;
	String version;
	public Product() {
		super();
	}
	public Product(String productId, String operatingSystem, String version) {
		super();
		this.productId = productId;
		this.operatingSystem = operatingSystem;
		this.version = version;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	

}
