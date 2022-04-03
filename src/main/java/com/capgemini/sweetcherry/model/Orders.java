package com.capgemini.sweetcherry.model;
  
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//create sequence order_seq start with 11 increment by 1 nocache nocycle;

@Entity
@Table(name="orders")
public class Orders {
	@SequenceGenerator(name ="id6_seq", sequenceName="order_seq", allocationSize = 1)
	@Id
	@GeneratedValue(generator = "id6_seq")
	@Column(unique = true)
	private int orderId;
	@Column(length=10)
	@NotNull
	private String orderDate;
	@Column(length=20)
	@NotNull
	private String orderStatus;
	@Column
	@NotNull
	private double totalPrice;
	
	@Column
	private int addressId;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private UserDetails userDetails;
	
	@ElementCollection
	@CollectionTable(name = "orders_cupcake_details",
			joinColumns = {@JoinColumn(name="orderid")})
	@MapKeyJoinColumn(name="cupcakeid")
	@Column(name="quantity")
	private Map<CupcakeDetails,Integer> cupcakeDetails=null;
	
	public Orders() {
		
	}


	public Orders(int orderId, String orderDate, String orderStatus, double totalPrice,
			UserDetails userDetails, Map<CupcakeDetails,Integer> cupcakeDetails) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.totalPrice = totalPrice;
		this.userDetails = userDetails;
		this.cupcakeDetails = cupcakeDetails;
	}


	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Map<CupcakeDetails,Integer> getCupcakeDetails() {
		return cupcakeDetails;
	}

	public void setCupcakeDetails(Map<CupcakeDetails,Integer> cupcakeDetails) {
		this.cupcakeDetails = cupcakeDetails;
	}

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

	
	public int getAddressId() {
		return addressId;
	}


	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}


	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	
}