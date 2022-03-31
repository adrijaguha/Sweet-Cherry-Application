package com.capgemini.sweetcherry.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.sweetcherry.exceptions.NoSuchCupcakeCategoryExistsException;
import com.capgemini.sweetcherry.model.CupcakeCategory;
import com.capgemini.sweetcherry.service.SweetCherryService;

@RestController
@RequestMapping("/cupcakecategory")
public class CupcakeCategoryController {
	@Autowired
	SweetCherryService service;
	
	@PostMapping
	public ResponseEntity<String> addCupcakeCategory(@RequestBody CupcakeCategory cupcakeCategory){
		service.addCupcakeCategory(cupcakeCategory);
		return new ResponseEntity<String>("Cupcake Category added",HttpStatus.OK);
	}
	@GetMapping("/{cupcakecategoryId}")
	public ResponseEntity<Optional<CupcakeCategory>> getCupcakeCategoryById(@PathVariable int cupcakecategoryId){
		if(service.getCupcakeCategoryById(cupcakecategoryId)!=null) {
			Optional<CupcakeCategory> cupcakecategory = service.getCupcakeCategoryById(cupcakecategoryId);
			return new ResponseEntity<Optional<CupcakeCategory>>(cupcakecategory,HttpStatus.OK);
			}
			else
				throw new NoSuchCupcakeCategoryExistsException();
		
	}
	
}
