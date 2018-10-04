package com.customer.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.customer.domain.Customer;
@Service
public interface CustomerService {
	public List<Customer>	 getCustomer();
	public Customer addCustomer(Customer customer);
	
}
