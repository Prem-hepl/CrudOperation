package com.springcrud.crudoperation.dto;

import lombok.Data;

@Data
public class UpdatePasswordDto {
    private String email;
    private String oldPassword;
    private String newPassword;
}
