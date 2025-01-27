package com.springcrud.crudoperation.repository;

import com.springcrud.crudoperation.model.email.Email;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepository extends MongoRepository<Email,String> {
}
