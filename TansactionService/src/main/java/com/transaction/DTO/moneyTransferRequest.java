package com.transaction.DTO;

public class moneyTransferRequest {
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private double amount;
    
    
    
	public moneyTransferRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public moneyTransferRequest(String senderAccountNumber, String receiverAccountNumber, double amount) {
		super();
		this.senderAccountNumber = senderAccountNumber;
		this.receiverAccountNumber = receiverAccountNumber;
		this.amount = amount;
	}
	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}
	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}
	public String getReceiverAccountNumber() {
		return receiverAccountNumber;
	}
	public void setReceiverAccountNumber(String receiverAccountNumber) {
		this.receiverAccountNumber = receiverAccountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

    // Getters and setters
    
    
}

