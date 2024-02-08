package com.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.account.entity.Account; 
 
public interface accountRepo extends JpaRepository<Account, Long> {  
  
	 
	 Account getAccountByAccountNumber(String acNumber); 
	 String getStatusByAccountNumber(String acNumber);
}
