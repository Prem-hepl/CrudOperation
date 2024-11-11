package com.springcrud.crudoperation.service.serviceimpl;


import com.springcrud.crudoperation.dto.MilestoneDto;
import com.springcrud.crudoperation.model.Milestone;
import com.springcrud.crudoperation.model.Project;
import com.springcrud.crudoperation.model.User;
import com.springcrud.crudoperation.repository.MilestoneRepository;
import com.springcrud.crudoperation.repository.ProjectRepository;
import com.springcrud.crudoperation.repository.UserRepository;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.response.UserResponseDto;
import com.springcrud.crudoperation.service.MilestoneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MilestoneServiceImpl implements MilestoneService {

    @Autowired
    MilestoneRepository milestoneRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;

    @Override
    public String createMilestone(MilestoneDto milestoneDto) {
        SuccessResponse<Object>response=new SuccessResponse<>();
        try {
            if (Objects.nonNull(milestoneDto)){
                Optional<Milestone> savedMilestone=milestoneRepository.findByName(milestoneDto.getName());
                if (savedMilestone.isPresent()){
                    throw new RuntimeException("Milestone name already exist");
                }
                Project project = projectRepository.findById(milestoneDto.getProjectId()).orElseThrow(
                        ()->new RuntimeException("Project Id not found"));

                Milestone milestone=modelMapper.map(milestoneDto, Milestone.class);
                User user = userRepository.findById(milestoneDto.getCreatedBy()).orElseThrow();
                milestone.setCreatedBy(modelMapper.map(user, UserResponseDto.class));
                milestone.setUpdatedBy(modelMapper.map(user, UserResponseDto.class));
                milestone.setProject(project);
                milestoneRepository.save(milestone);

                List<Milestone> existingMilestones = project.getMilestone();
                boolean milestoneExists = existingMilestones.stream()
                        .anyMatch(existingMilestone -> existingMilestone.getId().equals(milestone.getId()));
                if (!milestoneExists) {
                    existingMilestones.add(milestone);
                }
                project.setMilestone(existingMilestones);
                projectRepository.save(project);
            }

        }catch (Exception e){
            throw new RuntimeException(e);
        }
        response.setStatusCode(200);
        response.setStatusMesssage("Milestone Created Successfully...");
        return "Created";
    }
}