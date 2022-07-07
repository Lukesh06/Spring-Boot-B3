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
	public EmployeeResponseData getEmployeeDetails(int empId) {
		EmployeeResponseData data = new EmployeeResponseData();
		EmployeeResponseModel response = new EmployeeResponseModel();
		EmployeeDto employeeDto = employeeDao.getEmployeeDetails(empId);

		BeanUtils.copyProperties(employeeDto, response);

		response.setEmployeeName(employeeDto.getFirstName() + " " + employeeDto.getLastName());

		data.setDatails(response);
		return data;
	}

	@Override
	public List<EmployeeResponseData> getAllEmployees() {

		List<EmployeeResponseData> listEmployee = new ArrayList<>();

		List<EmployeeDto> listEmployeeDto = employeeDao.getAllEmployees();

		for (EmployeeDto employeeDto : listEmployeeDto) {

			EmployeeResponseData data = new EmployeeResponseData();

			EmployeeResponseModel response = new EmployeeResponseModel();

			BeanUtils.copyProperties(employeeDto, response);

			response.setEmployeeName(employeeDto.getFirstName() + " " + employeeDto.getLastName());

			data.setDatails(response);

			listEmployee.add(data);
		}

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

	@Override
	public List<EmployeeResponseData> getEmployeeByFirstAndLastName(String firstName, String lastName) {
		List<EmployeeResponseData> listEmployee = new ArrayList<>();

		List<EmployeeDto> listEmployeeDto = employeeDao.getEmployeeByFirstAndLastName(firstName, lastName);

		for (EmployeeDto employeeDto : listEmployeeDto) {

			EmployeeResponseData data = new EmployeeResponseData();

			EmployeeResponseModel response = new EmployeeResponseModel();

			BeanUtils.copyProperties(employeeDto, response);

			response.setEmployeeName(employeeDto.getFirstName() + " " + employeeDto.getLastName());

			data.setDatails(response);

			listEmployee.add(data);
		}

		return listEmployee;
	}

}
