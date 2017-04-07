package net.services.api.specasap.model;

import java.util.ArrayList;

public class ProductSet {
	String domain;
	ArrayList<String> operatingSystems = new ArrayList<String>();
	ArrayList<Product> products = new ArrayList<Product>();
	public ProductSet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDomain() {
		return domain;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	public ArrayList<String> getOperatingSystems() {
		return operatingSystems;
	}
	public void setOperatingSystems(ArrayList<String> operatingSystems) {
		this.operatingSystems = operatingSystems;
	}

}
