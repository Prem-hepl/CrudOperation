package com.springcrud.crudoperation.repository;

import com.springcrud.crudoperation.model.template.ProjectTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectTemplateRepository extends MongoRepository<ProjectTemplate,String> {
}
