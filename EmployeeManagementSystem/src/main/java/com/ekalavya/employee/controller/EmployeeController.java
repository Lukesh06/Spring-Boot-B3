package com.ekalavya.employee.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekalavya.employee.model.Employee;
import com.ekalavya.employee.model.EmployeeRequestModel;
import com.ekalavya.employee.model.EmployeeResponseData;
import com.ekalavya.employee.model.ErrorResponse;
import com.ekalavya.employee.service.EmployeeService;
import com.ekalavya.employee.utils.EmployeeConstants;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	

	@GetMapping(path = "/details", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Employee> getEmployee() {
		
		Employee employee = employeeService.getEmployee();
		
		ResponseEntity<Employee> response = new ResponseEntity<Employee>(employee, HttpStatus.OK);
		return response;
	}

	@GetMapping(path = "/all", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Employee>> getAllEmployees() {

		List<Employee> listEmployee = employeeService.getAllEmployees();

		ResponseEntity<List<Employee>> response = new ResponseEntity<List<Employee>>(listEmployee, HttpStatus.ACCEPTED);

		return response;

	}

	@PostMapping(path = "/save", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<EmployeeResponseData> createEmployee(@Valid @RequestBody EmployeeRequestModel request) {

		EmployeeResponseData data = employeeService.saveEmployee(request);

		ResponseEntity<EmployeeResponseData> responseEntity = new ResponseEntity<EmployeeResponseData>(data,
				HttpStatus.OK);

		return responseEntity;

	}

	
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception) {

		String errorMessage = null;

		MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;
		errorMessage = methodArgumentNotValidException.getAllErrors().get(0).getDefaultMessage();

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(errorMessage);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(EmployeeConstants.DATE_PATTERN);
		LocalDateTime now = LocalDateTime.now();

		
		errorResponse.setTimestamp(dtf.format(now));
		errorResponse.setHttpStatusCode(HttpStatus.BAD_REQUEST.toString());
		errorResponse.setErrorCode("E");

		ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(errorResponse,
				HttpStatus.BAD_REQUEST);

		return response;

	}

}
