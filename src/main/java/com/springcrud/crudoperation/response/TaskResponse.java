package com.springcrud.crudoperation.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private String id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
    private UserResponseDto createdBy;
    private UserResponseDto updatedBy;
    private boolean isActive;
    private boolean deleteFlag;
    private String milestoneId;
}
