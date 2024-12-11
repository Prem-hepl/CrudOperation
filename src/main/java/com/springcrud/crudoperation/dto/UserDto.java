package com.springcrud.crudoperation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String applicationRole;
    private boolean isActive;
    private boolean deleteFlag;
}
