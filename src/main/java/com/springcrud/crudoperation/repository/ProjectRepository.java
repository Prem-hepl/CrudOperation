package com.springcrud.crudoperation.repository;

import com.springcrud.crudoperation.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProjectRepository extends MongoRepository<Project,String> {

    Optional<Project> findByName(String name);
}
