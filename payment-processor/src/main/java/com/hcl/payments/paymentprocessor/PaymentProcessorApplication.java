package com.hcl.payments.paymentprocessor;

import java.util.Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PaymentProcessorApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(PaymentProcessorApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}
