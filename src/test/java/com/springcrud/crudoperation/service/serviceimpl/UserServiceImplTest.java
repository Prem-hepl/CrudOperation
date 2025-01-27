package com.springcrud.crudoperation.service.serviceimpl;

import com.springcrud.crudoperation.dto.UserDto;
import com.springcrud.crudoperation.model.MasterRole;
import com.springcrud.crudoperation.model.User;
import com.springcrud.crudoperation.repository.MasterRoleRepository;
import com.springcrud.crudoperation.repository.UserRepository;
import com.springcrud.crudoperation.response.SuccessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private MasterRoleRepository masterRoleRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId("1L");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password");
        userDto.setName("Test User");


        MasterRole masterRole = new MasterRole();
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.empty());
        when(masterRoleRepository.findById(userDto.getApplicationRole())).thenReturn(Optional.of(masterRole));
        SuccessResponse<Object>response=userService.createUser(userDto);
        assertNotNull(response);
        assertEquals(200,response.getStatusCode());
        assertEquals("User Created Successfully....",response.getStatusMesssage());

        User user=new User();
        user.setEmail("test@example.com");
        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(user));
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                userService.createUser(userDto));
        assertEquals("No Data Found", exception.getMessage());
    }
}