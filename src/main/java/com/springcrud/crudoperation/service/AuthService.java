package com.springcrud.crudoperation.service;

import com.springcrud.crudoperation.request.LoginRequest;
import com.springcrud.crudoperation.response.LoginResponse;
import com.springcrud.crudoperation.response.SuccessResponse;
import jakarta.servlet.http.HttpSession;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest, HttpSession session);
}
