package com.javaspring.sistemadechamados.application.mapper;

import org.springframework.stereotype.Component;

import com.javaspring.sistemadechamados.application.dto.request.UserRequestDTO;
import com.javaspring.sistemadechamados.application.dto.response.CompanyResponseDTO;
import com.javaspring.sistemadechamados.application.dto.response.UserResponseDTO;
import com.javaspring.sistemadechamados.domain.model.Company;
import com.javaspring.sistemadechamados.domain.model.User;
import com.javaspring.sistemadechamados.domain.serviceports.CompanyService;

@Component
public class UserMapper {

    
    // Converte User para UserResponseDTO
    public UserResponseDTO toResponseDTO(User user) {
    
        return new UserResponseDTO(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole(),
            user.getCompany()
           // mapCompaniesToDTOs(user.getCompanies()),  
            //mapTicketsToDTOs(user.getTicketsCreated()),  
            //mapTicketsToDTOs(user.getTicketsAssigned())  
        );
    }

    // Converte UserRequestDTO para User
    public User toEntity(UserRequestDTO requestDTO) {
        User user = new User();
        user.setName(requestDTO.name());
        user.setEmail(requestDTO.email());
        user.setPassword(requestDTO.password());
        user.setRole(requestDTO.role());
        return user;
    }
 
/* 
    private List<CompanyResponseDTO> mapCompaniesToDTOs(List<Company> companies) {
        if (companies == null) {
            return List.of(); // Retorna uma lista vazia se companies for null
        }
        return companies.stream()
                .map(this::mapCompanyToDTO)
                .collect(Collectors.toList());
    }

    private List<TicketResponseDTO> mapTicketsToDTOs(List<Ticket> tickets) {
        if (tickets == null) {
            return List.of(); // Retorna uma lista vazia se tickets for null
        }
        return tickets.stream()
                .map(ticket -> new TicketResponseDTO(
                    ticket.getId(),
                    ticket.getDescription()
                    // Adicione outros campos conforme necessário
                ))
                .collect(Collectors.toList());
    }
                */
}