package com.transaction.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transaction.entity.transaction;

public interface transactionRepo extends JpaRepository<transaction, Long> {
	
	List<transaction> getAllTransactionByAccountNumber(String acNumber);   
	 
} 
