package tn.esprit.billingservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.billingservice.dao.BillRepository;
import tn.esprit.billingservice.dao.ProductItemRepository;
import tn.esprit.billingservice.entities.Bill;
import tn.esprit.billingservice.entities.Product;
import tn.esprit.billingservice.entities.ProductItem;
import tn.esprit.billingservice.service.CustomerServiceClient;
import tn.esprit.billingservice.service.InventoryServiceClient;

import java.util.List;

@RestController
public class BillController {

    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerServiceClient customerServiceClient;
    private InventoryServiceClient inventoryServiceClient;


    public BillController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerServiceClient customerServiceClient, InventoryServiceClient inventoryServiceClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerServiceClient = customerServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    @GetMapping(path = "/bills")
    public ResponseEntity<List<Bill>> getBills(){
        List<Bill> bills = billRepository.findAll();

        bills.forEach(bill -> {
            bill.setCustomer(customerServiceClient.findCustomerById(bill.getCustomerId()));
            bill.getProductItems().forEach(productItem -> {
                productItem.setProduct(inventoryServiceClient.findProductById(productItem.getProductId()));
            });
        });

        return  ResponseEntity.ok(bills);
    }

    @GetMapping(path = "/bills/{id}")
    public ResponseEntity<Bill> getBillsById(@PathVariable("id") int id){
            Bill bill = billRepository.findById(id);
            bill.setCustomer(customerServiceClient.findCustomerById(bill.getCustomerId()));
            bill.getProductItems().forEach(productItem -> {
                productItem.setProduct(inventoryServiceClient.findProductById(productItem.getProductId()));
            });

            return  ResponseEntity.ok(bill);
    }

    @PostMapping(path = "bills")
    public ResponseEntity<Bill> save(@RequestBody Bill bill)
    {
        bill = billRepository.save(bill);
        return ResponseEntity.status(HttpStatus.CREATED).body(bill);
    }

    @PostMapping(path = "bills/{id}")
    public ResponseEntity<ProductItem> addProductToBill(@PathVariable("id") int id,@RequestBody ProductItem productItem)
    {
        Bill bill = billRepository.findById(id);
        Product product = inventoryServiceClient.findProductById(productItem.getProductId());
        productItem.setProduct(product);
        productItem.setPrice(product.getPrice()*productItem.getQuantity());
        productItem.setBill(bill);
        productItem = productItemRepository.save(productItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(productItem);
    }


}