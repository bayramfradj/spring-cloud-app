package tn.esprit.billingservice.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Product {
    private Integer id;
    private String name;
    private Double price;
}
