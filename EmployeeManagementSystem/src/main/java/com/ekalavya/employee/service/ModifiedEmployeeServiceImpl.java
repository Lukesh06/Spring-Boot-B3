package com.ekalavya.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ekalavya.employee.model.Employee;
import com.ekalavya.employee.model.EmployeeRequestModel;
import com.ekalavya.employee.model.EmployeeResponseData;
import com.ekalavya.employee.model.EmployeeResponseModel;

@Service
public class ModifiedEmployeeServiceImpl implements EmployeeService {

	@Override
	public Employee getEmployee() {
		Employee employee = new Employee(200, "Rahul Sharma", "Mumbai", "Maharastra");
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> listEmployee = new ArrayList<>();

		Employee employee1 = new Employee(100, "Harsh Thakur", "Indore", "Madhya Pradesh");
		Employee employee2 = new Employee(101, "Monit", "Bhopal", "Madhya Pradesh");
		Employee employee3 = new Employee(102, "Aishwarya", "Indore", "Madhya Pradesh");
		Employee employee4 = new Employee(103, "Amit Bhagat", "Pune", "Maharastra");

		listEmployee.add(employee1);
		listEmployee.add(employee2);
		listEmployee.add(employee3);
		listEmployee.add(employee4);
		return listEmployee;
	}

	@Override
	public EmployeeResponseData saveEmployee(EmployeeRequestModel request) {
		EmployeeResponseData data = new EmployeeResponseData();
		EmployeeResponseModel response = new EmployeeResponseModel();

		BeanUtils.copyProperties(request, response);

		String referenceNumber = UUID.randomUUID().toString();

		response.setEmployeeReferenceNumber(referenceNumber);

		response.setEmployeeName(request.getFirstName() + " " + request.getLastName());

		data.setDatails(response);
		return data;
	}

}
