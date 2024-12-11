package com.springcrud.crudoperation.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String ogPassword;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @DocumentReference
    private MasterRole applicationRole;
    private boolean isActive;
    private boolean deleteFlag;
}
