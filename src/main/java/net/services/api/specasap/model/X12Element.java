package net.services.api.specasap.model;

public class X12Element extends Element{

	String name;    
	String segmentId;
	String segmentName;
	String elementName;
	String[] versions;
	String dataType;
	String[] transactions;
	
	String requirementDesignator;
	Integer dataElementNumber;
	String implementationName;  
	Integer elementRepeat;
	String[] codes;
	String usage;
	String loop;

	String length;
	Integer minimumLength;
	Integer maximumLength;	
	
	public X12Element() {
		
	}

	public X12Element(String name, String segmentId, String segmentName, String elementName, String[] versions,
			String dataType, String[] transactions,   String requirementDesignator, Integer dataElementNumber,
			String implementationName, Integer elementRepeat, String[] codes, 
			String loop, String length, Integer minimumLength, Integer maximumLength) {
		super();
		this.name = name;
		this.segmentId = segmentId;
		this.segmentName = segmentName;
		this.elementName = elementName;
		this.versions = versions;
		this.dataType = dataType;
		this.transactions = transactions;
		this.requirementDesignator = requirementDesignator;
		this.dataElementNumber = dataElementNumber;
		this.implementationName = implementationName;
		this.elementRepeat = elementRepeat;
		this.codes = codes;
		this.loop = loop;
		this.length = length;
		this.minimumLength = minimumLength;
		this.maximumLength = maximumLength;
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

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String[] getTransactions() {
		return transactions;
	}

	public void setTransactions(String[] transactions) {
		this.transactions = transactions;
	}

	public String getRequirementDesignator() {
		return requirementDesignator;
	}

	public void setRequirementDesignator(String requirementDesignator) {
		this.requirementDesignator = requirementDesignator;
	}

	public Integer getDataElementNumber() {
		return dataElementNumber;
	}

	public void setDataElementNumber(Integer dataElementNumber) {
		this.dataElementNumber = dataElementNumber;
	}

	public String getImplementationName() {
		return implementationName;
	}

	public void setImplementationName(String implementationName) {
		this.implementationName = implementationName;
	}

	public Integer getElementRepeat() {
		return elementRepeat;
	}

	public void setElementRepeat(Integer elementRepeat) {
		this.elementRepeat = elementRepeat;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}


	public String getLoop() {
		return loop;
	}

	public void setLoop(String loop) {
		this.loop = loop;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Integer getMinimumLength() {
		return minimumLength;
	}

	public void setMinimumLength(Integer minimumLength) {
		this.minimumLength = minimumLength;
	}

	public Integer getMaximumLength() {
		return maximumLength;
	}

	public void setMaximumLength(Integer maximumLength) {
		this.maximumLength = maximumLength;
	}

	public String[] getVersions() {
		return versions;
	}

	public void setVersions(String[] versions) {
		this.versions = versions;
	}

	public String[] getCodes() {
		return codes;
	}

	public void setCodes(String[] codes) {
		this.codes = codes;
	}
	
	
}


