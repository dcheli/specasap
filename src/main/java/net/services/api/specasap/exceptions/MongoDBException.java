package net.services.api.specasap.exceptions;

public class MongoDBException extends RuntimeException {

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
		System.out.println(message);
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
