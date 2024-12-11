package com.springcrud.crudoperation.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "role")
public class MasterRole {
    @Id
    private String id;
    private String roleName;
    private LocalDateTime createdAt;
    private boolean isActive;
    private boolean deleteFlag;
}
