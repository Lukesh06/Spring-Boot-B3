package com.ekalavya.employee.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EmployeeAddressApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeAddressApplication.class, args);
	}

}
