package com.springcrud.crudoperation.repository;

import com.springcrud.crudoperation.model.Milestone;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MilestoneRepository extends MongoRepository<Milestone,String> {
    Optional<Milestone> findByName(String name);
}
