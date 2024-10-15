package com.Spring_Crud.CrudOperation.Service;

import com.Spring_Crud.CrudOperation.Dto.StudentDto;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    void addStudent(StudentDto studentDto);

    StudentDto getStudentById(String id);

    String updateStudentById(StudentDto studentDto);

    void deleteStudentById(String id);
}
