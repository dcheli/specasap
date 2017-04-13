package net.services.api.specasap.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class CCDPlusElement  extends Element{
	
	String tags;
	CCDPlusAttributes attributes;
	public CCDPlusElement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTags() {
		return tags;
	}
	public CCDPlusAttributes getAttributes() {
		return attributes;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public void setAttributes(CCDPlusAttributes attributes) {
		this.attributes = attributes;
	}
	
	

}
