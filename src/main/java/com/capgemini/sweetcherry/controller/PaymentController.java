package com.capgemini.sweetcherry.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.sweetcherry.dto.PaymentDisplayDto;
import com.capgemini.sweetcherry.dto.PaymentDto;
import com.capgemini.sweetcherry.exceptions.NoPaymentExistsException;
import com.capgemini.sweetcherry.exceptions.NoSuchOrderExistsException;
import com.capgemini.sweetcherry.exceptions.PaymentFailedException;
import com.capgemini.sweetcherry.model.Payment;
import com.capgemini.sweetcherry.service.SweetCherryService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	SweetCherryService service;
	
	@PostMapping("/status")
	public ResponseEntity<String> confirmPayment(@RequestParam("PaymnetId") int paymentId, @RequestParam("PaymentStatus") String paymentStatus) {
		Payment payment = service.confirmPayment(paymentId, paymentStatus);
		if(payment == null)
			throw new PaymentFailedException();
		return new ResponseEntity<String>("Payment Successful...",HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<String>  addPaymentDetails(@RequestBody PaymentDto payment) {
		Payment paymentDetails = service.addPaymentDetails(payment);
		if(paymentDetails == null)
			throw new NoSuchOrderExistsException();
		return new ResponseEntity<String>("Payment details added...",HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<PaymentDisplayDto>> showAllPayment() {
		List<PaymentDisplayDto> paymentList=service.showAllPayment();
		if(paymentList.isEmpty())
			throw new NoPaymentExistsException();
		return new ResponseEntity<List<PaymentDisplayDto>>(paymentList,HttpStatus.OK);
	}
	
	@GetMapping("/orderid/{orderId}")
	public ResponseEntity<List<PaymentDisplayDto>> showPaymentById(@PathVariable int orderId) {
		List<PaymentDisplayDto> payment=service.showPaymentById(orderId);
		if(payment == null)
			throw new NoPaymentExistsException();
		return new ResponseEntity<List<PaymentDisplayDto>>(payment,HttpStatus.OK);
	}
}
