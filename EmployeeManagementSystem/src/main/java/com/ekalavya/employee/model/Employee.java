package com.ekalavya.employee.model;

public class Employee {

	private int empId;
	
	private String name;
	
	private String city;
	
	private String state;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Employee(int empId, String name, String city, String state) {
		super();
		this.empId = empId;
		this.name = name;
		this.city = city;
		this.state = state;
	}
	
	
	
}
