package com.springcrud.crudoperation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MasterRoleDto {
    private String id;
    private String roleName;
    private boolean isActive;
    private boolean deleteFlag;
    private LocalDateTime createdAt;
}
