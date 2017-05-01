package com.unibeck.services;

import com.unibeck.model.Customer;
import com.unibeck.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private CustomerRepository customerRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Customer loadUserByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username);

        if (customer == null) {
            throw new IllegalStateException("Username [" + username + "] was not found");
        }

        return customer;
    }
}
