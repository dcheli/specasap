package net.services.api.specasap.model;

public class BAIAttributes {
	
	String[] segmentNames;
	String[] segmentIds;
	String elementName;
	String usage;
	String dataType;
	String length;
	String definition;
	String position;
	boolean codes;
	
	public BAIAttributes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String[] getSegmentNames() {
		return segmentNames;
	}
	public String[] getSegmentIds() {
		return segmentIds;
	}
	public String getElementName() {
		return elementName;
	}
	public String getUsage() {
		return usage;
	}
	public String getDataType() {
		return dataType;
	}
	public String getLength() {
		return length;
	}
	public String getDefinition() {
		return definition;
	}
	public boolean isCodes() {
		return codes;
	}
	public void setSegmentNames(String[] segmentNames) {
		this.segmentNames = segmentNames;
	}
	public void setSegmentIds(String[] segmentIds) {
		this.segmentIds = segmentIds;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public void setCodes(boolean codes) {
		this.codes = codes;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	

}
