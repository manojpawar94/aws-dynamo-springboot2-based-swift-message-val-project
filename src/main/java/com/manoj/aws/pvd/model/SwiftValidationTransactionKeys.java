package com.manoj.aws.pvd.model;

public enum SwiftValidationTransactionKeys {
	TABLE("SwiftValidationTransaction"), TRANSACTION_ID("TransactionId"), CLIENT_ID("ClientId"),
	SWIFT_MESSAGE_TYPE("SwiftMessageType"), SWIFT_MESSAGE_TEXT("SwiftMessageText"),
	VALIDATION_STATUS("ValidationStatus"), VALIDATION_MESSAGE("ValidationMessage"), VALIDATION_AT("ValiadtionAt");

	private String keyName;

	SwiftValidationTransactionKeys(String keyName) {
		this.keyName = keyName;
	}

	public String keyName() {
		return keyName;
	}
}
