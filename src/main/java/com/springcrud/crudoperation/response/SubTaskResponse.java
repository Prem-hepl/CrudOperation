package com.springcrud.crudoperation.response;

import lombok.Data;

@Data
public class SubTaskResponse {
    private String id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
    private UserResponseDto createdBy;
    private UserResponseDto updatedBy;
    private boolean isActive;
    private boolean deleteFlag;
    private String taskId;
}
