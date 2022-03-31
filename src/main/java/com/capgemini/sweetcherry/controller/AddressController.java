package com.capgemini.sweetcherry.controller;

import java.util.Optional;

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

import com.capgemini.sweetcherry.dto.AddressDto;
import com.capgemini.sweetcherry.exceptions.NoSuchAddressExistsException;
import com.capgemini.sweetcherry.exceptions.NoSuchUserExistsException;
import com.capgemini.sweetcherry.model.Address;
import com.capgemini.sweetcherry.model.UserDetails;
import com.capgemini.sweetcherry.service.SweetCherryService;

@RestController
@RequestMapping("/address")
public class AddressController {
	@Autowired
	SweetCherryService service;
	
	@DeleteMapping("/{addressId}")
	public ResponseEntity<String> deleteDeliveryAddress(@PathVariable int addressId) {
		Optional<Address> address = service.getDeliveryAddress(addressId);
		if(address == null)
			throw new NoSuchAddressExistsException();
		service.deleteDeliveryAddress(addressId);
		return new ResponseEntity<String>("Delivery addrss deleted",HttpStatus.OK);
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<Optional<Address>> getDeliveryAddress(@PathVariable int addressId) {
		Optional<Address> address=service.getDeliveryAddress(addressId);
		if(address == null)
			throw new NoSuchAddressExistsException();
		return new ResponseEntity<Optional<Address>>(address,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> addDeliveryAddress(@RequestBody AddressDto address) {
		Optional<UserDetails> ud = service.viewUserDetailsById(address.getUserId());
		if(ud == null)
			throw new NoSuchUserExistsException();
		service.addDeliveryAddress(address);
		return new ResponseEntity<String>("Delivery address added...",HttpStatus.OK);
	}
	
	@PutMapping("/modify address")
	public ResponseEntity<String> modifyDeliveryAddress(@RequestBody AddressDto address) {
		Optional<UserDetails> ud = service.viewUserDetailsById(address.getUserId());
		if(ud == null)
			throw new NoSuchUserExistsException();
		service.modifyDeliveryAddress(address);
		return new ResponseEntity<String>("Address Modified",HttpStatus.OK);
	}
}
