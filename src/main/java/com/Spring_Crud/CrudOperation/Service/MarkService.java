package com.Spring_Crud.CrudOperation.Service;

import com.Spring_Crud.CrudOperation.Dto.StudentMarkDto;

public interface MarkService {
    void addStudentMarkById(StudentMarkDto studentMarkDto);


    StudentMarkDto getMarkById(String id);

    String updateMarkById(String id, StudentMarkDto markDto);
}
