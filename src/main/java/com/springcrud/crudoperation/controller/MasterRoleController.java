package com.springcrud.crudoperation.controller;

import com.springcrud.crudoperation.dto.MasterRoleDto;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.service.MasterRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterRoleController {
    @Autowired
    private MasterRoleService masterRoleService;

    @PostMapping("/role")
    public SuccessResponse<Object>role(@RequestBody MasterRoleDto masterRoleDto){
        return masterRoleService.createRole(masterRoleDto);
    }
}
