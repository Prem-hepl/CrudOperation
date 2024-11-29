package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.dto.MilestoneDto;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MilestoneController {

    @Autowired
    MilestoneService milestoneService;

    @PostMapping("/createMilestone")
    public SuccessResponse<Object> createMilestone(@RequestBody MilestoneDto milestoneDto) throws RuntimeException {
        return milestoneService.createMilestone(milestoneDto);
    }
    @PostMapping("/updateMilestone")
    public SuccessResponse<Object>updateMilestone(@RequestBody MilestoneDto milestoneDto){
        return milestoneService.updateMilestone(milestoneDto);
    }
    @GetMapping("/getMilestoneById")
    public SuccessResponse<Object>getMilestoneById(@RequestParam String id){
        return milestoneService.getMilestoneById(id);
    }
    @GetMapping("/getAllMilestoneByProject")
    public SuccessResponse<Object> getAllMilestoneByProject(@RequestParam String projectId){
        return milestoneService.getAllMilestoneByProjectId(projectId);
    }
    @GetMapping("/getAllMilestone")
    public SuccessResponse<Object> getAllMilestone() {
        return milestoneService.getAllMilestone();
    }
}
