package com.Spring_Crud.CrudOperation.Controller;

import com.Spring_Crud.CrudOperation.Dto.StudentMarkDto;
import com.Spring_Crud.CrudOperation.Service.MarkService;
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
    @PutMapping("/updateMark")
    public String updateMark(@RequestParam String id,@RequestBody StudentMarkDto markDto){
        return markService.updateMarkById(id,markDto);
    }
    public StudentMarkDto getMarkById(@RequestParam String id){
        return markService.getMarkById(id);

    }
}
