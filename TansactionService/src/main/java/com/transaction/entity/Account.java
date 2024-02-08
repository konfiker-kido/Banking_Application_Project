package com.transaction.entity; 

  
public class Account {  
    
	     private String name;
	     private Long mobile;
	     private String accountNumber;

	    private double balance;
	    private String status="active";
	    

		public Account() {  
			super();
			// TODO Auto-generated constructor stub
		} 

		
		
  
		public Account(String name, Long mobile, String accountNumber, double balance, String status) {
			super();
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
