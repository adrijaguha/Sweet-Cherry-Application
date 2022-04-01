package com.capgemini.sweetcherry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.sweetcherry.service.SweetCherryService;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@Autowired
	SweetCherryService service;
	
	@GetMapping
	public ResponseEntity<String> logout(){
		service.logout();
		return new ResponseEntity<String>("Logout successful...",HttpStatus.OK);
	}

}
