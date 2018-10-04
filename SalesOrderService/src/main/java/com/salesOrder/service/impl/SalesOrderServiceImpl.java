package com.salesOrder.service.impl;

import java.sql.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.salesOrder.bean.Item;
import com.salesOrder.bean.Order;
import com.salesOrder.domain.Customer_SOS;
import com.salesOrder.domain.Order_Line_Item;
import com.salesOrder.domain.Sales_Order;
import com.salesOrder.exception.CustomerNotFoundException;
import com.salesOrder.exception.ItemNotFoundException;
import com.salesOrder.repository.CustomerRepository;
import com.salesOrder.repository.OrderLineItemRepository;
import com.salesOrder.service.SalesOrderService;
@Service
@EnableCircuitBreaker
public class SalesOrderServiceImpl implements SalesOrderService{
	
	@Autowired
	OrderLineItemRepository orderLineItemRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	RestTemplate restTemplate;
	

	
	@Override	
	public String  add(Order order) throws CustomerNotFoundException ,ItemNotFoundException{

		Map<String,Integer> itemMap=order.getItem();		
		if(!validateCustomer(order.getCustomerId())) {
			throw new CustomerNotFoundException("Customer not found");
		}
		
		Set<String> itemNameSet=itemMap.keySet();
		Set<String> newItemNameSet= new HashSet<>();
		
		int price=0;
		for(String name:itemNameSet) {
			
			Item item=getItem(name);
			if(item!=null) {
				//list.add(item);
				price=price+(item.getPrice()*itemMap.get(name));
				newItemNameSet.add(name);
			}else {
				throw new ItemNotFoundException("there is technical problem or the  item  is not present");
			}
		}
		Date date=new Date(order.getOrderDate().getTime());
		Sales_Order s= new Sales_Order(date,order.getCustomerId(),order.getOrderDescription(),price);
	
		for(String name:newItemNameSet) {
		Order_Line_Item o= new Order_Line_Item( name, itemMap.get(name), s);
		orderLineItemRepository.save(o);
		}
		return "";
	}
	
	
	public boolean validateCustomer(Integer id) {		
		Optional<Customer_SOS> cust=customerRepository.findById(id);
		if(cust.isPresent()) {
			return true;
		}
		return false;
	}
	
	@HystrixCommand(fallbackMethod="defaultMethod")
	public Item getItem(String itemName) {
		
		String url="http://localhost:5001/items/{itemName}";
		return restTemplate.getForObject(url, Item.class,itemName);
	}
	
	public Item defaultMethod() {
		System.out.println("#######################################################done");
		return null;
	}

	@Override
	public void addCustomer(Customer_SOS cust) {
		customerRepository.save(cust);
		
	}

}
