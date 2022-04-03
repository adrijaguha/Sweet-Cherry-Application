package com.capgemini.sweetcherry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//create sequence cupcake_seq start with 1001 increment by 2 maxvalue 5000;

@Entity
@Table(name="cupcake_details")
public class CupcakeDetails {
	@SequenceGenerator(name ="id2_seq", sequenceName="cupcake_seq", allocationSize = 1)
	@Id
	@GeneratedValue(generator = "id2_seq")
	private int cupcakeId;
	@Column(length=30, unique = true)
	@NotNull
	private String cupcakeName;
	@Column(length=80)
	private String cupcakeDescription;
	@Column
	@NotNull
	private double price;
	@Column
	private int rating;
	@Column
	private int quantity;
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private CupcakeCategory cupcakeCategory;
	
	public CupcakeDetails(String cupcakeName, String cupcakeDescription, @Min(0) double price, int rating,int quantity,
			CupcakeCategory cupcakeCategory) {
		super();
		this.cupcakeName = cupcakeName;
		this.cupcakeDescription = cupcakeDescription;
		this.price = price;
		this.rating = rating;
		this.cupcakeCategory = cupcakeCategory;
	}


	  public CupcakeCategory getCupcakeCategory() {
		  return cupcakeCategory; 
		  }
	 

	public void setCupcakeCategory(CupcakeCategory cupcakeCategory) {
		this.cupcakeCategory = cupcakeCategory;
	}

	public CupcakeDetails() {
		super();
	}
	

	public CupcakeDetails(int cupcakeId, String cupcakeName, String cupcakeDescription, @Min(0) double price,
			 int rating, CupcakeCategory cupcakeCategory) {
		super();
		this.cupcakeId = cupcakeId;
		this.cupcakeName = cupcakeName;
		this.cupcakeDescription = cupcakeDescription;
		this.price = price;
		this.rating = rating;
		this.quantity = quantity;
		this.cupcakeCategory = cupcakeCategory;
	}

	public int getCupcakeId() {
		return cupcakeId;
	}
	public void setCupcakeId(int cupcakeId) {
		this.cupcakeId = cupcakeId;
	}
	public String getCupcakeName() {
		return cupcakeName;
	}
	public void setCupcakeName(String cupcakeName) {
		this.cupcakeName = cupcakeName;
	}
	public String getCupcakeDescription() {
		return cupcakeDescription;
	}
	public void setCupcakeDescription(String cupcakeDescription) {
		this.cupcakeDescription = cupcakeDescription;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "CupcakeDetails [cupcakeId=" + cupcakeId + ", cupcakeName=" + cupcakeName + ", cupcakeDescription="
				+ cupcakeDescription + ", price=" + price + ", rating=" + rating +
				 "]";
	}
}
