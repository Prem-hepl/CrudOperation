package com.springcrud.crudoperation.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String refreshToken;
    private String userName;


    public AuthResponse(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }

}
