package com.professional.apigateway.userservice;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        if (id == 1) {
            return ResponseEntity.ok(new UserResponse(1L, "Youssef", "youssef@example.com", "USER"));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return List.of(
                new UserResponse(1L, "Youssef", "youssef@example.com", "USER"),
                new UserResponse(2L, "Sara", "sara@example.com", "ADMIN"));
    }

    public record UserResponse(Long id, String name, String email, String role) {
    }
}
