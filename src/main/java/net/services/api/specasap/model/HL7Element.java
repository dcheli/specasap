package net.services.api.specasap.model;

public class HL7Element {
	
	String name;	           		    
	String segmentId;
	String elementName;
	String version;
	String dataType;
	String[] transactions;

	String optionality;
	Integer sequence;
	String repitition;
	String table;
	
	String normativeLength;
	Integer normativeMinimumLength;
	Integer normativeMaximumLength;
	String conformanceLength;

 
	  

		
	public HL7Element() {
	}



}
