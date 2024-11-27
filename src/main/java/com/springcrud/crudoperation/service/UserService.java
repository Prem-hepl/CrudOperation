package com.springcrud.crudoperation.service;

import com.springcrud.crudoperation.dto.UserDto;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.response.UserResponseDto;

import java.util.List;


public interface UserService {

    SuccessResponse<Object> createUser(UserDto userDto) throws Exception;

    SuccessResponse<Object> updateUser(UserDto userDto);

    SuccessResponse<Object> getUserById(String id);

    SuccessResponse<List<UserResponseDto>> getAllUsers();
}
