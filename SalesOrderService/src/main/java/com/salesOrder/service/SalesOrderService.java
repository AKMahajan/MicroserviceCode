package com.salesOrder.service;

import org.springframework.stereotype.Service;

import com.salesOrder.bean.Order;
import com.salesOrder.domain.Customer_SOS;
import com.salesOrder.exception.CustomerNotFoundException;
import com.salesOrder.exception.ItemNotFoundException;
@Service
public interface SalesOrderService {
	public String add(Order order)throws CustomerNotFoundException,ItemNotFoundException;
	public void addCustomer(Customer_SOS cust);

}
