package tn.esprit.customerservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.esprit.customerservice.entities.Customer;
@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
