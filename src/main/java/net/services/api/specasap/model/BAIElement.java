package net.services.api.specasap.model;

public class BAIElement extends Element{
	
	String tags;
	BAIAttributes attributes;
	
	public BAIElement() {
		super();

	}

	public String getTags() {
		return tags;
	}

	public BAIAttributes getAttributes() {
		return attributes;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setAttributes(BAIAttributes attributes) {
		this.attributes = attributes;
	}

}
