package com.capgemini.sweetcherry.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//create sequence address_seq start with 101 increment by 1 nocache nocycle;

@Entity
@Table(name = "address")
public class Address {
	@SequenceGenerator(name ="id4_seq", sequenceName="address_seq", allocationSize = 1)
	@Id
	@GeneratedValue(generator = "id4_seq")
	@Column(unique =true)
	private int addressId;
	@Column(name="city", length=20)
	@NotNull
	private String city;
	@Column(name="state", length=20)
	@NotNull
	private String state;
	@Column(name="pincode", length=6)
	@NotNull
	private String pinCode;
	@Column(name="houseno", length=10)
	@NotNull
	private String houseNo;
	@Column(name="landmark", length=30)
	private String landmark;
	
	@ManyToOne
	@JoinColumn(name ="userid")
	private UserDetails user;

	public Address() {
		
	}

	
	public Address(String city) {
		this.city = city;
	}


	public Address(int adressId, String city, String state, String pinCode, String houseNo, String landmark) {
		this.addressId = adressId;
		this.city = city;
		this.state = state;
		this.pinCode = pinCode;
		this.houseNo = houseNo;
		this.landmark = landmark;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
	public UserDetails getUser() {
		return user;
	}


	public void setUser(UserDetails user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", city=" + city + ", state=" + state + ", pinCode=" + pinCode
				+ ", houseNo=" + houseNo + ", landmark=" + landmark + "]";
	}
}
