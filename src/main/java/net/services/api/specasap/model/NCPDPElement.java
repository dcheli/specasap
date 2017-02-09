package net.services.api.specasap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class NCPDPElement extends Element{
	  
	String elementId;
	String[] segmentIds;
	String[] segmentNames;
	String elementName;
	String[] versions;
	String dataType;
	String[] requestTransactions;
	String[] responseTransactions;
	String[] standardFormats;
	
	String usage;
	String[] fieldFormats;
	//@XmlElement(nillable = true) String codeSet;
	String[] codes;
	String definition;
	
	String[] lengths;
	Integer minimumLength;
	Integer maximumLength;	
    
	
	public NCPDPElement() {
		// This is needed for JAXB
	}

	public NCPDPElement(String elementId, String[] segmentIds, String[] segmentNames, String elementName, String[] versions, String dataType,
			String[] transactions, String usage, String[] fieldFormats, String[] codes, String[] length, String[] standardFormats,String definition,
			String[] requestTransactions, String[] responseTransactions) {
		super();
		this.elementId = elementId;
		this.elementName = elementName;
		this.versions = versions;
		this.dataType = dataType;
		this.requestTransactions = requestTransactions;
		this.responseTransactions = responseTransactions;
		this.usage = usage;
		this.fieldFormats = fieldFormats;
		this.codes = codes;
		this.lengths = length;
		this.standardFormats = standardFormats;
		this.definition = definition;
	}


	public String getElementName() {
		return elementName;
	}


	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String[] getLengths() {
		return lengths;
	}

	public void setLengths(String[] lengths) {
		this.lengths = lengths;
	}

	public String getDataType() {
		return dataType;
	}


	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getUsage() {
		return usage;
	}


	public void setUsage(String usage) {
		this.usage = usage;
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

	
	public String[] getStandardFormats() {
		return standardFormats;
	}

	public void setStandardFormats(String[] standardFormats) {
		this.standardFormats = standardFormats;
	}

	public String[] getFieldFormats() {
		return fieldFormats;
	}

	public void setFieldFormats(String[] fieldFormats) {
		this.fieldFormats = fieldFormats;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
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

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String[] getRequestTransactions() {
		return requestTransactions;
	}

	public void setRequestTransactions(String[] requestTransactions) {
		this.requestTransactions = requestTransactions;
	}

	public String[] getResponseTransactions() {
		return responseTransactions;
	}

	public void setResponseTransactions(String[] responseTransactions) {
		this.responseTransactions = responseTransactions;
	}

	public String[] getSegmentIds() {
		return segmentIds;
	}

	public void setSegmentIds(String[] segmentIds) {
		this.segmentIds = segmentIds;
	}

	public String[] getSegmentNames() {
		return segmentNames;
	}

	public void setSegmentNames(String[] segmentNames) {
		this.segmentNames = segmentNames;
	}

}
