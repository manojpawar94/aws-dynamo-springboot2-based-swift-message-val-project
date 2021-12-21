package com.manoj.aws.pvd.model;

public class SwiftValidationResponse {
	private String transactionId;
	private boolean validationStatus;
	private String validationMessage;

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the validationStatus
	 */
	public boolean isValidationStatus() {
		return validationStatus;
	}

	/**
	 * @param validationStatus the validationStatus to set
	 */
	public void setValidationStatus(boolean validationStatus) {
		this.validationStatus = validationStatus;
	}

	/**
	 * @return the validationMessage
	 */
	public String getValidationMessage() {
		return validationMessage;
	}

	/**
	 * @param validationMessage the validationMessage to set
	 */
	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

}
