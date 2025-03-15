package com.javaspring.sistemadechamados.application.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.javaspring.sistemadechamados.application.dto.request.CompanyRequestDTO;
import com.javaspring.sistemadechamados.application.dto.response.CompanyResponseDTO;
import com.javaspring.sistemadechamados.domain.model.Company;

@Component
public class CompanyMapper {

    // Converte Company para CompanyResponseDTO
    public CompanyResponseDTO toResponseDTO(Company company) {
        return new CompanyResponseDTO(
            company.getId(),
            company.getName(),
            company.getCnpj(),
            company.getAddress(),
            company.getPhone()
           // mapTicketsToDTOs(company.getTickets()), 
            //mapUsersToDTOs(company.getTechnicians())  
        );
    }

    // Converte CompanyRequestDTO para Company
    public Company toEntity(CompanyRequestDTO requestDTO) {
        Company company = new Company();
        company.setName(requestDTO.name());
        company.setCnpj(requestDTO.cnpj());
        company.setAddress(requestDTO.address());
        company.setPhone(requestDTO.phone());

         company.setTickets(null); 
        company.setTechnicians(null);  

        return company;
    }

    /* 
 
    private List<TicketResponseDTO> mapTicketsToDTOs(List<Ticket> tickets) {
        if (tickets == null) {
            return List.of();  
        }
        return tickets.stream()
                .map(ticket -> new TicketResponseDTO(
                    ticket.getId(),
                    ticket.getDescription()
                    
                ))
                .collect(Collectors.toList());
    }

    private List<UserResponseDTO> mapUsersToDTOs(List<User> users) {
        if (users == null) {
            return List.of();  
        }
        return users.stream()
                .map(user -> new UserResponseDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail()
                    // Adicione outros campos conforme necessário
                ))
                .collect(Collectors.toList());
    }

    */
}