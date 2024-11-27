package com.springcrud.crudoperation.dto.template;

import com.springcrud.crudoperation.response.UserResponseDto;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class SubTaskTemplate {
    @Id
    private String id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserResponseDto createdBy;
    private UserResponseDto updatedBy;
    private boolean isActive;
    private boolean deleteFlag;
}
