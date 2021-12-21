package com.manoj.aws.pvd.model;

import lombok.Data;

@Data
public class PaymentRequest {
	private String sourceAccount;
	private String targetAccount;
	private Double amount;
	/**
	 * @return the sourceAccount
	 */
	public String getSourceAccount() {
		return sourceAccount;
	}
	/**
	 * @param sourceAccount the sourceAccount to set
	 */
	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	/**
	 * @return the targetAccount
	 */
	public String getTargetAccount() {
		return targetAccount;
	}
	/**
	 * @param targetAccount the targetAccount to set
	 */
	public void setTargetAccount(String targetAccount) {
		this.targetAccount = targetAccount;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
