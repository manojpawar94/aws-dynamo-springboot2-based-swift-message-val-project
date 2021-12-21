package com.manoj.aws.pvd.exceptions;

public class SwiftValidationTransactionException extends Exception {

	private static final long serialVersionUID = 106446624439907450L;

	/**
	 * 
	 */
	public SwiftValidationTransactionException() {
		super();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public SwiftValidationTransactionException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SwiftValidationTransactionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public SwiftValidationTransactionException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public SwiftValidationTransactionException(Throwable arg0) {
		super(arg0);
	}

}
