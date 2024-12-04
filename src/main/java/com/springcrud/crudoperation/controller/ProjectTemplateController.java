package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.service.ProjectTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectTemplateController {
    @Autowired
    private ProjectTemplateService projectTemplateService;

    @PostMapping("/createProjectTemplate")
    public SuccessResponse<Object> createProjectTemplate(@RequestParam String projectId){
        return projectTemplateService.createProjectTemplate(projectId);
    }
}
