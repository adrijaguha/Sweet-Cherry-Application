package com.capgemini.sweetcherry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//create sequence category_seq start with 101 increment by 2 nocache nocycle;

@Entity
@Table(name="cupcake_category")
public class CupcakeCategory {
	@SequenceGenerator(name ="id5_seq", sequenceName="category_seq", allocationSize = 2)
	@Id
	@GeneratedValue(generator = "id5_seq")
	@Column(unique =true)
	private int categoryId;
	@Column(length=30, unique=true)
	@NotNull
	private String categoryName;
	
	public CupcakeCategory() {
	}
	
	public CupcakeCategory(int categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categorId) {
		this.categoryId = categorId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
