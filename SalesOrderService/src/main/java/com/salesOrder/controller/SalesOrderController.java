package com.salesOrder.controller;


import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.salesOrder.bean.Order;
import com.salesOrder.config.RabbitListenerConfig;
import com.salesOrder.domain.Customer_SOS;
import com.salesOrder.domain.Order_Line_Item;
import com.salesOrder.repository.CustomerRepository;
import com.salesOrder.repository.OrderLineItemRepository;
import com.salesOrder.repository.SalesOrderRepository;
import com.salesOrder.service.SalesOrderService;

@RestController
@RequestMapping("/service3")
//@RibbonClient(name="item-service",configuration=SalesOrderServiceRibbonConfig.class)
public class SalesOrderController{
	@Autowired
	RestTemplate rest;
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	 
	
	@Bean
	CommandLineRunner addPersons(CustomerRepository repo){
		return args -> {

			
			repo.save(new Customer_SOS(1,"Ashwini", "Mahajan","abc@gmail.com"));
			repo.save(new Customer_SOS(2,"Ashwini1", "Mahajan1","abc1@gmail.com"));
			repo.save(new Customer_SOS(3,"Ashwini2", "Mahajan2","abc2@gmail.com"));
			repo.save(new Customer_SOS(4,"Ashwini3", "Mahajan3","abc3@gmail.com"));
			
			
		};
	}
	
	DBInfo dbinfo;
	public SalesOrderController(DBInfo dbinfo){
		this.dbinfo = dbinfo;
	}

	@GetMapping("/test")
	public String test() {
		return "test successful";
	}
	
	@RequestMapping("/dbinfo")
	public DBInfo getInfo(){
		return this.dbinfo;
	}
	@Autowired
	OrderLineItemRepository orderLineItemRepository;
	
	@Autowired
	SalesOrderService salesOrderService;
	
	@Autowired
	SalesOrderRepository salesrepository;
	
	@PostMapping("/orders")	
	public String addOrder(@RequestBody Order order) throws Exception {
			String s=salesOrderService.add(order);
			return s;
	}
	
	
	@GetMapping("/orders1")
	public List<Order_Line_Item> getOrde() {
		return (List<Order_Line_Item>)orderLineItemRepository.findAll();
		
	}
	
	
	@RabbitListener(queues= RabbitListenerConfig.QUEUE_ORDERS)
	public void receiveMessage(Customer_SOS cust) {
		System.out.println("customer received with id "+cust.getId()+" and name "+cust.getFirst_name());
		salesOrderService.addCustomer(cust);
	}
	

}

@Component
class DBInfo {
	private String url;

	public DBInfo(DataSource dataSource) throws SQLException{
		this.url = dataSource.getConnection().getMetaData().getURL();
	}

	public String getUrl() {
		return url;
	}
}
