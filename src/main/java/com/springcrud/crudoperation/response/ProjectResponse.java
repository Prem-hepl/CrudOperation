package com.springcrud.crudoperation.response;

import lombok.Data;

import java.util.List;


@Data
public class ProjectResponse {
    private String id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
    private UserResponseDto createdBy;
    private UserResponseDto updatedBy;
    private boolean isActive;
    private boolean deleteFlag;
    private List<String> milestone;
}
