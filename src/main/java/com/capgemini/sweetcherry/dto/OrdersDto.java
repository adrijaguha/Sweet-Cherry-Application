package com.capgemini.sweetcherry.dto;

public class OrdersDto {
	private int orderId;
	private int userId;
	private int cupcakeId;
	private int quantity;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCupcakeId() {
		return cupcakeId;
	}
	public void setCupcakeId(int cupcakeId) {
		this.cupcakeId = cupcakeId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
