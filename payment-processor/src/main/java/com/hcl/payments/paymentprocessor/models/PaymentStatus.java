package com.hcl.payments.paymentprocessor.models;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatus extends Exception{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long transactionId;
	private String transactionStatus;
	private HttpStatus httpStatus;

}
