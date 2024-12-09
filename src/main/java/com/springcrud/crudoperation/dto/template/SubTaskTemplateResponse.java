package com.springcrud.crudoperation.dto.template;

import com.springcrud.crudoperation.response.UserResponseDto;
import lombok.Data;

@Data
public class SubTaskTemplateResponse {
    private String id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
    private UserResponseDto createdBy;
    private UserResponseDto updatedBy;
    private boolean isActive;
    private boolean deleteFlag;
}
