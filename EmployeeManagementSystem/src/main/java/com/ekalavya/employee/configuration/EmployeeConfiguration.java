package com.ekalavya.employee.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EmployeeConfiguration {

	//@Bean
	//public EmployeeService getEmployeeService() {
		//return new EmployeeServiceImpl();
	//}
	
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
