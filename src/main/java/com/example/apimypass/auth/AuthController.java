package com.example.apimypass.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.apimypass.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@RestController
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials) {
        var user = userRepository.findByUsername(credentials.username())
                .orElseThrow(()-> new RuntimeException("Access Denied"));

        if (!passwordEncoder.matches(credentials.password(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");

        }
        var expiresAt = LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.ofHours(-3));
        Algorithm algorithm = Algorithm.HMAC256("assinatura");
        String token = JWT.create()
                .withIssuer("mypass")
                .withSubject(credentials.username())
                .withClaim("role", "admin")
                .withExpiresAt(expiresAt)
                .sign(algorithm);

        return new Token(token);
    }

}
