package com.springcrud.crudoperation.repository;

import com.springcrud.crudoperation.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student,String> {
}
