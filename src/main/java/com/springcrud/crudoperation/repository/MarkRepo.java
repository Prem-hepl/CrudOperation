package com.springcrud.crudoperation.repository;

import com.springcrud.crudoperation.model.StudentMark;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarkRepo extends MongoRepository<StudentMark,String> {
}
