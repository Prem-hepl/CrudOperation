package com.springcrud.crudoperation.utils;

import com.springcrud.crudoperation.model.User;
import com.springcrud.crudoperation.repository.UserRepository;
import com.springcrud.crudoperation.request.LoginRequest;
import com.springcrud.crudoperation.response.AuthResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JwtUtils {
    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.secret}")
    private String secret;

    public AuthResponse createAccessToken(LoginRequest loginRequest, HttpSession session) {
        User user=userRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
        String token=createToken(user,session);
        return new AuthResponse(token, user.getEmail());
    }

    private String createToken(User user, HttpSession session) {
        Map<String,Object> claims=new HashMap<>();
        claims.put("userName",user.getName());
        claims.put("email",user.getEmail());
        session.setAttribute("getId",user.getId());
        return generateToken(claims,user.getEmail());
    }

    private String generateToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }
}
