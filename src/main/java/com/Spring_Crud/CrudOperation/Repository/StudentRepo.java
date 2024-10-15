package com.Spring_Crud.CrudOperation.Repository;

import com.Spring_Crud.CrudOperation.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student,String> {
}
