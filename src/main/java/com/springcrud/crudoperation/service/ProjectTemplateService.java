package com.springcrud.crudoperation.service;

import com.springcrud.crudoperation.response.SuccessResponse;

public interface ProjectTemplateService {
    SuccessResponse<Object> createProjectTemplate(String projectId);

    SuccessResponse<Object> getAllPojectTemplate();
}
