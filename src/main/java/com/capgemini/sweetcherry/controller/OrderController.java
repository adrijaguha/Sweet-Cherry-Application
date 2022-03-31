package com.capgemini.sweetcherry.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.sweetcherry.exceptions.NoSuchOrderExistsException;
import com.capgemini.sweetcherry.exceptions.PaymentFailedException;
import com.capgemini.sweetcherry.model.Orders;
import com.capgemini.sweetcherry.model.Payment;
import com.capgemini.sweetcherry.service.SweetCherryService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	SweetCherryService service;
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<String> cancelOnlineOrder (@PathVariable int orderId) {
		Optional<Orders> order = service.getOrderDetailsById(orderId);
		if(order == null)
			throw new NoSuchOrderExistsException();
		service.cancelOnlineOrder(orderId);
		return new ResponseEntity<String>("Order cancelled..",HttpStatus.OK);
	}
		

	@GetMapping("/userid/{userId}")
	public ResponseEntity<List<Orders>> showOrderDetailsByUserId(@PathVariable int userId)	{
		List<Orders> orders=service.showOrderDetailsByUserId(userId);
		if(orders == null)
			throw new NoSuchOrderExistsException();
		return new ResponseEntity<List<Orders>>(orders,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Orders>> getAllOrderDetails() {
		List<Orders> orderList=service.getAllOrderDetails();
		if(orderList.isEmpty())
			throw new NoSuchOrderExistsException();
		return new ResponseEntity<List<Orders>>(orderList,HttpStatus.OK);
	}
	
	@GetMapping("/orderid/{orderId}")
	public ResponseEntity<Optional<Orders>> getOrderDetailsById(@PathVariable int orderId) {
		Optional<Orders> orders = service.getOrderDetailsById(orderId);
		if(orders == null)
			throw new NoSuchOrderExistsException();
		return new ResponseEntity<Optional<Orders>>(orders,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Optional<Orders>> makeOnlineOrder(@RequestBody int orderId) {
		Optional<Orders> orders=service.makeOnlineOrder(orderId);
		if(orders == null)
			throw new NoSuchOrderExistsException();
		return new ResponseEntity<Optional<Orders>>(orders,HttpStatus.OK);
	}
}
