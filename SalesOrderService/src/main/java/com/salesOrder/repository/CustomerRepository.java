package com.salesOrder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.salesOrder.domain.Customer_SOS;
@Repository
public interface CustomerRepository extends CrudRepository<Customer_SOS, Integer>{


}
