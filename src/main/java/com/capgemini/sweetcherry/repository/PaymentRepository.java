package com.capgemini.sweetcherry.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.sweetcherry.model.Payment;

@Repository
@Transactional
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	@Query("SELECT py FROM Payment py WHERE py.order.orderId=:orderId")
	public List<Payment> findByOrderId(@Param("orderId") int orderId);
	
}