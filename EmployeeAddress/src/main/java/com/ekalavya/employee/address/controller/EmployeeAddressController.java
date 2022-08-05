package com.ekalavya.employee.address.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.ekalavya.employee.address.enums.AddressType;
import com.ekalavya.employee.address.model.AddressModel;

@RestController
@RequestMapping("/address")
public class EmployeeAddressController {

	@GetMapping("/{id}")
	public ResponseEntity<List<AddressModel>> getEmployeeAddress(@PathVariable Integer id) {

		System.out.println("Employee Id:::" + id);

		AddressModel addressModel1 = new AddressModel("Sarafa Bajar", "Indore", "Madhya Pradesh", 425874,
				AddressType.PERMANENT);
		AddressModel addressModel2 = new AddressModel("Station Road", "Bhopal", "Madhya Pradesh", 478475,
				AddressType.PRESENT);

		List<AddressModel> addressModelList = new ArrayList<>();
		addressModelList.add(addressModel1);
		addressModelList.add(addressModel2);

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
