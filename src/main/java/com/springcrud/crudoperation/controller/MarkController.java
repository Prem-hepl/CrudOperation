package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.dto.StudentMarkDto;
import com.springcrud.crudoperation.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
 public class MarkController {

    @Autowired
    MarkService markService;

    @PostMapping("/addMark")
    public String addStudentMark(@RequestBody StudentMarkDto studentMarkDto){
        markService.addStudentMarkById(studentMarkDto);
        return "Mark added....";
    }
    @PostMapping("/updateMark")
    public String updateMark(@RequestParam String id,@RequestBody StudentMarkDto markDto){
        return markService.updateMarkById(id,markDto);
    }
    @GetMapping("/markById")
    public StudentMarkDto getMarkById(@RequestParam String id){
        return markService.getMarkById(id);

    }
}
