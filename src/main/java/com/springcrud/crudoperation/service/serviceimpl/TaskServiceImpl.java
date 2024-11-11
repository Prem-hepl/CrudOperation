package com.springcrud.crudoperation.service.serviceimpl;

import com.springcrud.crudoperation.dto.MilestoneDto;
import com.springcrud.crudoperation.dto.TaskDto;
import com.springcrud.crudoperation.model.Milestone;
import com.springcrud.crudoperation.model.Task;
import com.springcrud.crudoperation.model.User;
import com.springcrud.crudoperation.repository.MilestoneRepository;
import com.springcrud.crudoperation.repository.TaskRepository;
import com.springcrud.crudoperation.repository.UserRepository;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.response.UserResponseDto;
import com.springcrud.crudoperation.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MilestoneRepository milestoneRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public SuccessResponse<Object> createTask(TaskDto taskDto) {
        SuccessResponse<Object> response=new SuccessResponse<>();
        try {
            if (Objects.nonNull(taskDto)){
                Optional<Task> savedTask=taskRepository.findByName(taskDto.getName());
                if (savedTask.isPresent()){
                    throw new RuntimeException("Task Name Already Exist");
                }
                Milestone milestone=milestoneRepository.findById(taskDto.getMilestoneId()).orElseThrow
                        (()->new RuntimeException("Milestone Not found"));
                Task task=modelMapper.map(taskDto, Task.class);
                User user=userRepository.findById(taskDto.getCreatedBy()).orElseThrow(()->
                        new RuntimeException("User not Found"));
                task.setCreatedBy(modelMapper.map(user, UserResponseDto.class));
                task.setUpdatedBy(modelMapper.map(user, UserResponseDto.class));
                taskRepository.save(task);
                task.setMilestone(milestone);

                List<Task> existingTask = milestone.getTasks();
                boolean taskExists = existingTask.stream()
                        .anyMatch(exixtingTask -> exixtingTask.getId().equals(task.getId()));
                if (!taskExists) {
                    existingTask.add(task);
                }
                milestone.setTasks(existingTask);
                milestoneRepository.save(milestone);

            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        response.setStatusCode(200);
        response.setStatusMesssage("Task Created Successfully");
        return response;
    }
}
