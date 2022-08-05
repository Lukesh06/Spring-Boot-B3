package com.ekalavya.employee.model;

public class EmployeeAddress {

	private String address;
	private String city;
	private String state;
	private Integer pin;
	private String type;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public EmployeeAddress(String address, String city, String state, Integer pin, String type) {
		super();
		this.address = address;
		this.city = city;
		this.state = state;
		this.pin = pin;
		this.type = type;
	}

}
