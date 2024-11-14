package com.springcrud.crudoperation.service;

import com.springcrud.crudoperation.dto.TaskDto;
import com.springcrud.crudoperation.response.SuccessResponse;

public interface TaskService {
    SuccessResponse<Object> createTask(TaskDto taskDto);

    SuccessResponse<Object> getAllByProjectId(String projectId);

    SuccessResponse<Object> getAllTasks();
}
