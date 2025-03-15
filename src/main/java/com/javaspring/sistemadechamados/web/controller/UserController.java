package com.javaspring.sistemadechamados.web.controller;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javaspring.sistemadechamados.application.dto.request.UserRequestDTO;
import com.javaspring.sistemadechamados.application.dto.response.PaginatedResponseDTO;
import com.javaspring.sistemadechamados.application.dto.response.UserResponseDTO;
import com.javaspring.sistemadechamados.application.mapper.UserMapper;
import com.javaspring.sistemadechamados.domain.model.User;
import com.javaspring.sistemadechamados.domain.serviceports.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("dvsolutions/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    
    
    @Operation(summary = "Create a new user", description = "Create a user with the provided information")
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Validated UserRequestDTO userRequestDTO) {
        logger.info("Creating user with email: {}", userRequestDTO.email());
        User user =  userMapper.toEntity(userRequestDTO);
        UserResponseDTO userResponseDTO = userMapper.toResponseDTO(userService.createUser(user)) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @Operation(summary = "Get all users", description = "Retrieve all users with pagination")
    @GetMapping ResponseEntity<PaginatedResponseDTO<UserResponseDTO>> getAllUsers( 
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<UserResponseDTO> userResponseDTOs = userService.getAllUsers(PageRequest.of(page, size))
                .map(userMapper::toResponseDTO);
                 
        return ResponseEntity.ok(new PaginatedResponseDTO<>(userResponseDTOs));
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a user by their unique ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        logger.info("Get user by id: {}", id);
        UserResponseDTO userResponseDTO = userMapper.toResponseDTO(userService.getUserById(id));
        return ResponseEntity.ok(userResponseDTO);
    }

    @Operation(summary = "Update user", description = "Update an existing user's information by their ID")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @RequestBody @Validated UserRequestDTO userRequestDTO) {
        logger.info("editing user with email: {}", userRequestDTO.email());
        User user = userMapper.toEntity(userRequestDTO);
        UserResponseDTO userResponseDTO = userMapper.toResponseDTO(userService.updateUser(id, user));
        return ResponseEntity.ok(userResponseDTO);
    }

    @Operation(summary = "Delete user", description = "Delete a user by their unique ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
