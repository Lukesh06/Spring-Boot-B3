package com.ekalavya.employee.address.service;

import java.util.List;

import com.ekalavya.employee.address.model.AddressModel;

public interface EmployeeAddressService {

	public List<AddressModel> getEmployeeAddress(Integer employeeId);
	
}
