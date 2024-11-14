package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.dto.TaskDto;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.service.TaskService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/createTask")
    public SuccessResponse<Object> createTask(@RequestBody TaskDto taskDto){
        return taskService.createTask(taskDto);
    }
    @GetMapping("/getAllTasks")
    public SuccessResponse<Object> getAllTasks(){
        return taskService.getAllTasks();
    }
    @GetMapping("/getAllByProjectId")
    public SuccessResponse<Object> getAllByProjectId(@RequestParam String projectId){
        return taskService.getAllByProjectId(projectId);
    }
}

