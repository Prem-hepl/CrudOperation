package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.dto.ProjectDto;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/createProject")
    public SuccessResponse<Object> addProject (@RequestBody ProjectDto projectDto){
        return projectService.createProject(projectDto);
    }
    @PostMapping("/updateProject")
    public SuccessResponse<Object>updateProject(@RequestBody ProjectDto projectDto){
        return projectService.updateProject(projectDto);
    }
    @GetMapping("/getProjectById")
    public SuccessResponse<Object> getProjectById(@RequestParam String id){
        return projectService.getProjectById(id);
    }
    @GetMapping("/getAllProjects")
    public SuccessResponse<Object> getAllProjects(){
        return projectService.getAllProjects();
    }
}
