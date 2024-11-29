package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.dto.TaskDto;
import com.springcrud.crudoperation.dto.TaskResponse;
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
    @PostMapping("/updateTask")
    public SuccessResponse<Object> updateTask(@RequestBody TaskDto taskDto){
        return taskService.updateTask(taskDto);
    }
    @GetMapping("/getAllTasks")
    public SuccessResponse<Object> getAllTasks(){
        return taskService.getAllTasks();
    }
    @GetMapping("/getTaskById")
    public SuccessResponse<Object> getTaskById(@RequestParam String id){
        return taskService.getTaskById(id);
    }
    @GetMapping("/getAllByProjectId")
    public SuccessResponse<Object> getAllByProjectId(@RequestParam String projectId){
        return taskService.getAllByProjectId(projectId);
    }
}

