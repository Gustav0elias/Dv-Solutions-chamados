package com.javaspring.sistemadechamados.domain.serviceports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaspring.sistemadechamados.domain.model.User;

public interface UserService {

  public User createUser(User user);
  public User getUserById(UUID id);
  public Page<User> getAllUsers(Pageable pageable);
  public User updateUser(UUID id, User user);
  public void deleteUser(UUID id);
  
}  