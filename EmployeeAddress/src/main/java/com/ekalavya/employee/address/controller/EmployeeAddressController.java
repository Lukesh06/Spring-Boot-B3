package com.ekalavya.employee.address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekalavya.employee.address.model.AddressModel;
import com.ekalavya.employee.address.service.EmployeeAddressService;

@RestController
@RequestMapping("/address")
public class EmployeeAddressController {

	@Autowired
	private EmployeeAddressService employeeAddressService;
	
	@GetMapping("/{id}")
	public ResponseEntity<List<AddressModel>> getEmployeeAddress(@PathVariable Integer id) {

		System.out.println("Employee Id:::" + id);
		
		List<AddressModel> addressModelList = employeeAddressService.getEmployeeAddress(id);

		ResponseEntity<List<AddressModel>> response = new ResponseEntity<List<AddressModel>>(addressModelList,
				HttpStatus.OK);
		return response;
	}

	@PostMapping("/create")
	public ResponseEntity<String> createAddress(@RequestBody AddressModel addressModel,
			@RequestHeader LinkedMultiValueMap<String, String> headers) {
		
		System.out.println(":::::::Headers::::::::::::::");

		for (String key : headers.keySet()) {
			System.out.println("Key::" + key + " Value:::" + headers.get(key).get(0));
		}

		System.out.println(":::::::Headers End::::::::::::::");
		
		System.out.println("Request Body:::"+addressModel.toString());

		return new ResponseEntity<String>(addressModel.toString(), HttpStatus.ACCEPTED);
	}

}
