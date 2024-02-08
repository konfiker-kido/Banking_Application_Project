package com.transaction.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.DTO.moneyTransferRequest;
import com.transaction.entity.Account;
import com.transaction.entity.transaction;
import com.transaction.repo.transactionRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class transactionServiceImp implements transactionService {

	@Autowired
	 transactionRepo trRepo; 
	
	@Autowired
	accountClient acClient;
	
  
// ---------------- To get the current time with Date -------------------------------------------------------------------------------------
	
	public String getCurrentTime() {
		// Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        // Define the date and time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        // Format the current date and time
        String formattedDateTime = currentDateTime.format(formatter);
        return formattedDateTime; 
	}
	
// ---------------------  this function is for Account which is exist in the database to update the transaction history ---------------
	  @Override
	  public  transaction updateTransaction(transaction tr) {
			  
			  // Creating new instance for transaction
			  transaction newTransaction=new transaction();
			  
			  // account detail for User
			  Account userAccount=acClient.getAccountDetails(tr.getAccountNumber());
			  double currentMoney=userAccount.getBalance();
			  		  
			  // setting common details to transcation 
			  newTransaction.setAccountNumber(tr.getAccountNumber());
			  newTransaction.setDate(getCurrentTime());
			  
			  
			  double money=tr.getCredit();
			  // it means we are trying to update it for debit money bcz credit money is zero
			  if(money==0) {
				 		// this is for debit money  
				  double debitMoney=tr.getDebit();
				  newTransaction.setDebit(debitMoney);
				  newTransaction.setCredit(0);	 
				  newTransaction.setTotal(currentMoney-debitMoney);
				  
				// Updating to user's Account
				  acClient.updateAccountBalance(userAccount.getAccountNumber(), (currentMoney-debitMoney));  
			  }else {
				// this is for credit money  
				  double creditMoney=tr.getCredit();
				  newTransaction.setDebit(0);
				  newTransaction.setCredit(creditMoney); 
				  newTransaction.setTotal(currentMoney+creditMoney);
				  
				// Updating to user's Account
				  acClient.updateAccountBalance(userAccount.getAccountNumber(), (currentMoney+creditMoney));  
			  }
			  	  
			 
			  return trRepo.save(newTransaction);   
		  }
	
// ---------------------- For Credit MOney -------------------------------------------------------------------------------------------
	
@Override
	public transaction creditMoney(transaction tr) {
		
		Account ac=acClient.getAccountDetails(tr.getAccountNumber());   
		
		if (ac == null || ac.getStatus().equals("revoke")) {  
            // Handle the scenario where no entity is found
            // For example, you can throw a custom exception or return a default transaction
            throw new EntityNotFoundException("No entity found for the provided account number");
        }  
		
		return updateTransaction(tr);    
	}
	
	
//------------------------- Implementation for Debit Money   -----------------------------------------
	
	@Override  
	public transaction debitMoney(transaction tr) {
		
	    Account ac = acClient.getAccountDetails(tr.getAccountNumber());

	    // checking for if there is no account or account is revoked
	    if (ac == null || ac.getStatus().equals("revoke")) {  
	        throw new EntityNotFoundException("No account found for the provided account number");
	    }

	    double balance = ac.getBalance();
        
        double debitAmount=tr.getDebit();
	    
        if (debitAmount > balance) {
	        throw new IllegalArgumentException("Insufficient funds in the account");
	    }
  
        return updateTransaction(tr); 
	}

	
		
	
//----------------------------------   fetching all the Trasaction details according to account Number
	 @Override
	  public List<transaction> getAllTransaction(String acNumber){  
		  
		  return trRepo.getAllTransactionByAccountNumber(acNumber);    
	  }
	  
	  

	  
//------------------------ Sending Money to other Bank Account  -------------------------------------------------------------------------
	  
	@Override
	public String sendMoney(moneyTransferRequest dto) {
		
		String senderAccountNum=dto.getSenderAccountNumber();
		String recAccountNum=dto.getReceiverAccountNumber();
		
		double amountToSend=dto.getAmount();
		
		Account senderAccount=acClient.getAccountDetails(senderAccountNum); 
		Account recAccount=acClient.getAccountDetails(recAccountNum);  
		
		double amountOfSender=senderAccount.getBalance();
				
		if (senderAccount == null || senderAccount.getStatus().equals("revoke")) {  
            // Handle the scenario where no entity is found
            // For example, you can throw a custom exception or return a default transaction
            return ("No entity found for the Sender Account Number");
        }
		
		if (recAccount == null || recAccount.getStatus().equals("revoke")) {  
            // Handle the scenario where no entity is found 
            // For example, you can throw a custom exception or return a default transaction
            return ("No entity found for the Reciever's  Account Number");
        }
		
		// Checking if insufficient Money in Sender's Account
		if(amountOfSender<amountToSend) {
			return "Insufficeint Balance in your Account";
		}
		
		
	//  Creating new instance for Sender user to update their account and transaction
		transaction forSender=new transaction();
		
		forSender.setAccountNumber(senderAccountNum);
		forSender.setCredit(0);
		forSender.setDebit(amountToSend);
		
		// updating sender Account and transaction For Sender 
		updateTransaction(forSender);
		
		transaction forReceiver= new transaction();
		forReceiver.setAccountNumber(recAccountNum);
		forReceiver.setCredit(amountToSend);
		forReceiver.setDebit(0); 
		
		updateTransaction(forReceiver); 
		
		return senderAccount.getName()+" Paid "+ amountToSend +" Rs. to "+recAccount.getName();  
		
	} 
	  
	 
	  
	  
}
