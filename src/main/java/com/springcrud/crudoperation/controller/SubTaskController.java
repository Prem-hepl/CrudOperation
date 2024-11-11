package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.dto.SubTaskDto;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.service.SubTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubTaskController {
    @Autowired
    private SubTaskService subTaskService;

    @PostMapping("/createSubTask")
    public SuccessResponse<Object> createSubTask(@RequestBody SubTaskDto subTaskDto){
        return subTaskService.createSubTask(subTaskDto);
    }
}
