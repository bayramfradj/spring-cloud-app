package tn.esprit.billingservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import tn.esprit.billingservice.entities.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {
    public Bill findById(int id);
}
