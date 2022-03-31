package com.capgemini.sweetcherry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.sweetcherry.model.CupcakeCategory;


public interface CupcakeCategoryRepository extends JpaRepository<CupcakeCategory, Integer> {

}
