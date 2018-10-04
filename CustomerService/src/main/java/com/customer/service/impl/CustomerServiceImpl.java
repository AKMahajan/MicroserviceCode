package com.customer.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.domain.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;	

	@Override
	public List<Customer> getCustomer() {
		
		return ( List<Customer>) customerRepository.findAll();
	}

	@Override
	public Customer addCustomer(Customer customer) {
		Customer cust=customerRepository.save(customer);
		return cust;
		
	}

}
