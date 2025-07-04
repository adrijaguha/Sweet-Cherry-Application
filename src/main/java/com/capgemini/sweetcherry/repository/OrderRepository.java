package com.capgemini.sweetcherry.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.sweetcherry.model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

	@Query(value = "SELECT o FROM Orders o WHERE o.userDetails.userId=:userId")
	public List<Orders> findByuserId(@Param("userId") int userId);
	
}