package com.springcrud.crudoperation.service.serviceimpl;


import com.springcrud.crudoperation.dto.ProjectDto;
import com.springcrud.crudoperation.dto.UserDto;
import com.springcrud.crudoperation.model.Project;
import com.springcrud.crudoperation.model.User;
import com.springcrud.crudoperation.repository.ProjectRepository;
import com.springcrud.crudoperation.repository.UserRepository;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.response.UserResponseDto;
import com.springcrud.crudoperation.service.ProjectService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        SuccessResponse<Object> response=new SuccessResponse<>();
        try {
            if (Objects.nonNull(projectDto)){
                Optional<Project> optional= projectRepository.findByName(projectDto.getName());
                if (optional.isPresent()){
                    throw new RuntimeException("Project Name Already exist");
                }
                Project project=modelMapper.map(projectDto, Project.class);
                User user = userRepository.findById(projectDto.getCreatedBy()).orElseThrow();
                project.setCreatedBy(modelMapper.map(user, UserResponseDto.class));
                project.setUpdatedBy(modelMapper.map(user, UserResponseDto.class));
                projectRepository.save(project);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        response.setData(200);
        response.setStatusMesssage("Project Created Successfully....");
        return response;
    }
}
