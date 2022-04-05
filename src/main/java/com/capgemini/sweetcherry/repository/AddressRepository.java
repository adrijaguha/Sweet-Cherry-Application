package com.capgemini.sweetcherry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.sweetcherry.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	@Query("SELECT ad FROM Address ad WHERE ad.user.userId=:userId")
	public List<Address> findByuserId(@Param("userId") int userId);

}