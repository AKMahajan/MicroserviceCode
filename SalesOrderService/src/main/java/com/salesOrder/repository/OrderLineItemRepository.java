package com.salesOrder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.salesOrder.domain.Order_Line_Item;
@Repository
public interface OrderLineItemRepository extends CrudRepository<Order_Line_Item, Integer>{

}
