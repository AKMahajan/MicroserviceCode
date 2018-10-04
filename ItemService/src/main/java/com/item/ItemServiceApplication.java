package com.item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.item.domain.Item;
import com.item.repository.ItemRepository;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner addPersons(ItemRepository repo){
		return args -> {

			
			repo.save(new Item("chair","woodenChair",350));
			repo.save(new Item("table","wooden table",550));
			repo.save(new Item("pen","Pen",30));
			repo.save(new Item("notepad","woodenChair",50));
			
		};
	}
}
