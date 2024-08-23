package com.example.apimypass.password;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordService {

    @Autowired
    PasswordRepository passwordRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Password> findAll() {
        return passwordRepository.findAll();
    }
    public Password create(Password password) {
        password.setPassword(passwordEncoder.encode(password.getPassword()));
        return passwordRepository.save(password);
    }
}
