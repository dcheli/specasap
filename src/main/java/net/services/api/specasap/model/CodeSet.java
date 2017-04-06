package net.services.api.specasap.model;

import java.util.ArrayList;

public class CodeSet {
	ArrayList<String> elementIds = new ArrayList<String>();
	ArrayList<Code> codes = new ArrayList<Code>();
	public CodeSet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Code> getCodes() {
		return codes;
	}
	public void setCodes(ArrayList<Code> codes) {
		this.codes = codes;
	}
	public ArrayList<String> getElementIds() {
		return elementIds;
	}
	public void setElementIds(ArrayList<String> elementIds) {
		this.elementIds = elementIds;
	}
	

}
