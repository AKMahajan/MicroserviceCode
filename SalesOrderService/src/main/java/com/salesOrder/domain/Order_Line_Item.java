package com.salesOrder.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Order_Line_Item {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String item_name;
	private int quantity;
	@ManyToOne(cascade=CascadeType.ALL)
	private Sales_Order salesOrder;
	
	
	
	public Order_Line_Item() {
		
	}
	public Order_Line_Item(String item_name, int quantity, Sales_Order order_id) {
	
		this.item_name = item_name;
		this.quantity = quantity;
		this.salesOrder = order_id;
	}
	public Sales_Order getSalesOrder() {
		return salesOrder;
	}
	public void setSalesOrder(Sales_Order salesOrder) {
		this.salesOrder = salesOrder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
