package com.security.learn.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private record Product(Integer productId, String productName, Double productPrice) {
    }

    List<Product> products = new ArrayList<>(List.of(
            new Product(1, "Laptop", 999.99),
            new Product(2, "Smartphone", 699.99),
            new Product(3, "Tablet", 399.99)
    ));

    @GetMapping
    public List<Product> getProducts() {
        return products;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }
}