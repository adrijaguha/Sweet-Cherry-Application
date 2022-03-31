package com.capgemini.sweetcherry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.sweetcherry.model.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
}
