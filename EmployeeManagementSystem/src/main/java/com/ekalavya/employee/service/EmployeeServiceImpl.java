package com.ekalavya.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ekalavya.employee.dao.EmployeeDao;
import com.ekalavya.employee.dto.EmployeeDto;
import com.ekalavya.employee.model.Employee;
import com.ekalavya.employee.model.EmployeeRequestModel;
import com.ekalavya.employee.model.EmployeeResponseData;
import com.ekalavya.employee.model.EmployeeResponseModel;

@Service
@Primary
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public Employee getEmployee() {
		Employee employee = new Employee(100, "Harsh Thakur", "Indore", "Madhya Pradesh");
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
		EmployeeDto employeeDto = new EmployeeDto();

		BeanUtils.copyProperties(request, employeeDto);

		String referenceNumber = UUID.randomUUID().toString().substring(0, 24);

		employeeDto.setEmployeeReferenceNumber(referenceNumber);

		employeeDto = employeeDao.saveEmployee(employeeDto);

		BeanUtils.copyProperties(employeeDto, response);

		response.setEmployeeName(employeeDto.getFirstName() + " " + employeeDto.getLastName());

		data.setDatails(response);
		return data;
	}

}
