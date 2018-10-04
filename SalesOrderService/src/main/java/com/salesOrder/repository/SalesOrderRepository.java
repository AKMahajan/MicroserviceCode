package com.salesOrder.repository;

import org.springframework.data.repository.CrudRepository;

import com.salesOrder.domain.Sales_Order;

public interface SalesOrderRepository extends CrudRepository<Sales_Order,Integer>{

}
