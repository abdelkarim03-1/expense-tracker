package com.expensetracker.controller;

import com.expensetracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody Map<String, String> request) {
        return authService.registerUser(request.get("username"), request.get("password"));
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> request) {
        return authService.authenticate(request.get("username"), request.get("password"));
    }
}
