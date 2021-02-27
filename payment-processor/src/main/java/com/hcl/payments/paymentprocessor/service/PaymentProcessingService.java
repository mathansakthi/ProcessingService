package com.hcl.payments.paymentprocessor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.payments.paymentprocessor.entity.PaymentTransaction;
import com.hcl.payments.paymentprocessor.repository.PaymentTransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentProcessingService {
	
		@Autowired
		private PaymentTransactionRepository paymentTransactionRepository;
		

		public PaymentTransaction saveTransaction(PaymentTransaction department) {
			
			log.info("saving the transaction");
			return paymentTransactionRepository.save(department);
		}
		
		public void saveTransactionStatusById(Long transactionId, String status)
		{
			log.info("updating the transaction status");
			paymentTransactionRepository.updateTransactionStatus(transactionId,status);
		}
		
}
