package com.Spring_Crud.CrudOperation.Dto;

import com.Spring_Crud.CrudOperation.Model.StudentMark;
import lombok.Data;

import java.util.List;

@Data

public class StudentDto {
    private String id;
    private String name;
    private String mobile;
    private String city;
    private List<StudentMark> studentMarkList;

}
