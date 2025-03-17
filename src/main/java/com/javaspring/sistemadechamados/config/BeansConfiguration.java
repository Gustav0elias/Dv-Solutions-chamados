package com.javaspring.sistemadechamados.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.javaspring.sistemadechamados.application.service.AttachmentServiceImpl;
import com.javaspring.sistemadechamados.application.service.CommentServiceImpl;
import com.javaspring.sistemadechamados.application.service.CompanyServiceImpl;
import com.javaspring.sistemadechamados.application.service.TicketServiceImpl;
import com.javaspring.sistemadechamados.application.service.UserServiceImpl;
import com.javaspring.sistemadechamados.domain.repositoryports.AttachmentRepository;
import com.javaspring.sistemadechamados.domain.repositoryports.CommentRepository;
import com.javaspring.sistemadechamados.domain.repositoryports.CompanyRepository;
import com.javaspring.sistemadechamados.domain.repositoryports.TicketRepository;
import com.javaspring.sistemadechamados.domain.repositoryports.UserRepository;
import com.javaspring.sistemadechamados.domain.serviceports.AttachmentService;
import com.javaspring.sistemadechamados.domain.serviceports.CommentService;
import com.javaspring.sistemadechamados.domain.serviceports.CompanyService;
import com.javaspring.sistemadechamados.domain.serviceports.TicketService;
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

    @Bean
    public TicketService ticketService (TicketRepository ticketRepository, CompanyRepository companyRepository, UserService userService) {
        return new TicketServiceImpl(ticketRepository, companyRepository, userService);
    }
    @Bean
    public CommentService commentService (CommentRepository commentRepository) {
        return new CommentServiceImpl(commentRepository);
    }

    @Bean
    public AttachmentService attachmentService (AttachmentRepository attachmentRepository) {
        return new AttachmentServiceImpl(attachmentRepository);
    }

    @Bean
    public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
}
}
