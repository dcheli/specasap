package net.services.api.specasap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class NCPDPElement extends Element{
	  
	String elementId;
	String[] segmentIds;
	String[] segmentNames;
	String elementName;
	String[] versions;
	String[] requestTransactions;
	String[] responseTransactions;
	String[] standardFormats;
	String comments;
	String[] fieldFormats;
	String[] fbRejectMessages;
	
	//@XmlElement(nillable = true) String codeSet;
	String[] codes;
	String definition;
	
	String[] lengths;
    
	
	public NCPDPElement() {
		// This is needed for JAXB
	}

	public NCPDPElement(String elementId, String[] segmentIds, String[] segmentNames, String elementName, String[] versions, 
			String[] transactions,  String[] fieldFormats, String[] codes, String[] length, String[] standardFormats,String definition,
			String[] requestTransactions, String[] responseTransactions, String comments,String[] fbRejectMessages) {
		super();
		this.elementId = elementId;
		this.elementName = elementName;
		this.versions = versions;
		this.segmentIds = segmentIds;
		this.segmentNames = segmentNames;
		this.requestTransactions = requestTransactions;
		this.responseTransactions = responseTransactions;
		this.fieldFormats = fieldFormats;
		this.codes = codes;
		this.lengths = length;
		this.standardFormats = standardFormats;
		this.definition = definition;
		this.comments = comments;
		this.fbRejectMessages =  fbRejectMessages;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String[] getFbRejectMessages() {
		return fbRejectMessages;
	}

	public void setFbRejectMessages(String[] fbRejectMessages) {
		this.fbRejectMessages = fbRejectMessages;
	}

}
