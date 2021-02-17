package tn.esprit.billingservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import tn.esprit.billingservice.entities.ProductItem;
@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem,Integer> {
}
