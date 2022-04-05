package com.capgemini.sweetcherry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.sweetcherry.dto.UserDisplayDto;
import com.capgemini.sweetcherry.exceptions.NoSuchUserExistsException;
import com.capgemini.sweetcherry.service.SweetCherryService;

@Controller
@RequestMapping("/Admin_controller")
public class AdminController {
	
	@Autowired
	SweetCherryService service;
		
	@GetMapping
	public ResponseEntity<List<UserDisplayDto>> allDetailsOfAdminAndUser() {
		List<UserDisplayDto> user=service.allDetailsOfAdminAndUser();
		if(user.isEmpty())
			throw new NoSuchUserExistsException();
		return new ResponseEntity<List<UserDisplayDto>>(user,HttpStatus.OK);
	}
}