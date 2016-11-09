package com.ace.training.usecase.sms.payment;

public class PaymentStatus {
	private String transactionId;
	private int amount;
	private String status;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isSuccess() {
		return "SUCCESS".equals(status);
	}
}
