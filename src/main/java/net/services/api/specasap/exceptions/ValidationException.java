package net.services.api.specasap.exceptions;

public class ValidationException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2891273357332281203L;

	public ValidationException(String message) {
		super(message);
	}
}
