package com.javaspring.sistemadechamados.infrastructure.persistence.repositoryjpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaspring.sistemadechamados.infrastructure.persistence.entity.UserEntity;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, UUID> {

   Optional <UserEntity> findByEmail(String email);
   
}
