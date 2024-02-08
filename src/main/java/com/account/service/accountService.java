package com.account.service;

import com.account.entity.Account;

public interface accountService {
 
	 Account createAccount(Account ac); 
	 double getBankBalance(String acNumber);
	 void changeAccountStatus(String acNumber); 
}
