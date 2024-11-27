package com.springcrud.crudoperation.service.serviceimpl;


import com.springcrud.crudoperation.dto.ProjectDto;
import com.springcrud.crudoperation.model.Milestone;
import com.springcrud.crudoperation.model.Project;
import com.springcrud.crudoperation.model.User;
import com.springcrud.crudoperation.repository.ProjectRepository;
import com.springcrud.crudoperation.repository.UserRepository;
import com.springcrud.crudoperation.dto.ProjectResponse;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.response.UserResponseDto;
import com.springcrud.crudoperation.service.ProjectService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    UserRepository userRepository;

    @Override
    public SuccessResponse<Object> createProject(ProjectDto projectDto) {
        SuccessResponse<Object> response = new SuccessResponse<>();
        try {
            if (Objects.nonNull(projectDto)) {
                Optional<Project> optional = projectRepository.findByName(projectDto.getName());
                if (optional.isPresent()) {
                    throw new RuntimeException("Project Name Already exist");
                }
                Project project = optional.get();
                project.setId(projectDto.getId());
                project.setName(projectDto.getName());
                project.setDescription(projectDto.getDescription());
                project.setCreatedAt(projectDto.getCreatedAt());
                project.setUpdatedAt(projectDto.getUpdatedAt());
                User user = userRepository.findById(projectDto.getCreatedBy()).orElseThrow();
                project.setCreatedBy(modelMapper.map(user, UserResponseDto.class));
                project.setUpdatedBy(modelMapper.map(user, UserResponseDto.class));
                projectRepository.save(project);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setStatusCode(200);
        response.setStatusMesssage("Project Created Successfully....");
        return response;
    }

    @Override
    public SuccessResponse<Object> updateProject(ProjectDto projectDto) {
        SuccessResponse<Object> response = new SuccessResponse<>();

        Project project = new Project();
        try {
            if (Objects.nonNull(projectDto)) {
                project = projectRepository.findById(projectDto.getId()).orElseThrow
                        (() -> new RuntimeException("Project not found"));
                }
                project = modelMapper.map(projectDto, Project.class);
                User user = userRepository.findById(projectDto.getCreatedBy()).orElseThrow
                        (() -> new RuntimeException("User not Found"));
                project.setCreatedBy(modelMapper.map(user, UserResponseDto.class));
                project.setUpdatedBy(modelMapper.map(user, UserResponseDto.class));
                project.setMilestone(project.getMilestone());
                projectRepository.save(project);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setStatusCode(200);
        response.setStatusMesssage("Project Updated Successfully...");
        return response;
    }

    @Override
    public SuccessResponse<Object> getProjectById(String id) {
        SuccessResponse<Object>response=new SuccessResponse<>();

        try {
            if (Objects.nonNull(id)){
                Optional<Project>savedProjects=projectRepository.findById(id);
                if (savedProjects.isPresent()){
                    Project project=savedProjects.get();
                    ProjectResponse projectResponse=new ProjectResponse();
                    projectResponse.setId(project.getId());
                    projectResponse.setName(project.getName());
                    projectResponse.setDescription(project.getDescription());
                    projectResponse.setCreatedAt(String.valueOf(LocalDateTime.now()));
                    projectResponse.setUpdatedAt(String.valueOf(LocalDateTime.now()));
                    projectResponse.setCreatedBy(project.getCreatedBy());
                    projectResponse.setUpdatedBy(project.getUpdatedBy());
                    projectResponse.setActive(project.isActive());
                    projectResponse.setDeleteFlag(!projectResponse.isActive());

                    List<String>milestoneList=project.getMilestone().stream()
                                    .map(Milestone::getId).toList();

                    projectResponse.setMilestone(milestoneList);

                    response.setData(projectResponse);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public SuccessResponse<Object> getAllProjects() {
        SuccessResponse<Object> response = new SuccessResponse<>();
        List<ProjectResponse> projectResponseList = new ArrayList<>();
        try {
            List<Project> projects = projectRepository.findAll();
            projectResponseList = projects.stream()
                    .map(project -> {
                        ProjectResponse projectResponse = new ProjectResponse();
                        projectResponse.setId(project.getId());
                        projectResponse.setName(project.getName());
                        projectResponse.setDescription(project.getDescription());
                        projectResponse.setCreatedAt(String.valueOf(project.getCreatedAt()));
                        projectResponse.setUpdatedAt(String.valueOf(project.getUpdatedAt()));
                        projectResponse.setCreatedBy(project.getCreatedBy());
                        projectResponse.setUpdatedBy(project.getUpdatedBy());
                        projectResponse.setActive(project.isActive());
                        projectResponse.setDeleteFlag(!project.isActive());
                        return projectResponse;
                    }).toList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setData(projectResponseList);
        return response;
    }
}
