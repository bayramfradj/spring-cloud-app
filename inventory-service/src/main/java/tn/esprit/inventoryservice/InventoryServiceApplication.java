package tn.esprit.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import tn.esprit.inventoryservice.dao.ProductRepository;
import tn.esprit.inventoryservice.entities.Product;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration)
	{
		restConfiguration.exposeIdsFor(Product.class);
		return args -> {
			productRepository.save(new Product(null,"Product 1",20.0));
			productRepository.save(new Product(null,"Product 2",40.0));
			productRepository.save(new Product(null,"Product 3",30.0));

			productRepository.findAll().forEach(System.out::println);
		};
	}

}
