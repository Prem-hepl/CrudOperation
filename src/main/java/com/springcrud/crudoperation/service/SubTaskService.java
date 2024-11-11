package com.springcrud.crudoperation.service;

import com.springcrud.crudoperation.dto.SubTaskDto;
import com.springcrud.crudoperation.response.SuccessResponse;

public interface SubTaskService {
    SuccessResponse<Object> createSubTask(SubTaskDto subTaskDto);
}
