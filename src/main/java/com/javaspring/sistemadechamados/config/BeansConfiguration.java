package com.javaspring.sistemadechamados.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.javaspring.sistemadechamados.application.service.CompanyServiceImpl;
import com.javaspring.sistemadechamados.application.service.UserServiceImpl;
import com.javaspring.sistemadechamados.domain.repositoryports.CompanyRepository;
import com.javaspring.sistemadechamados.domain.repositoryports.UserRepository;
import com.javaspring.sistemadechamados.domain.serviceports.CompanyService;
import com.javaspring.sistemadechamados.domain.serviceports.UserService;

@Configuration
public class BeansConfiguration {
      @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CompanyService companyService (CompanyRepository companyRepository) {
        return new CompanyServiceImpl(companyRepository);
    }

    @Bean 
    public UserService userService (UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

}
