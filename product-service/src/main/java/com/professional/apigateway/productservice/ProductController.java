package com.professional.apigateway.productservice;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id) {
        if (id == 10) {
            return ResponseEntity.ok(new ProductResponse(10L, "Laptop", new BigDecimal("8500.00"), "IN_STOCK"));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<ProductResponse> getProducts() {
        return List.of(
                new ProductResponse(10L, "Laptop", new BigDecimal("8500.00"), "IN_STOCK"),
                new ProductResponse(11L, "Smartphone", new BigDecimal("4000.00"), "IN_STOCK"),
                new ProductResponse(12L, "Headphones", new BigDecimal("600.00"), "OUT_OF_STOCK"));
    }

    public record ProductResponse(Long id, String name, BigDecimal price, String status) {
    }
}
