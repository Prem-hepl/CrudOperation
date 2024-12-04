package com.springcrud.crudoperation.repository;

import com.springcrud.crudoperation.dto.TaskResponse;
import com.springcrud.crudoperation.model.Project;
import com.springcrud.crudoperation.model.SubTask;
import com.springcrud.crudoperation.model.Task;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends MongoRepository<Project,String> {
    @Aggregation(pipeline = {
            "{$match: {_id:ObjectId(?0)}}",
            "{$lookup: {from: 'milestone', localField: 'milestone', foreignField: '_id', as: 'milestone'}}",
            "{$unwind: {path: '$milestone'}}",
            "{$lookup: {from: 'task', localField: 'milestone.tasks', foreignField: '_id', as: 'task'}}",
            "{$unwind: {path: '$task'}}",
            "{$match: {'task._id':ObjectId(?1)}}",
            "{$lookup: {from: 'subTask', localField: 'task.subTasks', foreignField: '_id', as: 'subTask'}}",
            "{$unwind: {path: '$subTask'}}",
//            "{ $project: { combinedName: { $concat: ['$milestone.name', ' ❤️❤️❤️ ', '$subTask.name'] } } }"
            "{$project: { _id:0, name:'$subTask.name'}}"

    })
    List<Object> findByProjectAndTaskId(String projectId, String taskId);


    Optional<Project> findByName(String name);
}
