package com.account.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.entity.Account;
import com.account.repo.accountRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class accountServiceImp implements accountService {

	@Autowired
	accountRepo acRepo;  
	  
//------------------------ Generating 11 Digit Random Number  --------------------------------------------------------------------------
	
	private String generateRandomNumber(long min, long max) {
        Random random = new Random();
        // Generate a random long within the specified range
        Long bank= min + ((long) (random.nextDouble() * (max - min)));
        return Long.toString(bank); 
    }

// -------------------- Creating a New Account for New User ----------------------------------------------------------------------------
	@Override
	public Account createAccount(Account ac) { 
		
		String acNumber = generateRandomNumber(10000000000L, 99999999999L);
		 
		Account checkAccount=acRepo.getAccountByAccountNumber(acNumber);
		
		// if Generated Account Number is Exist in Our DataBase so we have to generate it again  
		 while(checkAccount!=null) {
			acNumber = generateRandomNumber(10000000000L, 99999999999L);
			checkAccount=acRepo.getAccountByAccountNumber(acNumber); 
		}
		 
		ac.setAccountNumber(acNumber); 
		
		return acRepo.save(ac); 
	}

//------------------ Getting Bank Balance By their Existing Account Number --------------------------------------------------------------------
	@Override
	public double getBankBalance(String acNumber) {
	    
		Account ac=acRepo.getAccountByAccountNumber(acNumber);
	    
	    if(ac.getStatus().equals("revoke"))
	    	 throw new EntityNotFoundException("Account Not Found");	    
	    return ac.getBalance();  
		  
	}

//---------------------------------- Getting A Particular Account by their Account Number -------------------------------------------- 
	
	public Account getAccountByAcNumber(String acNumber) { 
		return acRepo.getAccountByAccountNumber(acNumber);
	}

	// updating Bank Balance 
	public void updateBalance(String acNumber,double balance) {
		 Account ac=acRepo.getAccountByAccountNumber(acNumber);
		 ac.setBalance(balance);
		 acRepo.save(ac);  
	 }
	
//----------------------- Changing the Account Status --------------------------------------------------------------------------------
	
	public void changeAccountStatus(String acNumber){
		Account ac=acRepo.getAccountByAccountNumber(acNumber);
		String status=ac.getStatus();
		 if(status.equals("active"))
		 {
			 ac.setStatus("revoke");
			
		 }else {
			 ac.setStatus("active"); 
		 } 
		 acRepo.save(ac);  
		
	}

	
	
//---------------- End Of Account Services --------------------------------------------------------------------------------------------	
}
