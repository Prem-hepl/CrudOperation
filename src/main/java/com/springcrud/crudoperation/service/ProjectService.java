package com.springcrud.crudoperation.service;

import com.springcrud.crudoperation.dto.ProjectDto;
import com.springcrud.crudoperation.dto.UserDto;
import com.springcrud.crudoperation.response.SuccessResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProjectService {

    SuccessResponse<Object> createProject(ProjectDto projectDto);

    SuccessResponse<Object> getAllProjects();

    SuccessResponse<Object> updateProject(ProjectDto projectDto);

    SuccessResponse<Object> getProjectById(String id);
}
