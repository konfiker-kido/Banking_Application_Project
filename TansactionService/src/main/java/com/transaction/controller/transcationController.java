package com.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.DTO.moneyTransferRequest;
import com.transaction.entity.transaction;
import com.transaction.service.transactionService;

@RestController
@RequestMapping("/transaction")
public class transcationController {
  
	@Autowired
	transactionService service;  
	 
	
	// For Credit 
	@PostMapping("/credit")
	public ResponseEntity<transaction> creditAmount(@RequestBody transaction tr) { 
		
		transaction tr1=	 service.creditMoney(tr);
		// Check if the returned transaction is null, indicating no entity found
        if (tr1 == null) {
            // Return a 404 Not Found response
            return ResponseEntity.notFound().build();
        }
      
        // Return the transaction with a 200 OK response
        return ResponseEntity.ok(tr1);
	}
	
	// For Debit 
	@PostMapping("/debit") 
	public ResponseEntity<transaction> debitAmount(@RequestBody transaction tr) { 
		
		transaction tr1= service.debitMoney(tr); 
		// Check if the returned transaction is null, indicating no entity found
        if (tr1 == null) { 
            // Return a 404 Not Found response
            return ResponseEntity.notFound().build();
        }
      
        // Return the transaction with a 200 OK response
        return ResponseEntity.ok(tr1);
	}
	
	
	// Getting all the transaction according to Account Number  
	@GetMapping("/getAllTransaction/{acNumber}")  
	public List<transaction> getAllTransaction(@PathVariable String acNumber) {
		return service.getAllTransaction(acNumber); 
	}
	
	// For Sending the Money to different Account  
	@PostMapping("/sendMoney")
	public ResponseEntity<String> sendMoney(@RequestBody moneyTransferRequest dto) {
		 
		String output=service.sendMoney(dto);
		 if (output == null) { 
	            // Return a 404 Not Found response
	            return ResponseEntity.notFound().build();
	        }
		 return ResponseEntity.ok(output);    
	}
	
	
}
