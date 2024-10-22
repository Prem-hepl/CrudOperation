package com.springcrud.crudoperation.dto;

import com.springcrud.crudoperation.model.StudentMark;
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
