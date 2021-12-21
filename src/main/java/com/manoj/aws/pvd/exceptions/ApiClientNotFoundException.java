package com.manoj.aws.pvd.exceptions;

/**
 * Exception to throw when ApiClient not found
 * 
 */
public class ApiClientNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7159511241587988322L;

	/**
	 * 
	 */
	public ApiClientNotFoundException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ApiClientNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApiClientNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ApiClientNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ApiClientNotFoundException(Throwable cause) {
		super(cause);
	}

}
