package com.springcrud.crudoperation.service;

import com.springcrud.crudoperation.dto.StudentMarkDto;

public interface MarkService {
    void addStudentMarkById(StudentMarkDto studentMarkDto);


    StudentMarkDto getMarkById(String id);

    String updateMarkById(String id, StudentMarkDto markDto);
}
