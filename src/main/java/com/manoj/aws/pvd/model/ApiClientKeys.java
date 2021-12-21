package com.manoj.aws.pvd.model;

/**
 * ApiClinet Table Key Enumeration
 * 
 */
public enum ApiClientKeys {
	TABLE("ApiClinet"), API_KEY("ApiKey"), CLIENT_ID("ClientId"), CLIENT_NAME("ClientName"),
	AUTHAURITIES("Authorities");

	private String keyName;

	ApiClientKeys(String keyName) {
		this.keyName = keyName;
	}

	public String keyName() {
		return keyName;
	}
}
