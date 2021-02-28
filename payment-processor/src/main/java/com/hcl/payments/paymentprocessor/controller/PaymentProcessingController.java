package com.hcl.payments.paymentprocessor.controller;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hcl.payments.paymentprocessor.entity.PaymentTransaction;
import com.hcl.payments.paymentprocessor.service.PaymentProcessingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/paymentprocess")
public class PaymentProcessingController {

	Set<String> countries = new HashSet<>();
	Set<String> bankCode = new HashSet<>();

	@Autowired
	private RestTemplate restTemplate;

	@PostConstruct
	public void initData() {

		countries.add("ITL");
		countries.add("XYZ");

		bankCode.add("abcd");
		bankCode.add("efgh");

	}

	@Autowired
	private PaymentProcessingService paymentProcessingService;

	@PostMapping(value = "/")
	public ResponseEntity<Object> processPayment(@RequestBody PaymentTransaction paymentTransaction) {

		log.info("Processing the payment");
		try {

			if (isValidInput(paymentTransaction)) {

				PaymentTransaction pTransaction = paymentProcessingService.saveTransaction(paymentTransaction);
				ResponseEntity<PaymentTransaction> responseEntity = restTemplate.postForEntity(
						"http://localhost:9090/sanctions-check-service/getSanctionsCheck", pTransaction,
						PaymentTransaction.class);
				Assert.isTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));

				return new ResponseEntity<Object>(responseEntity.getBody(), HttpStatus.OK);

			} else

				return new ResponseEntity<Object>(paymentTransaction, HttpStatus.BAD_GATEWAY);
		}

		catch (Exception e) {

			log.error(e.getMessage());
			return new ResponseEntity<Object>(paymentTransaction, HttpStatus.BAD_GATEWAY);
		}

	}

	// check if input data is valid
	private boolean isValidInput(PaymentTransaction paymentTransaction) {

		// should be annotation based
		// validate DebtorAgentID
		if (paymentTransaction.getDebtorAgentIdentifier().length() != 9
				|| !bankCode.contains(paymentTransaction.getDebtorAgentIdentifier().toString().subSequence(0, 4))
				|| !countries.contains(paymentTransaction.getDebtorAgentIdentifier().toString().subSequence(4, 7))
				|| !paymentTransaction.getPaymentCurrency().equalsIgnoreCase("EUR"))
			return false;

		// validate CreditorAgentID
		if (paymentTransaction.getCreditorAgentIdentifier().length() != 9
				|| !bankCode.contains(paymentTransaction.getCreditorAgentIdentifier().subSequence(0, 4))
				|| !countries.contains(paymentTransaction.getCreditorAgentIdentifier().subSequence(4, 7)))
			return false;

		return true;
	}

}
