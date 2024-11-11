package com.springcrud.crudoperation.repository;

import com.springcrud.crudoperation.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task,String> {
    Optional<Task> findByName(String name);
}
