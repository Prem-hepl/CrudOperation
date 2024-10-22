package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.dto.StudentDto;
import com.springcrud.crudoperation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
 public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody StudentDto studentDto){
        studentService.addStudent(studentDto);
        return "Student added...";
    }
    @GetMapping("/getStudentById")
    public StudentDto getStudent(@RequestParam String id){
        return studentService.getStudentById(id);
    }

    @PutMapping("/updateStudent")
    public String updateStudentById(@RequestBody StudentDto studentDto){
        return studentService.updateStudentById(studentDto);
    }
    @DeleteMapping("/deleteStudent")
    public void deleteStudentById(@RequestParam String id){
        studentService.deleteStudentById(id);
    }


}
