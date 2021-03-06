package com.customer.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.config.RabbitConfig;
import com.customer.domain.Customer;
import com.customer.service.CustomerService;

@RestController
@RequestMapping("/service1")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	private final RabbitTemplate template;
	
	@Autowired
	public CustomerController(RabbitTemplate template) {
		this.template = template;
	}
	

	@GetMapping("/customers")
	public List<Customer> getCustomer(){
		return customerService.getCustomer();
	}
	
	@PostMapping("/customer")
	public int addCustomer(@RequestBody Customer customer){
		Customer cust= customerService.addCustomer(customer);	
		template.convertAndSend(RabbitConfig.QUEUE_ORDERS,cust);
		return cust.getId();
	}
}
