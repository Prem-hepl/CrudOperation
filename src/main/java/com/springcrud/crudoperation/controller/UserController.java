package com.springcrud.crudoperation.controller;


import com.springcrud.crudoperation.dto.UserDto;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public SuccessResponse<Object> addUser(@RequestBody UserDto userDto) throws Exception {
        return  userService.createUser(userDto);
    }
    @PostMapping("/updateUser")
    public SuccessResponse<Object> updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }
    @GetMapping("/getUserById")
    public SuccessResponse<Object> getUserById(@RequestParam String id){
        return userService.getUserById(id);
    }
    @GetMapping("/getAllUsers")
    public SuccessResponse<Object> getAllUsers(){
        return userService.getAllUsers();
    }
}
