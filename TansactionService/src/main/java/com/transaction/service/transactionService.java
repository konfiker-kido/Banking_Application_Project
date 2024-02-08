package com.transaction.service;


import java.util.List;

import com.transaction.DTO.moneyTransferRequest;
import com.transaction.entity.transaction;

public interface transactionService {
	
	transaction creditMoney(transaction tr);
	transaction debitMoney(transaction tr);
    List<transaction>  getAllTransaction(String acNumber); 
    transaction updateTransaction(transaction tr); 
    String sendMoney(moneyTransferRequest dto);
}
