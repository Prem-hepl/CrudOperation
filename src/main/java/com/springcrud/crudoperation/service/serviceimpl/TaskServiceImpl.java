package com.springcrud.crudoperation.service.serviceimpl;

import com.springcrud.crudoperation.dto.TaskDto;
import com.springcrud.crudoperation.model.Milestone;
import com.springcrud.crudoperation.model.SubTask;
import com.springcrud.crudoperation.model.Task;
import com.springcrud.crudoperation.model.User;
import com.springcrud.crudoperation.repository.MilestoneRepository;
import com.springcrud.crudoperation.repository.TaskRepository;
import com.springcrud.crudoperation.repository.UserRepository;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.dto.TaskResponse;
import com.springcrud.crudoperation.response.UserResponseDto;
import com.springcrud.crudoperation.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                    throw new IllegalArgumentException("Task Name Already Exist");
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

                List<Task> existingTasks = milestone.getTasks();
                if (existingTasks.stream().noneMatch(existingTask -> existingTask.getId().equals(task.getId()))){
                    existingTasks.add(task);
                }
//                boolean taskExists = existingTask.stream()
//                        .anyMatch(exixtingTask -> exixtingTask.getId().equals(task.getId()));
//                if (!taskExists) {
//                    existingTask.add(task);
//                }
                milestone.setTasks(existingTasks);
                milestoneRepository.save(milestone);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        response.setStatusCode(200);
        response.setStatusMesssage("Task Created Successfully");
        return response;
    }

    @Override
    public SuccessResponse<Object> getAllByProjectId(String projectId) {
        SuccessResponse<Object>response = new SuccessResponse<>();

        List<TaskResponse>taskResponseList;
        try {
            List<Task>taskList=taskRepository.findByProjectId(projectId);
            taskResponseList = taskList.stream()
                    .map(task -> {
                        TaskResponse taskResponse=new TaskResponse();
                        taskResponse.setId(task.getId());
                        taskResponse.setName(task.getName());
                        taskResponse.setDescription(task.getDescription());
                        taskResponse.setCreatedAt(String.valueOf(task.getCreatedAt()));
                        taskResponse.setUpdatedAt(String.valueOf(task.getUpdatedAt()));
                        taskResponse.setCreatedBy(task.getCreatedBy());
                        taskResponse.setUpdatedBy(task.getUpdatedBy());
                        taskResponse.setMilestoneId(String.valueOf(task.getMilestone()));
                        taskResponse.setActive(task.isActive());
                        taskResponse.setDeleteFlag(!task.isActive());
                        taskResponse.setMilestoneId(task.getMilestone().getId());
                        return taskResponse;
                    }).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setCount(taskResponseList.size());
        response.setData(taskResponseList);
        return response;
    }

    @Override
    public SuccessResponse<Object> getAllTasks() {
        SuccessResponse<Object>response=new SuccessResponse<>();
        List<TaskResponse>taskResponseList=new ArrayList<>();
        try {
            List<Task>tasks=taskRepository.findAll();
            taskResponseList=tasks.stream()
                    .map(task -> {
                        TaskResponse taskResponse=new TaskResponse();
                        taskResponse.setId(task.getId());
                        taskResponse.setName(task.getName());
                        taskResponse.setDescription(task.getDescription());
                        taskResponse.setCreatedAt(String.valueOf(task.getCreatedAt()));
                        taskResponse.setUpdatedAt(String.valueOf(task.getUpdatedAt()));
                        taskResponse.setCreatedBy(task.getCreatedBy());
                        taskResponse.setUpdatedBy(task.getUpdatedBy());
                        taskResponse.setActive(task.isActive());
                        taskResponse.setDeleteFlag(!task.isActive());
                        taskResponse.setMilestoneId(task.getMilestone().getId());

                        List<String>subTaskList=task.getSubTasks().stream()
                                .map(SubTask::getId).toList();
                        taskResponse.setSubTask(subTaskList);
                        return taskResponse;
                    }).toList();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setCount(taskResponseList.size());
        response.setData(taskResponseList);
        return response;
    }

    @Override
    public SuccessResponse<Object> updateTask(TaskDto taskDto) {
        SuccessResponse<Object>response=new SuccessResponse<>();
        Task task=new Task();
        try {
            if (Objects.nonNull(taskDto)){
                task=taskRepository.findById(taskDto.getId()).orElseThrow
                        (()->new RuntimeException("Task not Found"));
            }
            User user=userRepository.findById(taskDto.getUpdatedBy()).orElseThrow();
            task.setName(taskDto.getName());
            task.setDescription(taskDto.getDescription());
            task.setUpdatedAt(taskDto.getUpdatedAt());
            task.setCreatedBy(task.getCreatedBy());
            task.setUpdatedBy(modelMapper.map(user, UserResponseDto.class));
            task.setActive(taskDto.isActive());
            task.setDeleteFlag(!taskDto.isActive());
            taskRepository.save(task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setStatusCode(200);
        response.setStatusMesssage("Task Updated Successfully...");
        return response;
    }

    @Override
    public SuccessResponse<Object> getTaskById(String id) {
        SuccessResponse<Object> response=new SuccessResponse<>();

        TaskResponse taskResponse= null;
        try {
            if (Objects.nonNull(id)) {
                Task task = taskRepository.findById(id).orElseThrow();
                taskResponse = new TaskResponse();
                taskResponse.setId(task.getId());
                taskResponse.setName(task.getName());
                taskResponse.setDescription(task.getDescription());
                taskResponse.setCreatedAt(String.valueOf(task.getCreatedAt()));
                taskResponse.setUpdatedAt(String.valueOf(task.getUpdatedAt()));
                taskResponse.setCreatedBy(task.getCreatedBy());
                taskResponse.setUpdatedBy(task.getUpdatedBy());
                taskResponse.setMilestoneId(task.getMilestone().getId());
                taskResponse.setActive(task.isActive());
                taskResponse.setDeleteFlag(!task.isActive());

                List<String> subTaskList = task.getSubTasks().stream()
                        .map(SubTask::getId).toList();

                taskResponse.setSubTask(subTaskList);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setData(taskResponse);
        return response;
    }
}
