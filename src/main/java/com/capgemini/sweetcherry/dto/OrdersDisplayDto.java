package com.capgemini.sweetcherry.dto;

import java.util.Map;

import com.capgemini.sweetcherry.model.CupcakeDetails;

public class OrdersDisplayDto {
	private int orderId;
	private String orderDate;
	private String orderStatus;
	private double totalPrice;
	private int addressId;
	private int userId;
	private Map<CupcakeDetails,Integer> cupcakeDetails=null;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Map<CupcakeDetails, Integer> getCupcakeDetails() {
		return cupcakeDetails;
	}
	public void setCupcakeDetails(Map<CupcakeDetails, Integer> cupcakeDetails) {
		this.cupcakeDetails = cupcakeDetails;
	}
	
	
}
