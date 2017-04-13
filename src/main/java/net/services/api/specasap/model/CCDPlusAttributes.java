package net.services.api.specasap.model;

public class CCDPlusAttributes {
	
	String segmentName;
	String elementPosition;
	String elementName;
	String usage;
	String dataType;
	String length;
	String position;	
	String definition;
	boolean codes; // true or false
	public CCDPlusAttributes() {
		super();
	}

	public String getElementPosition() {
		return elementPosition;
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

	public String getPosition() {
		return position;
	}
	public String getDefinition() {
		return definition;
	}
	public boolean isCodes() {
		return codes;
	}

	public void setElementPosition(String elementPosition) {
		this.elementPosition = elementPosition;
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

	public void setPosition(String position) {
		this.position = position;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public void setCodes(boolean codes) {
		this.codes = codes;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public String getLength() {
		return length;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public void setLength(String length) {
		this.length = length;
	}

}
