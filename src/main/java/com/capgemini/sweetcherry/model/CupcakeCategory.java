package com.capgemini.sweetcherry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="cupcake_category")
public class CupcakeCategory {
	@Id
	private int categoryId;
	@Column(length=30)
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
