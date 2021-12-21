package com.manoj.aws.pvd.model;

/**
 * 
 *
 */
public class SwiftValidationTransaction {
	private String transactionId;
	private String clientId;
	private String swiftMessageType;
	private String swiftMessageText;
	private boolean validationStatus;
	private String validationMessage;
	private String valiadtionAt;

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
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the swiftMessageType
	 */
	public String getSwiftMessageType() {
		return swiftMessageType;
	}

	/**
	 * @param swiftMessageType the swiftMessageType to set
	 */
	public void setSwiftMessageType(String swiftMessageType) {
		this.swiftMessageType = swiftMessageType;
	}

	/**
	 * @return the swiftMessageText
	 */
	public String getSwiftMessageText() {
		return swiftMessageText;
	}

	/**
	 * @param swiftMessageText the swiftMessageText to set
	 */
	public void setSwiftMessageText(String swiftMessageText) {
		this.swiftMessageText = swiftMessageText;
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

	/**
	 * @return the valiadtionAt
	 */
	public String getValiadtionAt() {
		return valiadtionAt;
	}

	/**
	 * @param valiadtionAt the valiadtionAt to set
	 */
	public void setValiadtionAt(String valiadtionAt) {
		this.valiadtionAt = valiadtionAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SwiftValidationTransaction [transactionId=").append(transactionId).append(", clientId=")
				.append(clientId).append(", swiftMessageType=").append(swiftMessageType).append(", swiftMessageText=")
				.append(swiftMessageText).append(", validationStatus=").append(validationStatus)
				.append(", validationMessage=").append(validationMessage).append(", valiadtionAt=").append(valiadtionAt)
				.append("]");
		return builder.toString();
	}

}
