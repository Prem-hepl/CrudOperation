package com.springcrud.crudoperation.service;

import com.springcrud.crudoperation.dto.ProjectDto;
import com.springcrud.crudoperation.response.SuccessResponse;


public interface ProjectService {

    SuccessResponse<Object> createProject(ProjectDto projectDto);

    SuccessResponse<Object> getAllProjects();

    SuccessResponse<Object> updateProject(ProjectDto projectDto);

    SuccessResponse<Object> getProjectById(String id);
}
