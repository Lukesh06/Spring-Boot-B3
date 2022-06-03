package com.ekalavya;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class User {

	public static void main(String[] args) {
	
		 ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		 
		 TravelAgent travelAgent = (TravelAgent) context.getBean("travelAgent");
	     
		 
		 travelAgent.travel();
	
	}

}
