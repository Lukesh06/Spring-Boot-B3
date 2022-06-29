package com.ekalavya.employee.service;

import java.util.List;

import com.ekalavya.employee.model.Employee;
import com.ekalavya.employee.model.EmployeeRequestModel;
import com.ekalavya.employee.model.EmployeeResponseData;

public interface EmployeeService {

	Employee getEmployee();
	
	List<Employee> getAllEmployees();
	
	EmployeeResponseData saveEmployee(EmployeeRequestModel request);
	
}
