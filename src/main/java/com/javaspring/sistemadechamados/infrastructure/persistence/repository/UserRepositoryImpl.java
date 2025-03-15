package com.javaspring.sistemadechamados.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.javaspring.sistemadechamados.domain.model.Company;
import com.javaspring.sistemadechamados.domain.model.User;
import com.javaspring.sistemadechamados.domain.repositoryports.UserRepository;
import com.javaspring.sistemadechamados.infrastructure.persistence.entity.CompanyEntity;
import com.javaspring.sistemadechamados.infrastructure.persistence.entity.UserEntity;
import com.javaspring.sistemadechamados.infrastructure.persistence.repositoryjpa.UserRepositoryJpa;
import com.javaspring.sistemadechamados.web.exception.BusinessException;
import com.javaspring.sistemadechamados.web.exception.ResourceNotFoundException;
import com.javaspring.sistemadechamados.domain.serviceports.CompanyService;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final UserRepositoryJpa userRepositoryJpa;
    private final ModelMapper modelMapper;
    private final CompanyService companyService;
    public UserRepositoryImpl(UserRepositoryJpa userRepositoryJpa, ModelMapper modelMapper, CompanyService companyService) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.modelMapper = modelMapper;
        this.companyService = companyService;
    }
    @Override
    public User createUser(User user) {
        userRepositoryJpa.findByEmail(user.getEmail()).ifPresent(existingUser-> {
            throw new BusinessException("Email already registered in the system");
        });
        Company company = companyService.getCompanyById(user.getCompany().getId());
        CompanyEntity companyEntity = modelMapper.map(company, CompanyEntity.class);
       UserEntity userEntity = modelMapper.map(user, UserEntity.class);
       userEntity.setCompany(companyEntity);
       UserEntity userEntitySaved = userRepositoryJpa.save(userEntity);
       return modelMapper.map(userEntitySaved, User.class);
    }
    @Override
    public Optional<User> getUserById(UUID id) {
      return userRepositoryJpa.findById(id)
            .map(userEntity -> modelMapper.map(userEntity, User.class));
    }
    @Override
    public Page<User> getAllUsers(Pageable pageable) {
      Page<UserEntity> userEntities = userRepositoryJpa.findAll(pageable);
      return userEntities.map(userEntity -> modelMapper.map(userEntity, User.class));
    }
    @Override
    public  User updateUser(UUID id, User user) {
        UserEntity existingUserEntity = userRepositoryJpa.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found whit ID: " + id));

        UserEntity updatedUserEntity = modelMapper.map(user, UserEntity.class);
        updatedUserEntity.setId(existingUserEntity.getId());
        UserEntity savedUserEntity = userRepositoryJpa.save(updatedUserEntity);
        return modelMapper.map(savedUserEntity, User.class);
     
    }
    @Override
    public void deleteUser(UUID id) {
        UserEntity existingUser = userRepositoryJpa.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found whit ID: " + id));
    
        userRepositoryJpa.delete(existingUser);

    }
    @Override
    public User getUserByEmail(String email) {
        return userRepositoryJpa.findByEmail(email)
        .map(userEntity -> modelMapper.map(userEntity, User.class))
        .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found."));

    }

}
