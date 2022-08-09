package com.ekalavya.employee.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ekalavya.employee.model.EmployeeAddress;

@FeignClient(name = "address-api")
public interface AdressFeignClient {

	@GetMapping("/address/{id}")
	public ResponseEntity<List<EmployeeAddress>> getEmployeeAddress(@PathVariable Integer id);
	
	@PostMapping("/address/create")
	public ResponseEntity<String> createAddress(@RequestBody EmployeeAddress addressModel,
			@RequestHeader LinkedMultiValueMap<String, String> headers) ;

}
