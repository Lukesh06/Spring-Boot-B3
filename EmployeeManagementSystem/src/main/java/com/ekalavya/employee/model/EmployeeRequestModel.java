package com.ekalavya.employee.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmployeeRequestModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "First Name Should Not be null")
	private String firstName;
	
	private String lastName;
	
	@NotNull(message = "Address Should Not be null")
	private String address;
	
	@NotNull(message = "City Should Not be null")
	private String city;
	
	private String state;
	
	@NotNull(message = "Mobile Number Should Not be null")
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile Number Should be 10 digit number") 
	private String mobileNumber;
	
	@NotNull(message = "Email Id should not be null")
	@Email(message = "Email should be valid email address")
	private String emailId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	
	
}
