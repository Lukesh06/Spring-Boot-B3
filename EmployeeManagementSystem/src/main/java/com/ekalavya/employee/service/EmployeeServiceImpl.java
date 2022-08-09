package com.ekalavya.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ekalavya.employee.dao.EmployeeDao;
import com.ekalavya.employee.dto.EmployeeDto;
import com.ekalavya.employee.exception.AddressNotFoundException;
import com.ekalavya.employee.model.EmployeeAddress;
import com.ekalavya.employee.model.EmployeeRequestModel;
import com.ekalavya.employee.model.EmployeeResponseData;
import com.ekalavya.employee.model.EmployeeResponseModel;
import com.ekalavya.employee.utils.EmployeeConstants;

@Service
@Primary
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Environment env;
	
	@Autowired
	AdressFeignClient adressFeignClient;

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

			List<EmployeeAddress> addressList = getEployeeAddress(employeeDto.getEmployeeId());

			response.setAddresses(addressList);

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
		
		saveAddress();

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

	public List<EmployeeAddress> getEployeeAddressRestTemplate(Integer empId) {

		String url = env.getProperty("address-service-url") + EmployeeConstants.ADDRESS_URL_RESOURCE + empId;
		List<EmployeeAddress> employeeAddressList = null;
		try {
			ResponseEntity<List<EmployeeAddress>> responseAddressList = restTemplate.exchange(url, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<EmployeeAddress>>() {
					});

			employeeAddressList = responseAddressList.getBody();
		} catch (Exception ex) {

			throw new AddressNotFoundException(EmployeeConstants.NOT_ABLE_TO_GET_ADDRESS);
		}

		return employeeAddressList;

	}

	public void saveAddress() {

		EmployeeAddress employeeAddress = new EmployeeAddress("Near Stadium", "Gwalior", "Madhya Pradesh", 425874,
				"PRESENT");

		String url = env.getProperty("address-service-url") + EmployeeConstants.CREATE_ADDRESS_URL_RESOURCE;

		LinkedMultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();

		headers.add("CorelationId", "123458758");
		headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.add("Business-Unit", "ADE");
		headers.add("Product-Type", "Loan");

		HttpEntity<EmployeeAddress> requestEntity = new HttpEntity<EmployeeAddress>(employeeAddress, headers);

		ResponseEntity<String> addressResponse = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		
		System.out.println("Received Response::"+addressResponse);
	}
	
	public List<EmployeeAddress> getEployeeAddress(Integer empId) {

		List<EmployeeAddress> employeeAddressList = null;
		try {
			ResponseEntity<List<EmployeeAddress>> responseAddressList = adressFeignClient.getEmployeeAddress(empId);

			employeeAddressList = responseAddressList.getBody();
		} catch (Exception ex) {

			throw new AddressNotFoundException(EmployeeConstants.NOT_ABLE_TO_GET_ADDRESS);
		}

		return employeeAddressList;

	}

}
