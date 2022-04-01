package com.capgemini.sweetcherry.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.sweetcherry.dto.UserDetailsDto;
import com.capgemini.sweetcherry.exceptions.NoSuchUserExistsException;
import com.capgemini.sweetcherry.exceptions.UserNameAndPasswordDoNotMatchRegularExpressionException;
import com.capgemini.sweetcherry.model.UserDetails;
import com.capgemini.sweetcherry.service.SweetCherryService;

@RestController
@RequestMapping("/user_details")
public class UserDetailsController {
	
	@Autowired
	SweetCherryService service;
	@PutMapping("/update customer details")
	public ResponseEntity<String> updateCustomerProfile(@RequestBody UserDetailsDto customer) {
		Optional<UserDetails> upd = service.allUserDetailsById(customer.getUserId());
		if(upd==null)
			throw new NoSuchUserExistsException();
		service.updateCustomerProfile(customer);
		return new ResponseEntity<String>("Updated Customer Details...",HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<String> registerCustomer(@RequestBody UserDetailsDto user){
		UserDetails u = service.registerCustomer(user);
		if(u==null)
			throw new UserNameAndPasswordDoNotMatchRegularExpressionException();
		return new ResponseEntity<String>("Registration successful...",HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> modifyPassword(@RequestParam("userId") int userId,@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword) {
		String updpwd =service.modifyPassword(userId,oldPassword, newPassword);
		if(updpwd.equals("Invalid User"))
			return new ResponseEntity<String>("Invalid User",HttpStatus.NOT_FOUND);
		if(updpwd.equals("Invalid"))
			return new ResponseEntity<String>("Invalid password",HttpStatus.NOT_FOUND);
		return new ResponseEntity<String>("Password updated Successfully",HttpStatus.OK);
	}
	
	@GetMapping("/userId/{userId}")
	public ResponseEntity<Optional<UserDetails>> allUserDetailsById(@PathVariable int UserId){
		Optional<UserDetails> usrdetails = service.allUserDetailsById(UserId);
		if(usrdetails==null)
			throw new NoSuchUserExistsException();
		return new ResponseEntity<Optional<UserDetails>>(usrdetails,HttpStatus.OK);
	}
	
	
}
