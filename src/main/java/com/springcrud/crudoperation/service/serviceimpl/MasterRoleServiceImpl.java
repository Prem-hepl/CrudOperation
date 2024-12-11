package com.springcrud.crudoperation.service.serviceimpl;

import com.springcrud.crudoperation.dto.MasterRoleDto;
import com.springcrud.crudoperation.model.MasterRole;
import com.springcrud.crudoperation.repository.MasterRoleRepository;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.service.MasterRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class MasterRoleServiceImpl implements MasterRoleService {

    @Autowired
    private MasterRoleRepository masterRoleRepository;


    @Override
    public SuccessResponse<Object> createRole(MasterRoleDto masterRoleDto) {
        SuccessResponse<Object>response=new SuccessResponse<>();

        try {
            if (Objects.nonNull(masterRoleDto)){
                Optional<MasterRole>savedRole=masterRoleRepository.findByRoleName(masterRoleDto.getRoleName());
                if (savedRole.isPresent()){
                    throw new RuntimeException("Role Name Already Exist");
                }
            }
            MasterRole masterRole=new MasterRole();
            masterRole.setId(masterRoleDto.getId());
            masterRole.setRoleName(masterRoleDto.getRoleName());
            masterRole.setCreatedAt(masterRoleDto.getCreatedAt());
            masterRole.setActive(masterRoleDto.isActive());
            masterRole.setDeleteFlag(!masterRoleDto.isActive());
            masterRoleRepository.save(masterRole);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        response.setStatusCode(200);
        response.setStatusMesssage("Role created Successfully");
        return response;
    }
}
