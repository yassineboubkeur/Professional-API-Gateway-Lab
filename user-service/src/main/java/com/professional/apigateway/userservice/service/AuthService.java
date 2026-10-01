package com.professional.apigateway.userservice.service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;

import com.professional.apigateway.userservice.config.JwtProperties;

@Service
public class AuthService {

    private final JwtEncoder jwtEncoder;
    private final JwtProperties jwtProperties;

    public AuthService(JwtEncoder jwtEncoder, JwtProperties jwtProperties) {
        this.jwtEncoder = jwtEncoder;
        this.jwtProperties = jwtProperties;
    }

    public record UserAccount(String username, String password, Long id, String role) {
    }

    private static final Map<String, UserAccount> USERS = Map.of(
            "youssef", new UserAccount("youssef", "password123", 1L, "USER"),
            "sara", new UserAccount("sara", "admin123", 2L, "ADMIN"));

    public Optional<String> login(String username, String password) {
        UserAccount account = USERS.get(username);
        if (account == null || !account.password().equals(password)) {
            return Optional.empty();
        }
        return Optional.of(generateToken(account));
    }

    private String generateToken(UserAccount account) {
        Instant now = Instant.now();
        Instant expiresAt = now.plus(jwtProperties.expiration());

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtProperties.issuer())
                .issuedAt(now)
                .expiresAt(expiresAt)
                .subject(account.username())
                .claim("userId", account.id())
                .claim("role", account.role())
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }
}
