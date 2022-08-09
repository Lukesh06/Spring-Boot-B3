package com.ekalavya.employee.address.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekalavya.employee.address.dao.EmployeeAddressDao;
import com.ekalavya.employee.address.dto.EmployeeAddressDto;
import com.ekalavya.employee.address.enums.AddressType;
import com.ekalavya.employee.address.model.AddressModel;

@Service
public class EmployeeAddressServiceImpl implements EmployeeAddressService {

	@Autowired
	private EmployeeAddressDao employeeAddressDao;

	@Override
	public List<AddressModel> getEmployeeAddress(Integer employeeId) {

		List<AddressModel> listAddressModel = null;

		List<EmployeeAddressDto> listEmployeeAddressDto = employeeAddressDao.getEmployeeAddress(employeeId);
		
		if(listEmployeeAddressDto!=null && !listEmployeeAddressDto.isEmpty()) {
			listAddressModel = new ArrayList<AddressModel>();
			for(EmployeeAddressDto employeeAddressDto:listEmployeeAddressDto) {
				AddressModel addressModel = new AddressModel();
				BeanUtils.copyProperties(employeeAddressDto, addressModel);
				addressModel.setType(AddressType.valueOf(employeeAddressDto.getAddressType()));
				listAddressModel.add(addressModel);
			}
		}

		return listAddressModel;
	}

}
