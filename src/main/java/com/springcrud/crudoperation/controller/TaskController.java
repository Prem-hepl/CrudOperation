package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.dto.TaskDto;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/createTask")
    public SuccessResponse<Object> createTask(@RequestBody TaskDto taskDto){
        return taskService.createTask(taskDto);
    }
}

