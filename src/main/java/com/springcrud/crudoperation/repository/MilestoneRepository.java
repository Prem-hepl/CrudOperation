package com.springcrud.crudoperation.repository;

import com.springcrud.crudoperation.dto.MilestoneResponse;
import com.springcrud.crudoperation.model.Milestone;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MilestoneRepository extends MongoRepository<Milestone,String> {
    Optional<Milestone> findByName(String name);

    List<Milestone> findByProject(ObjectId objectId);


    List<MilestoneResponse> findAllMilestoneByProjectId(String projectId);
}
