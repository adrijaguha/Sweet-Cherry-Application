package com.capgemini.sweetcherry.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//create sequence payment_seq start with 1001 increment by 1 nocache nocycle;

@Entity
@Table(name="payment")
public class Payment {
	@SequenceGenerator(name ="id3_seq", sequenceName="payment_seq", allocationSize = 1)
	@Id
	@GeneratedValue(generator = "id3_seq")
	private int paymentId;
	@Column
	@NotNull
	private long cardNo;
	@Column
	@NotNull
	private int cvv;
	@Column(length=30)
	@NotNull
	private String cardHolderName;
	@Column(length=10)
	@NotNull
	private String expiryDate;
	@Column(length=20)
	@NotNull
	private String status;
	
	@OneToOne
	@JoinColumn(name = "orderid")
	private Orders order;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Payment() {
	}

	public Payment( long cardNo, int cvv, String cardHolderName, String expiryDate, String status,
			Orders order) {
		super();
		//this.paymentId = paymentId;
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.cardHolderName = cardHolderName;
		this.expiryDate = expiryDate;
		this.status = status;
		this.order = order;
		
	}

	public Payment(String string) {
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public long getCardNo() {
		return cardNo;
	}

	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	
	  public String getPaymentStatus() { return status; }
	  
	  public void setPaymentStatus(String paymentStatus) { this.status =
	  paymentStatus; }
	 

}
