package com.javaspring.sistemadechamados.application.mapper;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.javaspring.sistemadechamados.application.dto.request.TicketRequestDTO;
import com.javaspring.sistemadechamados.application.dto.response.TicketResponseDTO;
import com.javaspring.sistemadechamados.application.enums.TicketPriority;
import com.javaspring.sistemadechamados.application.enums.TicketStatus;
import com.javaspring.sistemadechamados.domain.model.Attachment;
import com.javaspring.sistemadechamados.domain.model.Company;
import com.javaspring.sistemadechamados.domain.model.Ticket;
import com.javaspring.sistemadechamados.domain.model.User;
import com.javaspring.sistemadechamados.domain.repositoryports.CompanyRepository;
import com.javaspring.sistemadechamados.domain.repositoryports.UserRepository;
import com.javaspring.sistemadechamados.infrastructure.persistence.entity.UserEntity;


@Component
public class TicketMapper {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    public TicketMapper(UserRepository userRepository, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public Ticket toEntity(TicketRequestDTO ticketRequestDTO) {
        Ticket ticket = new Ticket();
        ticket.setTitle(ticketRequestDTO.title());
        ticket.setDescription(ticketRequestDTO.description());
  
        ticket.setCategory(ticketRequestDTO.category());

        User createdByUser = userRepository.getUserById(ticketRequestDTO.createdByUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        ticket.setCreatedByUser(createdByUser);

        Company company = companyRepository.getCompanyById(ticketRequestDTO.companyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));
        ticket.setCompany(company);


        ticket.setCreatedAt(LocalDateTime.now());

        return ticket;
    }

    public TicketResponseDTO toResponseDTO(Ticket ticket) {
        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO(ticket.getId(), ticket.getTitle(),ticket.getDescription(), ticket.getStatus(), ticket.getPriority(), ticket.getCategory(), ticket.getCreatedAt(), ticket.getUpdatedAt(), ticket.getCreatedByUser().getId(), ticket.getResponsibleUser() != null ? ticket.getResponsibleUser().getId() : null, ticket.getCompany().getId());
       
 

        return ticketResponseDTO;
    }
}