package com.springcrud.crudoperation.response;

import lombok.Data;

@Data
public class LoginResponse {
    private UserResponseDto userDto;
    private String token;
    private String refreshToken;

    public LoginResponse(UserResponseDto userDto, String token, String refreshToken) {
        this.userDto = userDto;
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
