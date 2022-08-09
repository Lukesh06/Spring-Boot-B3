package com.ekalavya.employee.address.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ekalavya.employee.address.dto.EmployeeAddressDto;
import com.ekalavya.employee.address.entity.EmployeeAddressEntity;
import com.ekalavya.employee.address.repository.EmployeeAddressRepository;

@Component
public class EmployeeAddressDao {

	@Autowired
	private EmployeeAddressRepository employeeAddressRepository;

	public List<EmployeeAddressDto> getEmployeeAddress(Integer employeeId) {

		List<EmployeeAddressDto> listEmployeeAddressDto = null;

		List<EmployeeAddressEntity> listEmployeeAddressEntity = employeeAddressRepository.findByEmployeeId(employeeId);

		if (listEmployeeAddressEntity != null && !listEmployeeAddressEntity.isEmpty()) {
			listEmployeeAddressDto = new ArrayList<EmployeeAddressDto>();

			for (EmployeeAddressEntity entity : listEmployeeAddressEntity) {
				EmployeeAddressDto employeeAddressDto = new EmployeeAddressDto();
				BeanUtils.copyProperties(entity, employeeAddressDto);
				listEmployeeAddressDto.add(employeeAddressDto);
			}

		}
		return listEmployeeAddressDto;
	}

}
