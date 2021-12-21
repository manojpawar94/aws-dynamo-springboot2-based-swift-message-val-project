package com.manoj.aws.pvd.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ApiClient {
	private String apiKey;
	private String clientId;
	private String name;
	private List<String> authorities;

	/**
	 * @return the apiKey
	 */
	public String getApiKey() {
		return apiKey;
	}

	/**
	 * @param apiKey the apiKey to set
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the authorities
	 */
	public List<String> getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(String... authorities) {
		this.authorities = Arrays.asList(authorities);
	}

	/**
	 * @param authorities the authorities to add
	 */
	public void addAuthority(String authority) {
		if (Objects.isNull(this.authorities)) {
			this.authorities = new ArrayList<>();
		}
		this.authorities.add(authority);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ApiClient [apiKey=").append(apiKey).append(", name=").append(name).append(", authorities=")
				.append(authorities).append("]");
		return builder.toString();
	}

}
