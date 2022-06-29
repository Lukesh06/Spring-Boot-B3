package com.ekalavya.employee.dao;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ekalavya.employee.Repository.EmployeeRepository;
import com.ekalavya.employee.dto.EmployeeDto;
import com.ekalavya.employee.entity.EmployeeEntity;

@Component
public class EmployeeDao {

	@Autowired
	EmployeeRepository employeeRepository;

	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

		EmployeeEntity employeeEntity = new EmployeeEntity();

		BeanUtils.copyProperties(employeeDto, employeeEntity);

		employeeEntity = employeeRepository.save(employeeEntity);

		BeanUtils.copyProperties(employeeEntity, employeeDto);

		return employeeDto;

	}

}
