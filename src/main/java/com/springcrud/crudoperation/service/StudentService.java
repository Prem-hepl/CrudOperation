package com.springcrud.crudoperation.service;

import com.springcrud.crudoperation.dto.StudentDto;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    void addStudent(StudentDto studentDto);

    StudentDto getStudentById(String id);

    String updateStudentById(StudentDto studentDto);

    void deleteStudentById(String id);
}
