package tn.esprit.customerservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import tn.esprit.customerservice.dao.CustomerRepository;
import tn.esprit.customerservice.entities.Customer;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository , RepositoryRestConfiguration restConfiguration)
	{
		restConfiguration.exposeIdsFor(Customer.class);
		return args -> {
			customerRepository.save(new Customer(null,"Bayram FRADJ","Bayram.fraj@esprit.tn"));
			customerRepository.save(new Customer(null,"Bassem FRADJ","Bassem.fraj@esprit.tn"));

			customerRepository.findAll().forEach(System.out::println);
		};
	}
}
