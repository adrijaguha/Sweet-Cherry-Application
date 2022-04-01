package com.capgemini.sweetcherry.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.sweetcherry.exceptions.NoSuchCupcakeCategoryExistsException;
import com.capgemini.sweetcherry.exceptions.NoSuchCupcakeExistsException;
import com.capgemini.sweetcherry.exceptions.NoSuchCupcakeExistsException;
import com.capgemini.sweetcherry.model.CupcakeCategory;
import com.capgemini.sweetcherry.model.CupcakeDetails;
import com.capgemini.sweetcherry.service.SweetCherryService;

@RestController
@RequestMapping("/cupcakedetails")
public class CupcakeDetailsController {
	@Autowired
	SweetCherryService service;
	@PostMapping
	public ResponseEntity<String> addCupcakeDetails(@RequestBody CupcakeDetails cupcakedetails){ 
		Optional<CupcakeCategory> category=service.getCupcakeCategoryById(cupcakedetails.getCupcakeCategory().getCategoryId());
		if(category!=null) {
		service.addCupcakeDetails(cupcakedetails);
		return new ResponseEntity<String>("Cupcake Details added",HttpStatus.OK);
		}
		else
			throw new NoSuchCupcakeCategoryExistsException();
	}
	
	@GetMapping
	public ResponseEntity<List<CupcakeDetails>> showCupcakeDetails(){
		List<CupcakeDetails> cupcakeList = service.showCupcakeDetails();
		if(!cupcakeList.isEmpty())
		return new ResponseEntity<List<CupcakeDetails>>(cupcakeList,HttpStatus.OK);
		else
			throw new NoSuchCupcakeExistsException("No cupcakes to display");
		
	}
	
	@GetMapping("/{cupcakeId}")
	public ResponseEntity<Optional<CupcakeDetails>> findCupcakeDetailsById(@PathVariable int cupcakeId){
		if(service.findCupcakeDetailsById(cupcakeId)!=null) {
		Optional<CupcakeDetails> cupcake = service.findCupcakeDetailsById(cupcakeId);
		return new ResponseEntity<Optional<CupcakeDetails>>(cupcake,HttpStatus.OK);
		}
		else
			throw new NoSuchCupcakeExistsException();

	}
	
	@PutMapping("/updateprice/{cupcakeId}/{price}")
	public ResponseEntity<Optional<CupcakeDetails>> updateCupcakePriceByCupcakeId(@PathVariable int cupcakeId,@PathVariable double price){
		if(service.findCupcakeDetailsById(cupcakeId)!=null) {
	    Optional<CupcakeDetails> modified_cupcake = service.updateCupcakePriceByCupcakeId(cupcakeId, price);
	    return new ResponseEntity<Optional<CupcakeDetails>>(modified_cupcake,HttpStatus.OK);
		}
		else
			throw new NoSuchCupcakeExistsException();
	}
	
	@PutMapping("/update rating/{cupcakeId}/{rating}")
	public ResponseEntity<Optional<CupcakeDetails>> modifyCupcakeRating(@PathVariable int cupcakeId,@PathVariable int rating){
		if(service.findCupcakeDetailsById(cupcakeId)!=null) {
			Optional<CupcakeDetails> modified_cupcake = service.modifyCupcakeRating(cupcakeId, rating);
	        return new ResponseEntity<Optional<CupcakeDetails>>(modified_cupcake,HttpStatus.OK);
		}
		else
			throw new NoSuchCupcakeExistsException();
	}
	@PutMapping("/update cupcakename/{cupcakeId}/{cupcakeName}")
	public ResponseEntity<Optional<CupcakeDetails>> modifyCupcakeName(@PathVariable int cupcakeId,@PathVariable String cupcakeName){
		if(service.findCupcakeDetailsById(cupcakeId)!=null) {
		Optional<CupcakeDetails> modified_cupcake = service.modifyCupcakeName(cupcakeId, cupcakeName);
		return new ResponseEntity<Optional<CupcakeDetails>>(modified_cupcake,HttpStatus.OK);
	}
	else
		throw new NoSuchCupcakeExistsException();
	}
	
	@DeleteMapping("/{cupcakeId}")
	public ResponseEntity<String> removeCupcakeDetails(@PathVariable int cupcakeId){
		if(service.findCupcakeDetailsById(cupcakeId)!=null) {
		String message=service.removeCupcakeDetails(cupcakeId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
		}
		else
			throw new NoSuchCupcakeExistsException();
	}
	
}
