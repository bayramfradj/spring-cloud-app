package tn.esprit.customerservice.projection;

import org.springframework.data.rest.core.config.Projection;
import tn.esprit.customerservice.entities.Customer;

@Projection(name = "fullCustomer",types = Customer.class)
public interface CustomerProjection {
    public Integer getId();
    public String getName();
    public String getEmail();
}
