package com.springcrud.crudoperation.repository;

import com.springcrud.crudoperation.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

   Optional<User> findByEmail(String email);

}
