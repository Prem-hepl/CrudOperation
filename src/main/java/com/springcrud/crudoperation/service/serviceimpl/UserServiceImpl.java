package com.springcrud.crudoperation.service.serviceimpl;

import com.springcrud.crudoperation.dto.UserDto;
import com.springcrud.crudoperation.model.User;
import com.springcrud.crudoperation.repository.UserRepository;
import com.springcrud.crudoperation.response.SuccessResponse;
import com.springcrud.crudoperation.response.UserResponseDto;
import com.springcrud.crudoperation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public SuccessResponse<Object> createUser(UserDto userDto) throws Exception {
        SuccessResponse<Object> response = new SuccessResponse<>();
        try {
            if (Objects.nonNull(userDto)) {
                Optional<User> savedUser = userRepository.findByEmail(userDto.getEmail());
                if (savedUser.isPresent()) {
                    throw new RuntimeException("User email already Exist");
                }
            }
            User user = new User();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setOgPassword(userDto.getOgPassword());
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setActive(userDto.isActive());
            user.setDeleteFlag(!userDto.isActive());
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setStatusCode(200);
        response.setStatusMesssage("User Created Successfully....");
        return response;
    }

    @Override
    public SuccessResponse<Object> updateUser(UserDto userDto) {
        SuccessResponse<Object> response = new SuccessResponse<>();
        User user = new User();
        try {
            if (Objects.nonNull(userDto.getId())) {
                user = userRepository.findById(userDto.getId()).orElseThrow
                        (() -> new RuntimeException("User not Found"));
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setOgPassword(userDto.getOgPassword());
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setActive(userDto.isActive());
            user.setDeleteFlag(!userDto.isActive());
            userRepository.save(user);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.setStatusCode(200);
        response.setStatusMesssage("User Updated Successfully....");
        return response;
    }

    @Override
    public SuccessResponse<Object> getUserById(String id) {
        SuccessResponse<Object> response = new SuccessResponse<>();
        try {
            if (Objects.nonNull(id)) {
                Optional<User> savedUser = userRepository.findById(id);
                if (savedUser.isPresent()) {
                    User users = savedUser.get();
                    UserResponseDto userDto = new UserResponseDto();
                    userDto.setId(users.getId());
                    userDto.setName(users.getName());
                    userDto.setEmail(users.getEmail());
                    userDto.setCreatedAt(String.valueOf(LocalDateTime.now()));
                    userDto.setUpdatedAt(String.valueOf(LocalDateTime.now()));
                    userDto.setActive(users.isActive());
                    userDto.setDeleteFlag(!users.isActive());

                    response.setStatusCode(200);
                    response.setStatusMesssage("Success");
                    response.setData(userDto);
                } else {
                    response.setStatusCode(500);
                    response.setStatusMesssage("User not Found");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    @Override
    public SuccessResponse<Object> getAllUsers() {
        SuccessResponse<Object> response = new SuccessResponse<>();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        try {
            List<User> savedUser = userRepository.findAll();

            userResponseDtos = savedUser.stream()
                    .map(user -> {
                        UserResponseDto userDto = new UserResponseDto();
                        userDto.setId(user.getId());
                        userDto.setName(user.getName());
                        userDto.setEmail(user.getEmail());
                        userDto.setCreatedAt(String.valueOf(LocalDateTime.now()));
                        userDto.setUpdatedAt(String.valueOf(LocalDateTime.now()));
                        userDto.setActive(user.isActive());
                        userDto.setDeleteFlag(!user.isActive());
                        return userDto;
                    }).toList();
            response.setData(userResponseDtos);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
}
