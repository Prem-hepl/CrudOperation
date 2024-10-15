package com.Spring_Crud.CrudOperation.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Document(collection = "student")
public class Student {
    @Id
    private String id;
    private String name;
    private String mobile;
    private String city;

    @DocumentReference
    List<StudentMark> studentMarks;

}
