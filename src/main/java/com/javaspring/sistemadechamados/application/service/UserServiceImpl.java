package com.javaspring.sistemadechamados.application.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaspring.sistemadechamados.domain.model.User;
import com.javaspring.sistemadechamados.domain.repositoryports.UserRepository;
import com.javaspring.sistemadechamados.domain.serviceports.UserService;
import com.javaspring.sistemadechamados.web.exception.ResourceNotFoundException;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user) {
       return userRepository.createUser(user);
    }
    @Override
    public User getUserById(UUID id) {
        return userRepository.getUserById(id)
             .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
    @Override
    public Page<User> getAllUsers(Pageable pageable) {
       return userRepository.getAllUsers(pageable);
    }
    @Override
    public User updateUser(UUID id, User user) {
   
        return userRepository.updateUser(id, user);
    }
    @Override
    public void deleteUser(UUID id) {
        User existingUser = getUserById(id);
        userRepository.deleteUser(id);
    }


}
