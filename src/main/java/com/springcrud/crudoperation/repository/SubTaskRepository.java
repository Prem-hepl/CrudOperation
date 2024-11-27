package com.springcrud.crudoperation.repository;

import com.springcrud.crudoperation.model.SubTask;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SubTaskRepository extends MongoRepository<SubTask,String> {
    Optional<SubTask> findByName(String name);

    List<SubTask> findByTask(ObjectId id);
}
