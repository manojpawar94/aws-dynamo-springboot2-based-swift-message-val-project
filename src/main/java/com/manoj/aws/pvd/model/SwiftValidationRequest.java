package com.manoj.aws.pvd.model;

/**
 * 
 *
 */
public class SwiftValidationRequest {
	private String clientId;
	private SwiftMessageType swiftMessageType;
	private String swiftMessageText;

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
	public SwiftMessageType getSwiftMessageType() {
		return swiftMessageType;
	}

	/**
	 * @param swiftMessageType the swiftMessageType to set
	 */
	public void setSwiftMessageType(SwiftMessageType swiftMessageType) {
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

}
