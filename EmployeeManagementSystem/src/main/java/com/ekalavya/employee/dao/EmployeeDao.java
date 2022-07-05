package com.ekalavya.employee.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public List<EmployeeDto> getAllEmployees() {
		List<EmployeeDto> listEmployeeDto = new ArrayList<>();

		List<EmployeeEntity> listEmployeeEntity = (List<EmployeeEntity>) employeeRepository.findAll();

		for (EmployeeEntity employeeEntity : listEmployeeEntity) {

			EmployeeDto employeeDto = new EmployeeDto();

			BeanUtils.copyProperties(employeeEntity, employeeDto);

			listEmployeeDto.add(employeeDto);
		}
		return listEmployeeDto;
	}

	public EmployeeDto getEmployeeDetails(int EmpId) {
		EmployeeDto employeeDto = new EmployeeDto();

		Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(EmpId);

		if (employeeEntity.isPresent()) {
			BeanUtils.copyProperties(employeeEntity.get(), employeeDto);
		} else {
			throw new RuntimeException("Data not present for given Employee Id");
		}

		return employeeDto;
	}

}
