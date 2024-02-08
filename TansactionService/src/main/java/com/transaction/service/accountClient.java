package com.transaction.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.transaction.entity.Account;

@FeignClient(url="http://localhost:8002",value="account-client") 
public interface accountClient {
  
	@GetMapping("/account/getAccount/{acNumber}")  
	Account getAccountDetails(@PathVariable String acNumber); 
	
	@GetMapping("/account/updateBalance/{acNumber}/{newBalance}") 
    void updateAccountBalance(@PathVariable("acNumber") String acNumber, @PathVariable("newBalance") double newBalance);

		
}
