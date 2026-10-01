package com.professional.apigateway.userservice.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/stats")
    public Map<String, Object> stats() {
        return Map.of(
                "totalUsers", 2,
                "message", "Only ADMIN role can see this");
    }
}
