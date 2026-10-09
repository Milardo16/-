package com.example.shop.config;

import com.example.shop.model.*;
import com.example.shop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           ProductRepository productRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            userRepository.save(new User("admin",
                    passwordEncoder.encode("admin123"), Role.ADMIN));
            userRepository.save(new User("user",
                    passwordEncoder.encode("user123"), Role.USER));
        }
        if (productRepository.count() == 0) {
            productRepository.save(new Product("Ноутбук", "Игровой ноутбук",
                    new BigDecimal("75000"), 10));
            productRepository.save(new Product("Мышь", "Беспроводная мышь",
                    new BigDecimal("1500"), 50));
        }
    }
}