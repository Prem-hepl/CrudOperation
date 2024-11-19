package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.dto.SubTaskDto;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.service.SubTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubTaskController {
    @Autowired
    private SubTaskService subTaskService;

    @PostMapping("/createSubTask")
    public SuccessResponse<Object> createSubTask(@RequestBody SubTaskDto subTaskDto){
        return subTaskService.createSubTask(subTaskDto);
    }
    @GetMapping("/getAllSubTasks")
    public SuccessResponse<Object>getAllSubTasks(){
        return subTaskService.getAllSubTasks();
    }
    @PostMapping("/updateSubTask")
    public SuccessResponse<Object> updateSubTask(@RequestBody SubTaskDto subTaskDto){
        return subTaskService.updateSubTask(subTaskDto);
    }
    @GetMapping("/getSubTaskById")
    public SuccessResponse<Object> getSubTaskById(@RequestParam String id){
        return subTaskService.getSubTaskById(id);
    }
}
