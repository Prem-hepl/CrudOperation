package com.springcrud.crudoperation.dto;

import com.springcrud.crudoperation.model.Milestone;
import com.springcrud.crudoperation.response.UserResponseDto;
import lombok.Data;

import java.util.List;


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
    private List<String> subTask;
}
