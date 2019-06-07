package com.globant.ecommerce.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.globant.ecommerce.dao.OrderDaoImpl;

@SpringBootApplication
@ComponentScan({"com.globant"})
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
		
	}

	
	  @Bean 
	  public RestTemplate restTemplate() 
	  {
		  return new RestTemplate(); 
	  }
	 
}
