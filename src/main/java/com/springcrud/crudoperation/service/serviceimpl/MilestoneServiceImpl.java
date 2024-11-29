package com.springcrud.crudoperation.service.serviceimpl;


import com.springcrud.crudoperation.dto.MilestoneDto;
import com.springcrud.crudoperation.model.Milestone;
import com.springcrud.crudoperation.model.Project;
import com.springcrud.crudoperation.model.Task;
import com.springcrud.crudoperation.model.User;
import com.springcrud.crudoperation.repository.MilestoneRepository;
import com.springcrud.crudoperation.repository.ProjectRepository;
import com.springcrud.crudoperation.repository.UserRepository;
import com.springcrud.crudoperation.dto.MilestoneResponse;
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
    public SuccessResponse<Object> createMilestone(MilestoneDto milestoneDto) throws RuntimeException {
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
//                boolean milestoneExists = existingMilestones.stream()
//                        .anyMatch(existingMilestone -> existingMilestone.getId().equals(milestone.getId()));
//                if (!milestoneExists) {
//                    existingMilestones.add(milestone);
//                }
                if (existingMilestones.stream().noneMatch(existingMilestone -> existingMilestone.getId().equals(milestone.getId()))) {
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
        return response;
    }

    @Override
    public SuccessResponse<Object> updateMilestone(MilestoneDto milestoneDto) {
        SuccessResponse<Object>response=new SuccessResponse<>();
        Milestone milestone=new Milestone();
        try {
            if (Objects.nonNull(milestoneDto)){
                milestone=milestoneRepository.findById(milestoneDto.getId()).orElseThrow
                        (()->new RuntimeException("Milestone not found"));
            }
            User user=userRepository.findById(milestoneDto.getCreatedBy()).orElseThrow
                    (()->new RuntimeException("User not Found"));
            milestone.setName(milestoneDto.getName());
            milestone.setDescription(milestoneDto.getDescription());
            milestone.setCreatedBy(milestone.getCreatedBy());
            milestone.setUpdatedAt(milestoneDto.getUpdatedAt());
            milestone.setUpdatedBy(modelMapper.map(user, UserResponseDto.class));
            milestone.setTasks(milestone.getTasks());
            milestone.setProject(milestone.getProject());
            milestoneRepository.save(milestone);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        response.setStatusCode(200);
        response.setStatusMesssage("Milestone Updated Successfully...");
        return response;
    }

    @Override
    public SuccessResponse<Object> getMilestoneById(String id) {
        SuccessResponse<Object> response=new SuccessResponse<>();
        try {
            if (Objects.nonNull(id)){
                Optional<Milestone>savedMilestone=milestoneRepository.findById(id);
                if (savedMilestone.isPresent()){
                    Milestone milestone=savedMilestone.get();
                    MilestoneResponse milestoneResponse=new MilestoneResponse();
                    milestoneResponse.setId(milestone.getId());
                    milestoneResponse.setName(milestone.getName());
                    milestoneResponse.setDescription(milestone.getDescription());
                    milestoneResponse.setCreatedAt(String.valueOf(milestone.getCreatedAt()));
                    milestoneResponse.setUpdatedAt(String.valueOf(milestone.getUpdatedBy()));
                    milestoneResponse.setCreatedBy(milestone.getCreatedBy());
                    milestoneResponse.setUpdatedBy(milestone.getUpdatedBy());
                    milestoneResponse.setActive(milestone.isActive());
                    milestoneResponse.setDeleteFlag(!milestone.isActive());
                    milestoneResponse.setProjectId(milestone.getProject().getId());

                    List<String>taskList=milestone.getTasks().stream()
                                    .map(Task::getId).toList();
                    milestoneResponse.setTask(taskList);
                    response.setData(milestoneResponse);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public SuccessResponse<Object> getAllMilestoneByProjectId(String projectId) {
        SuccessResponse<Object> response=new SuccessResponse<>();
        List<MilestoneResponse>milestoneResponseList=milestoneRepository.findAllMilestoneByProjectId(projectId);
        response.setData(milestoneResponseList);
        return response;
    }

    @Override
    public SuccessResponse<Object> getAllMilestone() {
        SuccessResponse<Object>response=new SuccessResponse<>();
        List<MilestoneResponse>milestoneDtoList=new ArrayList<>();
        try {
            List<Milestone>milestones=milestoneRepository.findAll();

            milestoneDtoList=milestones.stream()
                    .map(milestone -> {
                        MilestoneResponse milestoneDto=new MilestoneResponse();
                        milestoneDto.setId(milestone.getId());
                        milestoneDto.setName(milestone.getName());
                        milestoneDto.setDescription(milestone.getDescription());
                        milestoneDto.setCreatedAt(String.valueOf(milestone.getCreatedAt()));
                        milestoneDto.setUpdatedAt(String.valueOf(milestone.getUpdatedAt()));
                        milestoneDto.setCreatedBy((milestone.getCreatedBy()));
                        milestoneDto.setUpdatedBy((milestone.getUpdatedBy()));
                        milestoneDto.setActive(milestone.isActive());
                        milestoneDto.setDeleteFlag(!milestone.isActive());
                        milestoneDto.setProjectId(milestone.getProject().getId());

                        List<String> taskList=milestone.getTasks().stream()
                                .map(Task::getId).toList();
                        milestoneDto.setTask(taskList);
                        return milestoneDto;
                    }).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setCount(milestoneDtoList.size());
        response.setData(milestoneDtoList);
        return response;
    }


}