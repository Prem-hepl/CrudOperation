package com.springcrud.crudoperation.response;

import lombok.Data;

@Data
public class UserResponseDto {
    private String id;
    private String name;
    private String email;
    private String createdAt;
    private String updatedAt;
    private boolean isActive;
    private boolean deleteFlag;
}
