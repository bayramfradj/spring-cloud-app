package tn.esprit.inventoryservice.projection;

import org.springframework.data.rest.core.config.Projection;
import tn.esprit.inventoryservice.entities.Product;

@Projection(name = "fullProduct",types = Product.class)
public interface ProductProjection {
    public Integer getId();
    public String getName();
    public Double getPrice();
}
