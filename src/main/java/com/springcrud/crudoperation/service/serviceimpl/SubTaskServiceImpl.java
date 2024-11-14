package com.springcrud.crudoperation.service.serviceimpl;

import com.springcrud.crudoperation.dto.SubTaskDto;
import com.springcrud.crudoperation.model.SubTask;
import com.springcrud.crudoperation.model.Task;
import com.springcrud.crudoperation.model.User;
import com.springcrud.crudoperation.repository.SubTaskRepository;
import com.springcrud.crudoperation.repository.TaskRepository;
import com.springcrud.crudoperation.repository.UserRepository;
import com.springcrud.crudoperation.response.SubTaskResponse;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.response.TaskResponse;
import com.springcrud.crudoperation.response.UserResponseDto;
import com.springcrud.crudoperation.service.SubTaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubTaskServiceImpl implements SubTaskService {
    @Autowired
    private SubTaskRepository subTaskRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SuccessResponse<Object> createSubTask(SubTaskDto subTaskDto) {
        SuccessResponse<Object>response=new SuccessResponse<>();
        try {
            if (Objects.nonNull(subTaskDto)){
                Optional<SubTask> savedSubTask=subTaskRepository.findByName(subTaskDto.getName());
                if (savedSubTask.isPresent()){
                    throw new RuntimeException("SubTask name already exist");
                }
                Task task=taskRepository.findById(subTaskDto.getTaskId()).orElseThrow
                        (()->new RuntimeException("Task not Found"));
                SubTask subTask=modelMapper.map(subTaskDto, SubTask.class);
                User user=userRepository.findById(subTaskDto.getCreatedBy()).orElseThrow
                        (()->new RuntimeException("User not Found"));
                subTask.setCreatedBy(modelMapper.map(user, UserResponseDto.class));
                subTask.setUpdatedBy(modelMapper.map(user, UserResponseDto.class));
                subTaskRepository.save(subTask);
                subTask.setTask(task);

                List<SubTask>existingSubTask=task.getSubTasks();
                boolean  existSubtask=existingSubTask.stream()
                        .anyMatch(exixtingSubTask -> exixtingSubTask.getId().equals(subTask.getId()));
                if (!existSubtask){
                    existingSubTask.add(subTask);
                }
                task.setSubTasks(existingSubTask);
                taskRepository.save(task);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        response.setStatusCode(200);
        response.setStatusMesssage("SubTask Created Successfully");
        return response;
    }

    @Override
    public SuccessResponse<Object> getAllSubTasks() {
        SuccessResponse<Object>response=new SuccessResponse<>();
        List<SubTaskResponse>subTaskResponseList=new ArrayList<>();
        try {
            List<SubTask>subTasks=subTaskRepository.findAll();
            subTaskResponseList=subTasks.stream()
                    .map(subTask -> {
                        SubTaskResponse subTaskResponse=new SubTaskResponse();
                        subTaskResponse.setId(subTask.getId());
                        subTaskResponse.setName(subTask.getName());
                        subTaskResponse.setDescription(subTask.getDescription());
                        subTaskResponse.setCreatedAt(String.valueOf(subTask.getCreatedAt()));
                        subTaskResponse.setUpdatedAt(String.valueOf(subTask.getUpdatedAt()));
                        subTaskResponse.setCreatedBy(subTask.getCreatedBy());
                        subTaskResponse.setUpdatedBy(subTask.getUpdatedBy());
                        subTaskResponse.setActive(subTask.isActive());
                        subTaskResponse.setDeleteFlag(!subTask.isActive());
                        subTaskResponse.setTaskId(subTask.getTask().getId());
                        return subTaskResponse;
                    }).toList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setData(subTaskResponseList);
        return response;
    }
}
