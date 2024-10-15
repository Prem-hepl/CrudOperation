package com.Spring_Crud.CrudOperation.Repository;

import com.Spring_Crud.CrudOperation.Model.StudentMark;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MarkRepo extends MongoRepository<StudentMark,String> {
}
