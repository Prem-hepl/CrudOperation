package com.springcrud.crudoperation.service;

import com.springcrud.crudoperation.dto.MilestoneDto;
import com.springcrud.crudoperation.response.SuccessResponse;

public interface MilestoneService {
    SuccessResponse<Object>createMilestone(MilestoneDto milestoneDto) throws RuntimeException;

    SuccessResponse<Object> getAllMilestone();

    SuccessResponse<Object> updateMilestone(MilestoneDto milestoneDto);

    SuccessResponse<Object> getMilestoneById(String id);
}
