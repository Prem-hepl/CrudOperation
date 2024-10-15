package com.Spring_Crud.CrudOperation.Service.ServiceImpl;

import com.Spring_Crud.CrudOperation.Dto.StudentDto;
import com.Spring_Crud.CrudOperation.Model.Student;
import com.Spring_Crud.CrudOperation.Repository.StudentRepo;
import com.Spring_Crud.CrudOperation.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepo studentRepo;
    @Override
    public void addStudent(StudentDto studentDto)
    {
        //Dto to Entity for ADD,UPDATE
        Student student= new Student();

        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setMobile(studentDto.getMobile());
        student.setCity(studentDto.getCity());
        studentRepo.save(student);

    }
    @Override
    public StudentDto getStudentById(String id) {
        Optional<Student> optional= studentRepo.findById(id);
         Student student = optional.get();

        StudentDto studentDto=new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setMobile(student.getMobile());
        studentDto.setCity(student.getCity());
        return studentDto;

    }

    @Override
    public String updateStudentById(StudentDto studentDto) {
        Optional<Student> optional=studentRepo.findById(studentDto.getId());
        Student student=optional.get();

        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setMobile(studentDto.getMobile());
        student.setCity(studentDto.getCity());
        studentRepo.save(student);
        return "Updated Successfully";
    }

    @Override
    public void deleteStudentById(String id) {
        studentRepo.deleteById(id);
    }
}
