package com.capgemini.sweetcherry.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.sweetcherry.exceptions.NoSuchUserExistsException;
import com.capgemini.sweetcherry.exceptions.UserNameAndPasswordDoNotMatchRegularExpressionException;
import com.capgemini.sweetcherry.model.UserDetails;

import com.capgemini.sweetcherry.service.SweetCherryService;



@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	SweetCherryService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam("password") String password){
		String response = service.login(email, password);
		if(response.equals("Login Unsuccessful")) {
			throw new NoSuchUserExistsException();
		}
		else {
			
			return new ResponseEntity<String>(response,HttpStatus.OK);
		}
		
	}
	
	
	
	
	
	
	
	
}
