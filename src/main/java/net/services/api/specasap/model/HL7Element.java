package net.services.api.specasap.model;

public class HL7Element {
	
	String name;	           		    
	String segmentId;
	String segmentName;
	String elementName;
	Integer sequence;
	String length;
	String conformanceLength;
	String dataType;
	String optionality;
	String repetition;
	String tableNumber;	
	String itemNumber;

	String[] versions;
	String[] transactions;
	
	public HL7Element() {
	}

	public HL7Element(String name, String segmentId, String segmentName, String elementName, Integer sequence,
			String length, String conformanceLength, String dataType, String optionality, String repetition,
			String tableNumber, String itemNumber, String[] versions, String[] transactions) {
		super();
		this.name = name;
		this.segmentId = segmentId;
		this.segmentName = segmentName;
		this.elementName = elementName;
		this.sequence = sequence;
		this.length = length;
		this.conformanceLength = conformanceLength;
		this.dataType = dataType;
		this.optionality = optionality;
		this.repetition = repetition;
		this.tableNumber = tableNumber;
		this.itemNumber = itemNumber;
		this.versions = versions;
		this.transactions = transactions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getConformanceLength() {
		return conformanceLength;
	}

	public void setConformanceLength(String conformanceLength) {
		this.conformanceLength = conformanceLength;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getOptionality() {
		return optionality;
	}

	public void setOptionality(String optionality) {
		this.optionality = optionality;
	}

	public String getRepetition() {
		return repetition;
	}

	public void setRepetition(String repetition) {
		this.repetition = repetition;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public String[] getVersions() {
		return versions;
	}

	public void setVersions(String[] versions) {
		this.versions = versions;
	}

	public String[] getTransactions() {
		return transactions;
	}

	public void setTransactions(String[] transactions) {
		this.transactions = transactions;
	}
	

}
