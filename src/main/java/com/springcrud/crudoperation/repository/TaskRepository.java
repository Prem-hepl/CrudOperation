package com.springcrud.crudoperation.repository;

import com.springcrud.crudoperation.model.Task;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task,String> {
    Optional<Task> findByName(String name);

    @Aggregation(pipeline = {
            "{$lookup: { from: 'milestone', localField: 'milestone', foreignField: '_id', as: 'milestone' }}",
            "{$unwind: '$milestone'}",
            "{$lookup: { from: 'project', localField: 'milestone.project', foreignField: '_id', as: 'project'}}",
            "{$unwind: '$project'}",
            "{$match: { 'project._id': ObjectId(?0)}}"
    })
    List<Task> findByProjectId(String projectId);

}
