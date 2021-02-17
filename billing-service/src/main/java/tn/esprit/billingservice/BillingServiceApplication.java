package tn.esprit.billingservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tn.esprit.billingservice.dao.BillRepository;
import tn.esprit.billingservice.dao.ProductItemRepository;
import tn.esprit.billingservice.entities.Bill;
import tn.esprit.billingservice.entities.ProductItem;
import tn.esprit.billingservice.service.InventoryServiceClient;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository, InventoryServiceClient inventoryServiceClient)
	{
		return args -> {
			Bill b = billRepository.save(new Bill(null,new Date(),1,null,null));
			productItemRepository.save(new ProductItem(null,1,null,20.0,2,b));
			productItemRepository.save(new ProductItem(null,2,null,40.0,2,b));
			productItemRepository.save(new ProductItem(null,3,null,30.0,2,b));

			System.out.println("*************************************");
			System.out.println(inventoryServiceClient.findProductById(1).toString());

		};
	}
}
