package com.item.controller;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.item.domain.Item;
import com.item.service.ItemService;

@RestController
@RequestMapping("/service2")
public class ItemController {
	DBInfo dbinfo;
	public ItemController(DBInfo dbinfo){
		this.dbinfo = dbinfo;
	}

	
	@RequestMapping("/dbinfo")
	public DBInfo getInfo(){
		return this.dbinfo;
	}
	@Autowired
	ItemService itemService;
	
	@GetMapping("/items")
	public List<Item> getItems(){
		return itemService.getItems();
		
	}
	
	@GetMapping("/items/{itemName}")
	public Item getItemByName(@PathVariable String itemName){
		return itemService.getItemByName(itemName);
		
	}
	@Value("${server.port}")
	private String port;
	@GetMapping("/getPort")
	public String get(){
		return "Running on port:"+port;
		
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