package com.capgemini.sweetcherry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.sweetcherry.dto.PaymentDto;
import com.capgemini.sweetcherry.exceptions.PaymentFailedException;
import com.capgemini.sweetcherry.model.Payment;
import com.capgemini.sweetcherry.service.SweetCherryService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	SweetCherryService service;
	
	@PostMapping
	public ResponseEntity<String> makeOnlinePayment(@RequestBody PaymentDto payment) {
		service.makeOnlinePayment(payment);
		Payment check= service.getPaymentById(payment.getPaymentId());
		if(!(check == null) && check.getStatus().equalsIgnoreCase("Payment Successful"))
			return new ResponseEntity<String>("Payment successful...",HttpStatus.OK);
		else
			throw new PaymentFailedException();
		
	}
}
