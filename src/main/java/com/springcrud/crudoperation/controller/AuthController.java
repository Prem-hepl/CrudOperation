package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.request.LoginRequest;
import com.springcrud.crudoperation.response.LoginResponse;
import com.springcrud.crudoperation.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpSession session){
        return authService.login(loginRequest, session);
    }
}
