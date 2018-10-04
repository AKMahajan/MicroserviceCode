package com.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.customer.domain.Customer;
import com.customer.repository.CustomerRepository;


@SpringBootApplication
//@EnableEurekaClient
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner addPersons(CustomerRepository repo){
		return args -> {

			
			repo.save(new Customer("Ashwini", "Mahajan","abc@gmail.com"));
			repo.save(new Customer("Ashwini1", "Mahajan1","abc1@gmail.com"));
			repo.save(new Customer("Ashwini2", "Mahajan2","abc2@gmail.com"));
			repo.save(new Customer("Ashwini3", "Mahajan3","abc3@gmail.com"));
			
			
		};
	}
}
