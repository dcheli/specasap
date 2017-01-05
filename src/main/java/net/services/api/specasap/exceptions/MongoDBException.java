package net.services.api.specasap.exceptions;

import org.apache.log4j.Logger;

public class MongoDBException extends RuntimeException {
	
	final transient Logger logger = Logger.getLogger(MongoDBException.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8458955840347294344L;
	
	String message = null;

	public MongoDBException() {
		super();
	}

	public MongoDBException(Exception e) {
		e.printStackTrace();
	}
	
	public  MongoDBException(String message) {
		this.message = message;
		logger.error("MongoDBException error " + message);
	}
	
	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
