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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.sweetcherry.dto.OrdersDisplayDto;
import com.capgemini.sweetcherry.dto.OrdersDto;
import com.capgemini.sweetcherry.exceptions.CupcakeNotAvailableException;
import com.capgemini.sweetcherry.exceptions.NoSuchOrderExistsException;
import com.capgemini.sweetcherry.exceptions.NoSuchUserExistsException;
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
		OrdersDisplayDto order = service.getOrderDetailsById(orderId);
		if(order == null)
			throw new NoSuchOrderExistsException();
		service.cancelOnlineOrder(orderId);
		return new ResponseEntity<String>("Order cancelled..",HttpStatus.OK);
	}
	
	@PostMapping("/addToCart")
	public ResponseEntity<String> addCupcakeToCart(@RequestBody OrdersDto order){
		String response = service.addCupcakeToCart(order);
		if(response == null)
			throw new NoSuchUserExistsException();
		if(response.equalsIgnoreCase("not available"))
			throw new CupcakeNotAvailableException();
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
		

	@GetMapping("/userid/{userId}")
	public ResponseEntity<List<OrdersDisplayDto>> showOrderDetailsByUserId(@PathVariable int userId)	{
		List<OrdersDisplayDto> orders=service.showOrderDetailsByUserId(userId);
		if(orders == null)
			throw new NoSuchUserExistsException();
		return new ResponseEntity<List<OrdersDisplayDto>>(orders,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<OrdersDisplayDto>> getAllOrderDetails() {
		List<OrdersDisplayDto> orderList=service.getAllOrderDetails();
		if(orderList.isEmpty())
			throw new NoSuchOrderExistsException();
		return new ResponseEntity<List<OrdersDisplayDto>>(orderList,HttpStatus.OK);
	}
	
	@GetMapping("/orderid/{orderId}")
	public ResponseEntity<OrdersDisplayDto> getOrderDetailsById(@PathVariable int orderId) {
		OrdersDisplayDto orders = service.getOrderDetailsById(orderId);
		if(orders == null)
			throw new NoSuchOrderExistsException();
		return new ResponseEntity<OrdersDisplayDto>(orders,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<OrdersDisplayDto> makeOnlineOrder(@RequestParam("orderId") int orderId, @RequestParam("addressId") int addressId) {
		OrdersDisplayDto orders=service.makeOnlineOrder(orderId,addressId);
		if(orders == null)
			throw new NoSuchOrderExistsException();
		return new ResponseEntity<OrdersDisplayDto>(orders,HttpStatus.OK);
	}
	
	@PostMapping("/status/{orderId}")
	public ResponseEntity<OrdersDisplayDto> confirmOrderStatus(@RequestParam("orderId") int orderId, @RequestParam("orderStatus") String orderStatus) {
		OrdersDisplayDto orders=service.confirmOrderStatus(orderId,orderStatus);
		if(orders == null)
			throw new NoSuchOrderExistsException();
		return new ResponseEntity<OrdersDisplayDto>(orders,HttpStatus.OK);
	}
}
