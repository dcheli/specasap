package net.services.api.specasap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class NCPDPElement extends Element{
	
	String name;        		    
	String segmentId;
	String segmentName;
	String elementName;
	String[] versions;
	String dataType;
	String[] transactions;
	String[] standardFormats;
	
	String usage;
	String fieldFormat;
	String[] fieldFormats;
	//@XmlElement(nillable = true) String codeSet;
	String[] codes;
	String definition;
	
	String[] lengths;
	Integer minimumLength;
	Integer maximumLength;	
    
	
	public NCPDPElement() {
		
	}

	public NCPDPElement(String name, String segmentId, String segmentName, String elementName, String[] versions, String dataType,
			String[] transactions, String usage, String[] fieldFormats, String[] codes, String[] length, String[] standardFormats,String definition) {
		super();
		this.name = name;
		this.segmentId = segmentId;
		this.segmentName = segmentName;
		this.elementName = elementName;
		this.versions = versions;
		this.dataType = dataType;
		this.transactions = transactions;
		this.usage = usage;
		this.fieldFormats = fieldFormats;
		this.codes = codes;
		this.lengths = length;
		this.standardFormats = standardFormats;
		this.definition = definition;
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

    //@XmlElementWrapper(name = "transactions")
    //@XmlElement(name = "transaction")
	public String[] getTransactions() {
		return transactions;
	}


	public void setTransactions(String[] transactions) {
		this.transactions = transactions;
	}


	public String getUsage() {
		return usage;
	}


	public void setUsage(String usage) {
		this.usage = usage;
	}


	public String getFieldFormat() {
		return fieldFormat;
	}


	public void setFieldFormat(String fieldFormat) {
		this.fieldFormat = fieldFormat;
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

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
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

}
