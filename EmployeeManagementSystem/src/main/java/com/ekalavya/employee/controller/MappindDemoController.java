package com.ekalavya.employee.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class MappindDemoController {

	@Autowired
	private Environment environment;
	
	@GetMapping("/hii/{name}/{id}")
	public String sayHi(@PathVariable String name, @PathVariable int id) {
		return "Hii " + name + " My Id is " + id;
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam String firstName, @RequestParam(required = false) String lastName,
			@RequestParam(required = false, defaultValue = "Pune") String city) {
		
		String text =  null;
		System.out.println(text.length());
		
		if (lastName == null) {
			lastName = "";
		}
		return "Hello " + firstName +" " +lastName +" City "+city;
	}
	
	@GetMapping("/status-check")
	public ResponseEntity<String> statusCheck() {
		ResponseEntity<String> response = new ResponseEntity<String>(
				"working Port::" + environment.getProperty("local.server.port"), HttpStatus.OK);

		return response;
	}

}
