package com.ekalavya.employee.exception;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ekalavya.employee.model.ErrorResponse;
import com.ekalavya.employee.utils.EmployeeConstants;

@ControllerAdvice
public class GlobleExceptionHandler {

	@ExceptionHandler({ NullPointerException.class })
	public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException exception) {

		String errorMessage = null;

		errorMessage = exception.getMessage();

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
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleNullPointerException(Exception ex){
		String errorMessage = ex.getMessage();
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(errorMessage);

		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(EmployeeConstants.DATE_PATTERN);
		LocalDateTime now = LocalDateTime.now();

		errorResponse.setTimestamp(dtf.format(now));
		errorResponse.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		errorResponse.setErrorCode("Z");
		
		ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return response;
	}
	
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAddressNotFoundException(AddressNotFoundException ex){
		
		String errorMessage = ex.getMessage();
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorMessage(errorMessage);

		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(EmployeeConstants.DATE_PATTERN);
		LocalDateTime now = LocalDateTime.now();

		errorResponse.setTimestamp(dtf.format(now));
		errorResponse.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		errorResponse.setErrorCode("AD001");
		
		ResponseEntity<ErrorResponse> response = new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return response;
	}
}
