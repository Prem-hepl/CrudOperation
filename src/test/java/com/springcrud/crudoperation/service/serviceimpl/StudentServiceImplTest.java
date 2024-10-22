package com.springcrud.crudoperation.service.serviceimpl;

import com.springcrud.crudoperation.dto.StudentDto;
import com.springcrud.crudoperation.model.Student;
import com.springcrud.crudoperation.repository.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {
    @Mock
    StudentRepo studentRepo;
    @InjectMocks
    StudentServiceImpl studentServiceImpl;
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
   public void addStudent() {
        StudentDto studentDto=new StudentDto();
        studentDto.setId("123");
        studentDto.setName("TestName");
        studentDto.setCity("Sample City");

        Student student=new Student();
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setCity(studentDto.getCity());
        studentServiceImpl.addStudent(studentDto);
    }

    @Test
    public void getStudentById() {

        String id="1L";
        Student  student=new Student();
        student.setId("1l");

        when(studentRepo.findById(id)).thenReturn(Optional.of(student));
        StudentDto studentDto=new StudentDto();
        studentDto.setId(student.getId());

        StudentDto result=studentServiceImpl.getStudentById(id);
        assertNotNull(result);

    }

    @Test
    void updateStudentById() {
        StudentDto studentDto=new StudentDto();
        studentDto.setId("1L");

        Student student=new Student();
        when(studentRepo.findById(studentDto.getId())).thenReturn(Optional.of(student));
        student.setId(studentDto.getId());
        student.setName(studentDto.getName());
        student.setMobile(studentDto.getMobile());
        student.setCity(studentDto.getCity());
       String response= studentServiceImpl.updateStudentById(studentDto);
       assertEquals("Updated Successfully",response);


    }

    @Test
    void deleteStudentById() {
        String id="1L";
        studentServiceImpl.deleteStudentById(id);
    }
}