package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.dto.MilestoneDto;
import com.springcrud.crudoperation.service.MilestoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
 public class MilestoneController {

  @Autowired
 MilestoneService milestoneService;

  @PostMapping("/createMilestone")
 public String createMilestone(@RequestBody MilestoneDto milestoneDto){
   return milestoneService.createMilestone(milestoneDto);
  }
}
