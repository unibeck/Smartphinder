package com.unibeck.controllers;

import com.unibeck.model.Customer;
import com.unibeck.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login")
    public ResponseEntity<Customer> signIn(@RequestHeader String username, @RequestHeader String password) {
        if (username == null || password == null) {
            return new ResponseEntity<>((Customer) null, HttpStatus.FORBIDDEN);
        }

        Customer customer = userService.loadUserByUsername(username);

        if (customer == null) {
            throw new IllegalStateException("Could not find customer [" + username + "]");
        }

        if (userService.passwordEncoder().matches(password, customer.getPassword())) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>((Customer) null, HttpStatus.UNAUTHORIZED);
        }
    }
}
