package com.springcrud.crudoperation.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "StudentMark")
public class StudentMark {
    @Id
    private String id;
    private String Exam_name;
    private String english;
    private String tamil;
}
