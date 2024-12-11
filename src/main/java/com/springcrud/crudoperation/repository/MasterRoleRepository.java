package com.springcrud.crudoperation.repository;

import com.springcrud.crudoperation.model.MasterRole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MasterRoleRepository extends MongoRepository<MasterRole,String> {

    Optional<MasterRole> findByRoleName(String roleName);
}
