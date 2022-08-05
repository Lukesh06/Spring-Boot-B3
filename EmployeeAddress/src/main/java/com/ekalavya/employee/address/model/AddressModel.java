package com.ekalavya.employee.address.model;

import com.ekalavya.employee.address.enums.AddressType;

public class AddressModel {

	private String address;
	private String city;
	private String state;
	private Integer pin;
	private AddressType type;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}

	public AddressModel(String address, String city, String state, Integer pin, AddressType type) {
		this.address = address;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.type = type;
	}

}
