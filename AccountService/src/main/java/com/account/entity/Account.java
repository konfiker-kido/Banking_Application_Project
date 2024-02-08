package com.account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {  
   
	    @Id 
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;  

	     private String name;
	     private Long mobile;
	     private String accountNumber;

	    private double balance;
	    private String status="active"; 
	    

		public Account() {
			super();
			// TODO Auto-generated constructor stub
		} 

		
		


		public Account(Long id, String name, Long mobile, String accountNumber, double balance, String status) {
			super();
			this.id = id;
			this.name = name;
			this.mobile = mobile;
			this.accountNumber = accountNumber;
			this.balance = balance;
			this.status = status;
		}





		public String getStatus() {
			return status;
		}


		public void setStatus(String status) {
			this.status = status;
		}


		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getMobile() {
			return mobile;
		}

		public void setMobile(Long mobile) {
			this.mobile = mobile;
		}

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}

		public double getBalance() {   
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}
	    
	    

}
