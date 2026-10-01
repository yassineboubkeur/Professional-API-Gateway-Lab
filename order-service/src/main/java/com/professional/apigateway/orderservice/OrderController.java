package com.professional.apigateway.orderservice;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        if (id == 100) {
            return ResponseEntity.ok(new OrderResponse(100L, 1L, "COMPLETED", new BigDecimal("8500.00")));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<OrderResponse> getOrders() {
        return List.of(
                new OrderResponse(100L, 1L, "COMPLETED", new BigDecimal("8500.00")),
                new OrderResponse(101L, 2L, "PENDING", new BigDecimal("4600.00")));
    }

    public record OrderResponse(Long id, Long userId, String status, BigDecimal total) {
    }
}
