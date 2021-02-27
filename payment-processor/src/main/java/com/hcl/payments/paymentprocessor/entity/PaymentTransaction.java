/**
 * 
 */
package com.hcl.payments.paymentprocessor.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author mathan sakthi
 *
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentTransaction {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long transactionId;
	private String messageID;
	private String endToEndId;
	
	@NotNull
	@Column(length=35)
	@Size(min = 1, max = 35)
	private String debtorAccount;
	private String debtorName; 
	private String debtorAddress;
	
	@NotNull
	@Column(length=35)
	@Size(min = 1, max = 35)
	private String creditorAccount;
	private String creditorName;
	private String creditorAddress;
	
	
	private String debtorAgentIdentifier;
	private String creditorAgentIdentifier; 
	private String paymentCurrency;
	@Column(columnDefinition="Decimal(10,2)")
	private Double paymentAmount;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date valueDate;
	private String remittanceInfo;
	private String status;

}
