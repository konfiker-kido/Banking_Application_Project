package com.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.account.entity.Account;
import com.account.service.accountServiceImp;

@RestController
@RequestMapping("/account")
public class accountController {
  
	@Autowired
	 accountServiceImp service;
	
	@PostMapping("/create")
	public Account createAccount(@RequestBody Account ac) {
	
		return service.createAccount(ac); 
	}  
	
	@GetMapping("/checkBalance/{acNumber}")  
	public double getBankBalance(@PathVariable String acNumber) {  
		return service.getBankBalance(acNumber);   
		
	}
	
	@GetMapping("/getAccount/{acNumber}")
	Account getAccountDetails(@PathVariable String acNumber) {
		return service.getAccountByAcNumber(acNumber);     
	}
	
	
	@GetMapping("/updateBalance/{acNumber}/{newBalance}")  
    public void updateAccountBalance(@PathVariable String acNumber,  @PathVariable double newBalance) {
                             
		service.updateBalance(acNumber,newBalance); 
	}
	
	// To change the Status of the Acccount
	@GetMapping("/changeStatus/{acNumber}")
	public String changeStatus(@PathVariable String acNumber) {
		service.changeAccountStatus(acNumber); 
		return "Account Status Chenaged";  
	}
	
}
