/**
 * 
 */
package com.hcl.payments.paymentprocessor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.payments.paymentprocessor.entity.PaymentTransaction;

/**
 * @author mathan sakthi
 *
 */
@Repository
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
	
	@Modifying(flushAutomatically = true)
	@Query("update PaymentTransaction pt set pt.status =:status where pt.transactionId =:transactionId")
	void updateTransactionStatus(@Param("transactionId") Long transactionId, @Param("status") String status);
}
