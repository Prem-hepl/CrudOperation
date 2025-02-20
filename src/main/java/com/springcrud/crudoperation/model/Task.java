package com.springcrud.crudoperation.model;

import com.springcrud.crudoperation.response.UserResponseDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "task")
public class Task {
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
    @DocumentReference(lazy = true)
    private Milestone milestone;
    @DocumentReference(lazy = true)
    private List<SubTask>subTasks;
}
