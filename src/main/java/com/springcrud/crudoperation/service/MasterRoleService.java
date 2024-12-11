package com.springcrud.crudoperation.service;

import com.springcrud.crudoperation.dto.MasterRoleDto;
import com.springcrud.crudoperation.response.SuccessResponse;

public interface MasterRoleService {
    SuccessResponse<Object> createRole(MasterRoleDto masterRoleDto);
}
