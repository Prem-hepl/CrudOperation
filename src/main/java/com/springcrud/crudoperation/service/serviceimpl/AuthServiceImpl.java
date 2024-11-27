package com.springcrud.crudoperation.service.serviceimpl;

import com.springcrud.crudoperation.model.User;
import com.springcrud.crudoperation.repository.UserRepository;
import com.springcrud.crudoperation.request.LoginRequest;
import com.springcrud.crudoperation.response.AuthResponse;
import com.springcrud.crudoperation.response.LoginResponse;
import com.springcrud.crudoperation.response.UserResponseDto;
import com.springcrud.crudoperation.service.AuthService;
import com.springcrud.crudoperation.utils.JwtUtils;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LoginResponse login(LoginRequest loginRequest, HttpSession session) {
        AuthResponse authResponse=jwtUtils.createAccessToken(loginRequest,session);
        User user=userRepository.findByEmail(authResponse.getUserName()).orElseThrow();
       return new LoginResponse(modelMapper.map(user, UserResponseDto.class), authResponse.getToken(), authResponse.getRefreshToken());
    }
}
