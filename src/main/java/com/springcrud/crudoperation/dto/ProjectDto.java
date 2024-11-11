package com.springcrud.crudoperation.dto;

import com.springcrud.crudoperation.model.Milestone;
import com.springcrud.crudoperation.response.UserResponseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDto {
    private String id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private boolean isActive;
    private boolean deleteFlag;

}
